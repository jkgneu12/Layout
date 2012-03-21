package ui.layout;

import ui.activity.BaseActivity;
import ui.layout.calc.FlowLayoutCalc;
import ui.layout.calc.LayoutCalc;
import ui.wrapper.ContainerWrapper;

public class FlowLayout extends Layout {

	public FlowLayout(BaseActivity activity, ContainerWrapper wrapper) {
		super(activity, wrapper);
	}

	@Override
	protected LayoutCalc createCalc(ContainerWrapper wrapper) {
		return new FlowLayoutCalc(wrapper);
	}

}
