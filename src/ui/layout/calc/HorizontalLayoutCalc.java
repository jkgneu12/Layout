package ui.layout.calc;

import ui.factory.MeasureFactory;
import ui.wrapper.ContainerWrapper;
import ui.wrapper.Wrapper;

/**
 * 
 *
 */
public class HorizontalLayoutCalc extends LayoutCalc {

	public HorizontalLayoutCalc(ContainerWrapper c) {
		super(c);
	}
	
	public void layoutViews() {
		applyLayoutParamsToViews();
		applySpacingToViews();
	}
	
	protected void applySpacingToViews() {
		int xOffset = 0;
		
		int maxHeight = MeasureFactory.getPaddingTop(container);;
		
		for(Wrapper<?> wrapper : childWrappers){
			if(wrapper.getView() != null){
				int width = MeasureFactory.getMeasuredWidth(wrapper);
				int space = 0;
				
	        	xOffset += calculateLeftSpacing(wrapper, space);
	        	wrapper.increaseLeftMargin(xOffset);
	        	xOffset += calculateRightSpacing(wrapper, space) + width;
	        	
	        	wrapper.increaseTopMargin(MeasureFactory.getMarginTop(wrapper));
	        	
	        	int fullHeight = MeasureFactory.getMeasuredHeightPlusMargins(wrapper);
	        	if(fullHeight > maxHeight)
	        		maxHeight = fullHeight;
			}
		}
		
		maxHeight += MeasureFactory.getPaddingBottom(container);
		container.setCalculatedWidth(xOffset);
		container.setCalculatedHeight(maxHeight);
	}
	
	protected int calculateLeftSpacing(Wrapper<?> wrapper, int space) {
		return MeasureFactory.getMarginLeft(wrapper);
	}
	
	protected int calculateRightSpacing(Wrapper<?> wrapper, int space) {
		return MeasureFactory.getMarginRight(wrapper);
	}

}
