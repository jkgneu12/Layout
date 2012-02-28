package ui.layout;

import ui.layout.calc.FlowLayoutCalc;
import ui.layout.calc.LayoutCalc;
import ui.view.wrapper.Container;
import android.content.Context;

public class FlowLayout extends Layout {

	public FlowLayout(Context context) {
		super(context);
	}

	@Override
	protected LayoutCalc createCalc(Container c, int width, int height) {
		return new FlowLayoutCalc(c, width, height);
	}

}
