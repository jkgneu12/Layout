package ui.layout.calc;

import java.util.ArrayList;

import ui.wrapper.ContainerWrapper;
import ui.wrapper.Wrapper;
import android.widget.FrameLayout.LayoutParams;


/**
 * Calculates the absolute position of Wrapper(s) within a ContainerWrapper
 *
 * Layout(s) are positioned based on the parentWidth and parentHeight of their parent ContainerWrapper
 */
public abstract class LayoutCalc {

	protected ContainerWrapper container;

	protected int parentWidth;
	protected int parentHeight;
	
	protected ArrayList<Wrapper> childWrappers;

	public LayoutCalc(ContainerWrapper c, int parentWidth, int parentHeight) {
		this.container = c;
		
		this.childWrappers = c.getChildWrappers();
		
		this.parentWidth = parentWidth;
		this.parentHeight = parentHeight;
	}
	
	
	public abstract void layoutViews();
	
	/**
	 * Set each wrapper to their measuredWidth and measuredHeight
	 */
	protected void applyLayoutParamsToViews() {
		LayoutParams layoutParams;
		for(Wrapper wrapper : childWrappers){
			layoutParams = new LayoutParams(wrapper.getMeasuredWidth(), wrapper.getMeasuredHeight());
			wrapper.getView().setLayoutParams(layoutParams);
		}
	}
	
	
	/**
	 * Determines the amount of space to the left of a Wrapper(s) Layout
	 * 
	 * Usually comprised of the leftMargin and screenAlignment
	 */
	protected abstract int calculateLeftSpacing(Wrapper wrapper, int space);
	
	
	/**
	 * Determines the amount of space to the right of a Wrapper(s) Layout
	 * 
	 * Usually comprised of the rightMargin and screenAlignment
	 */
	protected abstract int calculateRightSpacing(Wrapper wrapper, int space);
}
