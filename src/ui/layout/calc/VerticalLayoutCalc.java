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
		int horizontalMargin = 0;
		int verticalMargin = 0;
		
		int maxWidth = 0;
		
		for(Wrapper wrapper : childWrappers){
			int fullWidth = MeasureFactory.getMeasuredWidthPlusMarginsAndPadding(wrapper);
			wrapper.increaseLeftMargin(horizontalMargin + calculateLeftSpacing(wrapper, 0));
			wrapper.increaseTopMargin(verticalMargin);
        	verticalMargin += MeasureFactory.getMeasuredHeightPlusMargins(wrapper);
        	if(fullWidth > maxWidth)
        		maxWidth = fullWidth;
		}
		
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
