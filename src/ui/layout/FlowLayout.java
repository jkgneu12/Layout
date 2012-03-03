package ui.layout;

import ui.activity.BaseActivity;
import ui.layout.calc.FlowLayoutCalc;
import ui.layout.calc.LayoutCalc;
import ui.wrapper.ContainerWrapper;
import ui.wrapper.Wrapper;

public class FlowLayout extends Layout {

	public FlowLayout(BaseActivity activity, Wrapper wrapper) {
		super(activity, wrapper);
	}

	@Override
	protected LayoutCalc createCalc(ContainerWrapper c, int width, int height) {
		return new FlowLayoutCalc(c, width, height);
	}

}
