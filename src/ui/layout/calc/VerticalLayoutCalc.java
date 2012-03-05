package ui.layout.calc;

import ui.factory.MeasureFactory;
import ui.wrapper.ContainerWrapper;
import ui.wrapper.Wrapper;

public class VerticalLayoutCalc extends LayoutCalc {

	public VerticalLayoutCalc(ContainerWrapper c) {
		super(c);
	}

	@Override
	public void layoutViews() {
		applyLayoutParamsToViews();
		applySpacingToViews();
	}
	
	protected void applySpacingToViews() {
		int horizontalMargin = MeasureFactory.getPaddingLeft(container);
		int verticalMargin = MeasureFactory.getPaddingTop(container);
		
		int maxWidth = 0;//container.subtractPaddingFromHeight(parentWidth);
		
		for(Wrapper wrapper : childWrappers){
			int fullWidth = MeasureFactory.getMeasuredWidthPlusMarginsAndPadding(wrapper);
        	wrapper.getView().setTranslationX(horizontalMargin + calculateLeftSpacing(wrapper, 0));
        	wrapper.getView().setTranslationY(verticalMargin);
        	verticalMargin += MeasureFactory.getMeasuredHeightPlusMargins(wrapper);
        	if(fullWidth > maxWidth)
        		maxWidth = fullWidth;
		}
		horizontalMargin +=  MeasureFactory.getPaddingRight(container);
		container.setCalculatedWidth(maxWidth);
		container.setCalculatedHeight(verticalMargin);
	}

	@Override
	protected int calculateLeftSpacing(Wrapper wrapper, int space) {
		return MeasureFactory.getMarginLeft(wrapper);
	}

	@Override
	protected int calculateRightSpacing(Wrapper wrapper, int space) {
		return MeasureFactory.getMarginRight(wrapper);
	}

}
