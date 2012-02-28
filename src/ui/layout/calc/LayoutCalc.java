package ui.layout.calc;

import java.util.ArrayList;

import ui.activity.BaseActivity;
import ui.view.wrapper.Container;
import ui.view.wrapper.ViewWrapper;
import config.ControlConfig;

public abstract class LayoutCalc {

	protected ArrayList<ViewWrapper> controls;

	protected int parentWidth;
	protected int parentHeight;

	protected Container container;

	public LayoutCalc(Container c, int parentWidth, int parentHeight) {
		this.container = c;
		this.controls = c.getControls();
		this.parentWidth = parentWidth;
		this.parentHeight = parentHeight;
	}
	
	
	public abstract void layoutControls();
	
	protected abstract int calculateLeftSpacing(ViewWrapper c, int space);
	
	protected abstract int calculateRightSpacing(ViewWrapper c, int space);
}
