package ui.layout.calc;

import java.util.ArrayList;

import ui.wrapper.ContainerWrapper;
import ui.wrapper.Wrapper;

public class FlowLayoutCalc extends LayoutCalc {
	
	protected ArrayList<Integer> spacingWidth;
	protected ArrayList<Integer> spacingHeight;
	protected ArrayList<ArrayList<Wrapper>> wrappersByLine;

	public FlowLayoutCalc(ContainerWrapper c, int parentWidth, int parentHeight) {
		super(c, parentWidth, parentHeight);
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
		wrappersByLine = new ArrayList<ArrayList<Wrapper>>();
		wrappersByLine.add(new ArrayList<Wrapper>());
		
		int wrapperWidth = 0;
		int line = 0;
		
		int maxWidth = container.subtractPaddingFromWidth(parentWidth);
		
		for(Wrapper c : childWrappers){
			int w = c.getMeasuredWidthPlusMargins();
        	wrapperWidth += w;
			if(wrapperWidth > maxWidth){
				line++;
				wrapperWidth = w;
				wrappersByLine.add(new ArrayList<Wrapper>());
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
		
		int maxWidth = parentWidth;
		
		for(int line = 0; line < wrappersByLine.size(); line++){
			ArrayList<Wrapper> lineOfWrappers = wrappersByLine.get(line);
			int wrapperWidth = 0;
			int maxHeight = 0;
			for(Wrapper wrapper : lineOfWrappers){
	        	wrapperWidth += wrapper.getMeasuredWidthPlusMargins();
	        	int h = wrapper.getMeasuredHeightPlusMargins();
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
		int verticalMargin = container.getConfig().paddingTop;
		for(int line = 0; line < wrappersByLine.size(); line++){
			ArrayList<Wrapper> lineOfWrappers = wrappersByLine.get(line);
			int space = spacingWidth.get(line);
			int horizontalMargin = container.getConfig().paddingLeft;
			if(line > 0)
				verticalMargin += spacingHeight.get(line - 1);
			for(Wrapper wrapper : lineOfWrappers){
	        	horizontalMargin += calculateLeftSpacing(wrapper, space);
	        	wrapper.getView().setTranslationX(horizontalMargin);
	        	horizontalMargin += calculateRightSpacing(wrapper, space) + wrapper.getMeasuredWidth();
	        	wrapper.getView().setTranslationY(verticalMargin + wrapper.getConfig().marginTop);
			}
		}
		verticalMargin += spacingHeight.get(spacingHeight.size() - 1);
		verticalMargin += container.getConfig().paddingBottom;
		container.setCalculatedHeight(verticalMargin);
		container.setCalculatedWidth(container.subtractMarginFromWidth(parentWidth));
	}
	
	/**
	 * 
	 */
	protected int calculateLeftSpacing(Wrapper wrapper, int space) {
		int marginLeft = wrapper.getConfig().marginLeft;
		switch(wrapper.getConfig().screenAlignment){
		case LEFT: return marginLeft;
		case CENTER: return marginLeft + (space /2);
		case RIGHT: return marginLeft + space;
		}
		return 0;
	}
	
	protected int calculateRightSpacing(Wrapper wrapper, int space) {
		int marginRight = wrapper.getConfig().marginRight;
		switch(wrapper.getConfig().screenAlignment){
		case RIGHT: return marginRight;
		case CENTER: return marginRight + (space /2);
		case LEFT: return marginRight + space;
		}
		return 0;
	}


	
	
}
