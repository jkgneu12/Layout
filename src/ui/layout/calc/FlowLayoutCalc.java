package ui.layout.calc;

import java.util.ArrayList;

import ui.factory.MeasureFactory;
import ui.wrapper.ContainerWrapper;
import ui.wrapper.Wrapper;

public class FlowLayoutCalc extends LayoutCalc {
	
	protected ArrayList<Integer> spacingWidth;
	protected ArrayList<Integer> spacingHeight;
	protected ArrayList<ArrayList<Wrapper<?>>> wrappersByLine;

	public FlowLayoutCalc(ContainerWrapper c) {
		super(c);
	}
	
	public void layoutViews() {
		groupWrappersByLine();
		calculateSpacingBetweenViews();
		applyLayoutParamsToViews();
		applySpacingBetweenViews();
	}
	
	/*
	 * Group wrappers into rows as they will appear on screen.
	 */
	protected void groupWrappersByLine() {
		wrappersByLine = new ArrayList<ArrayList<Wrapper<?>>>();
		wrappersByLine.add(new ArrayList<Wrapper<?>>());
		
		int wrapperWidth = 0;
		int line = 0;
		
		int maxWidth = MeasureFactory.subtractPaddingFromWidth(container);
		if(maxWidth == Wrapper.INVALID)
			maxWidth = container.getActivity().getScreenWidth();
		
		for(Wrapper<?> c : childWrappers){
			int w = MeasureFactory.getMeasuredWidthPlusMargins(c);
        	wrapperWidth += w;
			if(wrapperWidth > maxWidth){
				line++;
				wrapperWidth = w;
				wrappersByLine.add(new ArrayList<Wrapper<?>>());
			}
			wrappersByLine.get(line).add(c);
        	
		}
	}

	/*
	 * Calculate the horizontal and vertical spacing between wrappers
	 */
	protected void calculateSpacingBetweenViews() {
		spacingWidth = new ArrayList<Integer>();
		spacingHeight = new ArrayList<Integer>();
		
		//TODO:pull out check
		int maxWidth = MeasureFactory.subtractPaddingFromWidth(container);
		if(maxWidth == Wrapper.INVALID)
			maxWidth = container.getActivity().getScreenWidth();
		
		for(int line = 0; line < wrappersByLine.size(); line++){
			ArrayList<Wrapper<?>> lineOfWrappers = wrappersByLine.get(line);
			int wrapperWidth = 0;
			int maxHeight = 0;
			for(Wrapper<?> wrapper : lineOfWrappers){
	        	wrapperWidth += MeasureFactory.getMeasuredWidthPlusMargins(wrapper);
	        	int h = MeasureFactory.getMeasuredHeightPlusMargins(wrapper);
	        	if(h > maxHeight)
	        		maxHeight = h;
			}
			double spacing = maxWidth - wrapperWidth;
			if(lineOfWrappers.size() > 0){
				spacing = spacing / lineOfWrappers.size();
			}
			
			spacingWidth.add((int) spacing);
			spacingHeight.add(maxHeight);
		}
		
	}
	
	/*
	 * Apply the horizontal and vertical spacing to wrappers
	 */
	protected void applySpacingBetweenViews() {
		int verticalMargin = 0;
		for(int line = 0; line < wrappersByLine.size(); line++){
			ArrayList<Wrapper<?>> lineOfWrappers = wrappersByLine.get(line);
			int space = spacingWidth.get(line);
			int horizontalMargin = 0;
			if(line > 0)
				verticalMargin += spacingHeight.get(line - 1);
			for(Wrapper<?> wrapper : lineOfWrappers){
	        	horizontalMargin += calculateLeftSpacing(wrapper, space);
	        	wrapper.increaseLeftMargin(horizontalMargin);
	        	horizontalMargin += calculateRightSpacing(wrapper, space) + MeasureFactory.getMeasuredWidth(wrapper);
	        	wrapper.increaseTopMargin(verticalMargin + MeasureFactory.getMarginTop(wrapper));
			}
		}
		verticalMargin += spacingHeight.get(spacingHeight.size() - 1);
		container.setCalculatedHeight(verticalMargin);
		container.setCalculatedWidth(MeasureFactory.subtractPaddingFromWidth(container));
	}
	
	/**
	 * 
	 */
	protected int calculateLeftSpacing(Wrapper<?> wrapper, int space) {
		int marginLeft = MeasureFactory.getMarginLeft(wrapper);
		switch(wrapper.getConfig().screenAlignment){
		case LEFT: return marginLeft;
		case CENTER: return marginLeft + (space /2);
		case RIGHT: return marginLeft + space;
		}
		return 0;
	}
	
	protected int calculateRightSpacing(Wrapper<?> wrapper, int space) {
		int marginRight = MeasureFactory.getMarginRight(wrapper);;
		switch(wrapper.getConfig().screenAlignment){
		case RIGHT: return marginRight;
		case CENTER: return marginRight + (space /2);
		case LEFT: return marginRight + space;
		}
		return 0;
	}


	
	
}
