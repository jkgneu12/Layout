package ui.layout;

import java.util.ArrayList;

import ui.layout.calc.HorizontalLayoutCalc;
import ui.layout.calc.LayoutCalc;
import ui.view.control.IControl;
import android.content.Context;

public class HorizontalLayout extends Layout {

	public HorizontalLayout(Context context) {
		super(context);
	}
	
	@Override
	protected LayoutCalc createCalc(ArrayList<IControl> controls, int width, int height) {
		return new HorizontalLayoutCalc(controls, width, height);
	}

}
