package ui.layout;

import ui.layout.calc.HorizontalLayoutCalc;
import ui.layout.calc.LayoutCalc;
import ui.view.wrapper.Container;
import android.content.Context;

public class HorizontalLayout extends Layout {

	public HorizontalLayout(Context context) {
		super(context);
	}
	
	@Override
	protected LayoutCalc createCalc(Container c, int width, int height) {
		return new HorizontalLayoutCalc(c, width, height);
	}

}
