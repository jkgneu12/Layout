package ui.layout.calc;

import java.util.ArrayList;

import ui.view.control.IControl;
import android.widget.FrameLayout.LayoutParams;

public class HorizontalLayoutCalc extends LayoutCalc {

	public HorizontalLayoutCalc(ArrayList<IControl> controls, int parentWidth, int parentHeight) {
		super(controls, parentWidth, parentHeight);
	}
	
	public void layoutControls() {
		applyLayoutParams();
		applySpacing();
	}
	
	/*
	 * Apply the horizontal and vertical spacing to controls
	 */
	protected void applySpacing() {
		int horizontalMargin = 0;
		for(IControl control : controls){
			int width = getMeasuredWidth(control);
			int marginLeft = control.getControlConfig().getMarginLeft();
			int marginRight = control.getControlConfig().getMarginRight();
			int space = parentWidth - width - marginLeft - marginRight;
        	horizontalMargin += calculateLeftSpacing(control, space);
        	control.setTranslationX(horizontalMargin);
        	horizontalMargin += calculateRightSpacing(control, space) + width;
        	control.setTanslationY(control.getControlConfig().getMarginTop());
		}
	}
	
	protected int calculateLeftSpacing(IControl c, int space) {
		int marginLeft = c.getControlConfig().getMarginLeft();
		
		/*switch(c.getControlConfig().getScreenAlignment()){
		case LEFT: return marginLeft;
		case CENTER: return (space /2);
		case RIGHT: return marginLeft + (space /2);
		}
		return 0;*/
		return marginLeft;
	}
	
	protected int calculateRightSpacing(IControl c, int space) {
		int marginRight = c.getControlConfig().getMarginRight();
		
		/*switch(c.getControlConfig().getScreenAlignment()){
		case RIGHT: return marginRight;
		case CENTER: return (space /2);
		case LEFT: return marginRight + (space /2);
		}
		return 0;*/
		return marginRight;
	}
	
	/*
	 * Apply LayoutParams to each control
	 */
	protected void applyLayoutParams() {
		for(IControl control : controls){
			control.setLayoutParams(new LayoutParams(getMeasuredWidth(control), getMeasuredHeight(control)));
		}	
	}

}
