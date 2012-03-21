package ui.layout;

import ui.activity.BaseActivity;
import ui.layout.calc.LayoutCalc;
import ui.layout.calc.VerticalLayoutCalc;
import ui.wrapper.ContainerWrapper;

public class VerticalLayout extends Layout {

	public VerticalLayout(BaseActivity activity, ContainerWrapper wrapper) {
		super(activity, wrapper);
	}

	@Override
	protected LayoutCalc createCalc(ContainerWrapper wrapper) {
		return new VerticalLayoutCalc(wrapper);
	}

}
