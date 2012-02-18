package ui.layout.calc;

import java.util.ArrayList;

import ui.view.wrapper.ViewWrapper;
import android.widget.FrameLayout.LayoutParams;

public class FlowLayoutCalc extends LayoutCalc {
	
	protected ArrayList<Integer> spacingWidth;
	protected ArrayList<Integer> spacingHeight;
	protected ArrayList<ArrayList<ViewWrapper>> controlsByLine;

	public FlowLayoutCalc(ArrayList<ViewWrapper> controls, int parentWidth, int parentHeight) {
		super(controls, parentWidth, parentHeight);
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
		
		for(ViewWrapper c : controls){
        	controlWidth += getMeasuredWidth(c) + c.getMarginLeft() + c.getMarginRight();
			if(controlWidth > parentWidth){
				line++;
				controlWidth = 0;
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
		
		for(int line = 0; line < controlsByLine.size(); line++){
			ArrayList<ViewWrapper> lineOfControls = controlsByLine.get(line);
			int controlWidth = 0;
			int maxHeight = 0;
			for(ViewWrapper c : lineOfControls){
	        	controlWidth += getMeasuredWidth(c) + c.getMarginLeft() + c.getMarginRight();
	        	int h = getMeasuredHeight(c) + c.getMarginTop() + c.getMarginBottom();
	        	if(h > maxHeight)
	        		maxHeight = h;
			}
			double remainingWidth = parentWidth - controlWidth;
			
			spacingWidth.add((int) (remainingWidth / (lineOfControls.size())));
			spacingHeight.add(maxHeight);
		}
		
	}
	
	/*
	 * Apply the horizontal and vertical spacing to controls
	 */
	protected void applySpacing() {
		
		int verticalMargin = 0;
		for(int line = 0; line < controlsByLine.size(); line++){
			ArrayList<ViewWrapper> lineOfControls = controlsByLine.get(line);
			int space = spacingWidth.get(line);
			int horizontalMargin = 0;
			if(line > 0)
				verticalMargin += spacingHeight.get(line - 1);
			for(ViewWrapper c : lineOfControls){
	        	horizontalMargin += calculateLeftSpacing(c, space);
	        	c.getView().setTranslationX(horizontalMargin);
	        	horizontalMargin += calculateRightSpacing(c, space) + getMeasuredWidth(c);
	        	c.getView().setTranslationY(verticalMargin + c.getMarginTop());
			}
		}
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
				
				layoutParams = new LayoutParams(getMeasuredWidth(control), getMeasuredHeight(control));
				control.getView().setLayoutParams(layoutParams);
				count++;
			}
		}	
	}
	
}
