package ui.layout.calc;

import ui.wrapper.ContainerWrapper;
import ui.wrapper.Wrapper;

/**
 * 
 *
 */
public class HorizontalLayoutCalc extends LayoutCalc {

	public HorizontalLayoutCalc(ContainerWrapper c, int parentWidth, int parentHeight) {
		super(c, parentWidth, parentHeight);
	}
	
	public void layoutViews() {
		applyLayoutParamsToViews();
		applySpacingToViews();
	}
	
	protected void applySpacingToViews() {
		int horizontalMargin = container.getPaddingLeft();
		int maxHeight = 0;
		
		int maxWidth = container.subtractPaddingFromWidth(parentWidth);
		
		for(Wrapper wrapper : childWrappers){
			int width = wrapper.getMeasuredWidth();
			int space = maxWidth - width;
        	horizontalMargin += calculateLeftSpacing(wrapper, space);
        	wrapper.getView().setTranslationX(horizontalMargin);
        	horizontalMargin += calculateRightSpacing(wrapper, space) + width;
        	wrapper.getView().setTranslationY(wrapper.getMarginTop());
        	int fullHeight = wrapper.getMeasuredHeightPlusMargins();
        	if(fullHeight > maxHeight)
        		maxHeight = fullHeight;
		}
		horizontalMargin += container.getPaddingRight();
		container.setCalculatedWidth(horizontalMargin);
		container.setCalculatedHeight(maxHeight);
	}
	
	protected int calculateLeftSpacing(Wrapper wrapper, int space) {
		return wrapper.getMarginLeft();
	}
	
	protected int calculateRightSpacing(Wrapper wrapper, int space) {
		return wrapper.getMarginRight();
	}

}
