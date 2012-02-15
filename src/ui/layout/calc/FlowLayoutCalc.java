package ui.layout.calc;

import java.util.ArrayList;

import ui.view.control.IControl;
import android.widget.FrameLayout.LayoutParams;

public class FlowLayoutCalc extends LayoutCalc {
	
	protected ArrayList<Integer> spacingWidth;
	protected ArrayList<Integer> spacingHeight;
	protected ArrayList<ArrayList<IControl>> controlsByLine;

	public FlowLayoutCalc(ArrayList<IControl> controls, int parentWidth, int parentHeight) {
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
		controlsByLine = new ArrayList<ArrayList<IControl>>();
		controlsByLine.add(new ArrayList<IControl>());
		
		int controlWidth = 0;
		int line = 0;
		
		for(IControl c : controls){
        	controlWidth += getMeasuredWidth(c) + c.getControlConfig().getMarginLeft() + c.getControlConfig().getMarginRight();
			if(controlWidth > parentWidth){
				line++;
				controlWidth = 0;
				controlsByLine.add(new ArrayList<IControl>());
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
			ArrayList<IControl> lineOfControls = controlsByLine.get(line);
			int controlWidth = 0;
			int maxHeight = 0;
			for(IControl c : lineOfControls){
	        	controlWidth += getMeasuredWidth(c) + c.getControlConfig().getMarginLeft() + c.getControlConfig().getMarginRight();
	        	int h = getMeasuredHeight(c) + c.getControlConfig().getMarginTop() + c.getControlConfig().getMarginBottom();
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
			ArrayList<IControl> lineOfControls = controlsByLine.get(line);
			int space = spacingWidth.get(line);
			int horizontalMargin = 0;
			if(line > 0)
				verticalMargin += spacingHeight.get(line - 1);
			for(IControl c : lineOfControls){
	        	horizontalMargin += calculateLeftSpacing(c, space);
	        	c.setTranslationX(horizontalMargin);
	        	horizontalMargin += calculateRightSpacing(c, space) + getMeasuredWidth(c);
	        	c.setTanslationY(verticalMargin + c.getControlConfig().getMarginTop());
			}
		}
	}
	
	protected int calculateLeftSpacing(IControl c, int space) {
		int marginLeft = c.getControlConfig().getMarginLeft();
		switch(c.getControlConfig().getScreenAlignment()){
		case LEFT: return marginLeft;
		case CENTER: return marginLeft + (space /2);
		case RIGHT: return marginLeft + space;
		}
		return 0;
	}
	
	protected int calculateRightSpacing(IControl c, int space) {
		int marginRight = c.getControlConfig().getMarginRight();
		switch(c.getControlConfig().getScreenAlignment()){
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
			ArrayList<IControl> lineOfControls = controlsByLine.get(line);
			for(int c = 0; c < lineOfControls.size(); c++){
				IControl control = lineOfControls.get(c);
				
				layoutParams = new LayoutParams(getMeasuredWidth(control), getMeasuredHeight(control));
				control.setLayoutParams(layoutParams);
				count++;
			}
		}	
	}
	
}
