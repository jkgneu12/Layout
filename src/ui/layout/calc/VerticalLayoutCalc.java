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
		int horizontalMargin = container.getConfig().paddingLeft;
		int verticalMargin = container.getConfig().paddingTop;
		
		int maxWidth = container.subtractPaddingFromHeight(parentWidth);
		
		for(Wrapper wrapper : childWrappers){
			int fullWidth = wrapper.getMeasuredWidth();
        	wrapper.getView().setTranslationX(horizontalMargin + calculateLeftSpacing(wrapper, 0));
        	wrapper.getView().setTranslationY(verticalMargin);
        	verticalMargin += wrapper.getMeasuredHeightPlusMargins();
        	if(fullWidth > maxWidth)
        		maxWidth = fullWidth;
		}
		horizontalMargin += container.getConfig().paddingRight;
		container.setCalculatedWidth(maxWidth);
		container.setCalculatedHeight(verticalMargin);
	}

	@Override
	protected int calculateLeftSpacing(Wrapper wrapper, int space) {
		return wrapper.getConfig().marginLeft;
	}

	@Override
	protected int calculateRightSpacing(Wrapper wrapper, int space) {
		return wrapper.getConfig().marginRight;
	}

}
