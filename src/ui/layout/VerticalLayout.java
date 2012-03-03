package ui.layout;

import ui.activity.BaseActivity;
import ui.layout.calc.LayoutCalc;
import ui.layout.calc.VerticalLayoutCalc;
import ui.wrapper.ContainerWrapper;
import ui.wrapper.Wrapper;

public class VerticalLayout extends Layout {

	public VerticalLayout(BaseActivity activity, Wrapper wrapper) {
		super(activity, wrapper);
	}

	@Override
	protected LayoutCalc createCalc(ContainerWrapper c, int width, int height) {
		return new VerticalLayoutCalc(c, width, height);
	}

}
