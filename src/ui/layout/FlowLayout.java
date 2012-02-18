package ui.layout;

import java.util.ArrayList;

import ui.layout.calc.FlowLayoutCalc;
import ui.layout.calc.LayoutCalc;
import ui.view.wrapper.ViewWrapper;
import android.content.Context;

public class FlowLayout extends Layout {

	public FlowLayout(Context context) {
		super(context);
	}

	@Override
	protected LayoutCalc createCalc(ArrayList<ViewWrapper> controls, int width, int height) {
		return new FlowLayoutCalc(controls, width, height);
	}

}
