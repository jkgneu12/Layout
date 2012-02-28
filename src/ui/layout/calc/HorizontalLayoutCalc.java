package ui.layout.calc;

import ui.view.wrapper.Container;
import ui.view.wrapper.ViewWrapper;
import android.widget.FrameLayout.LayoutParams;

public class HorizontalLayoutCalc extends LayoutCalc {

	public HorizontalLayoutCalc(Container c, int parentWidth, int parentHeight) {
		super(c, parentWidth, parentHeight);
	}
	
	public void layoutControls() {
		applyLayoutParams();
		applySpacing();
	}
	
	/*
	 * Apply the horizontal and vertical spacing to controls
	 */
	protected void applySpacing() {
		int horizontalMargin = container.getPaddingLeft();
		int maxHeight = 0;
		
		int maxWidth = container.subtractPaddingFromWidth(parentWidth);
		
		for(ViewWrapper control : controls){
			int width = control.getMeasuredWidth();
			int space = maxWidth - width;
        	horizontalMargin += calculateLeftSpacing(control, space);
        	control.getView().setTranslationX(horizontalMargin);
        	horizontalMargin += calculateRightSpacing(control, space) + width;
        	control.getView().setTranslationY(control.getMarginTop());
        	int fullHeight = control.getMeasuredHeightPlusMargins();
        	if(fullHeight > maxHeight)
        		maxHeight = fullHeight;
		}
		horizontalMargin += container.getPaddingRight();
		container.setCalculatedWidth(horizontalMargin);
		container.setCalculatedHeight(maxHeight);
	}
	
	protected int calculateLeftSpacing(ViewWrapper c, int space) {
		int marginLeft = c.getMarginLeft();
		
		/*switch(c.getControlConfig().getScreenAlignment()){
		case LEFT: return marginLeft;
		case CENTER: return (space /2);
		case RIGHT: return marginLeft + (space /2);
		}
		return 0;*/
		return marginLeft;
	}
	
	protected int calculateRightSpacing(ViewWrapper c, int space) {
		int marginRight = c.getMarginRight();
		
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
		for(ViewWrapper control : controls){
			control.getView().setLayoutParams(new LayoutParams(control.getMeasuredWidth(), control.getMeasuredHeight()));
		}	
	}

}
