package ui.layout.calc;

import java.util.ArrayList;

import ui.view.wrapper.Container;
import ui.view.wrapper.ViewWrapper;
import android.widget.FrameLayout.LayoutParams;

public class FlowLayoutCalc extends LayoutCalc {
	
	protected ArrayList<Integer> spacingWidth;
	protected ArrayList<Integer> spacingHeight;
	protected ArrayList<ArrayList<ViewWrapper>> controlsByLine;

	public FlowLayoutCalc(Container c, int parentWidth, int parentHeight) {
		super(c, parentWidth, parentHeight);
	}
	
	public void layoutControls() {
		groupControlsByLine();
		calculateSpacing();
		applyLayoutParams();
		applySpacing();
	}
	
	/*
	 * Group controls into rows as they will appear on screen.
	 */
	protected void groupControlsByLine() {
		controlsByLine = new ArrayList<ArrayList<ViewWrapper>>();
		controlsByLine.add(new ArrayList<ViewWrapper>());
		
		int controlWidth = 0;
		int line = 0;
		
		int maxWidth = container.subtractPaddingFromWidth(parentWidth);
		
		for(ViewWrapper c : controls){
			int w = c.getMeasuredWidthPlusMargins();
        	controlWidth += w;
			if(controlWidth > maxWidth){
				line++;
				controlWidth = w;
				controlsByLine.add(new ArrayList<ViewWrapper>());
			}
			controlsByLine.get(line).add(c);
        	
		}
	}

	/*
	 * Calculate the horizontal and vertical spacing between controls
	 */
	protected void calculateSpacing() {
		spacingWidth = new ArrayList<Integer>();
		spacingHeight = new ArrayList<Integer>();
		
		int maxWidth = parentWidth;
		
		for(int line = 0; line < controlsByLine.size(); line++){
			ArrayList<ViewWrapper> lineOfControls = controlsByLine.get(line);
			int controlWidth = 0;
			int maxHeight = 0;
			for(ViewWrapper c : lineOfControls){
	        	controlWidth += c.getMeasuredWidthPlusMargins();
	        	int h = c.getMeasuredHeightPlusMargins();
	        	if(h > maxHeight)
	        		maxHeight = h;
			}
			double spacing = maxWidth - controlWidth;
			if(lineOfControls.size() > 0){
				spacing = spacing / lineOfControls.size();
			}
			
			spacingWidth.add((int) spacing);
			spacingHeight.add(maxHeight);
		}
		
	}
	
	/*
	 * Apply the horizontal and vertical spacing to controls
	 */
	protected void applySpacing() {
		int verticalMargin = container.getPaddingTop();
		for(int line = 0; line < controlsByLine.size(); line++){
			ArrayList<ViewWrapper> lineOfControls = controlsByLine.get(line);
			int space = spacingWidth.get(line);
			int horizontalMargin = container.getPaddingLeft();
			if(line > 0)
				verticalMargin += spacingHeight.get(line - 1);
			for(ViewWrapper c : lineOfControls){
	        	horizontalMargin += calculateLeftSpacing(c, space);
	        	c.getView().setTranslationX(horizontalMargin);
	        	horizontalMargin += calculateRightSpacing(c, space) + c.getMeasuredWidth();
	        	c.getView().setTranslationY(verticalMargin + c.getMarginTop());
			}
		}
		verticalMargin += spacingHeight.get(spacingHeight.size() - 1);
		verticalMargin += container.getPaddingBottom();
		container.setCalculatedHeight(verticalMargin);
		container.setCalculatedWidth(container.subtractMarginFromWidth(parentWidth));
	}
	
	protected int calculateLeftSpacing(ViewWrapper c, int space) {
		int marginLeft = c.getMarginLeft();
		switch(c.getScreenAlignment()){
		case LEFT: return marginLeft;
		case CENTER: return marginLeft + (space /2);
		case RIGHT: return marginLeft + space;
		}
		return 0;
	}
	
	protected int calculateRightSpacing(ViewWrapper c, int space) {
		int marginRight = c.getMarginRight();
		switch(c.getScreenAlignment()){
		case RIGHT: return marginRight;
		case CENTER: return marginRight + (space /2);
		case LEFT: return marginRight + space;
		}
		return 0;
	}


	/*
	 * Apply LayoutParams to each control
	 */
	protected void applyLayoutParams() {
		LayoutParams layoutParams;
		int count = 0;
		for(int line = 0; line < controlsByLine.size(); line++){
			ArrayList<ViewWrapper> lineOfControls = controlsByLine.get(line);
			for(int c = 0; c < lineOfControls.size(); c++){
				ViewWrapper control = lineOfControls.get(c);
				
				layoutParams = new LayoutParams(control.getMeasuredWidth(), control.getMeasuredHeight());
				control.getView().setLayoutParams(layoutParams);
				count++;
			}
		}	
	}
	
}
