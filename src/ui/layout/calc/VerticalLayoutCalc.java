package ui.layout.calc;

import ui.wrapper.ContainerWrapper;
import ui.wrapper.Wrapper;

public class VerticalLayoutCalc extends LayoutCalc {

	public VerticalLayoutCalc(ContainerWrapper c, int parentWidth, int parentHeight) {
		super(c, parentWidth, parentHeight);
	}

	@Override
	public void layoutViews() {
		applyLayoutParamsToViews();
		applySpacingToViews();
	}
	
	protected void applySpacingToViews() {
		int horizontalMargin = container.getPaddingLeft();
		int verticalMargin = container.getPaddingTop();
		
		int maxWidth = container.subtractPaddingFromHeight(parentWidth);
		
		for(Wrapper wrapper : childWrappers){
			int fullWidth = wrapper.getMeasuredWidth();
        	wrapper.getView().setTranslationX(horizontalMargin + calculateLeftSpacing(wrapper, 0));
        	wrapper.getView().setTranslationY(verticalMargin);
        	verticalMargin += wrapper.getMeasuredHeightPlusMargins();
        	if(fullWidth > maxWidth)
        		maxWidth = fullWidth;
		}
		horizontalMargin += container.getPaddingRight();
		container.setCalculatedWidth(maxWidth);
		container.setCalculatedHeight(verticalMargin);
	}

	@Override
	protected int calculateLeftSpacing(Wrapper wrapper, int space) {
		return wrapper.getMarginLeft();
	}

	@Override
	protected int calculateRightSpacing(Wrapper wrapper, int space) {
		return wrapper.getMarginRight();
	}

}
