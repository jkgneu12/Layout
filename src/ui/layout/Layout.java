package ui.layout;
import java.util.ArrayList;

import enums.ELayoutType;

import ui.layout.calc.LayoutCalc;
import ui.view.wrapper.ViewWrapper;
import android.content.Context;
import android.graphics.Color;
import android.widget.FrameLayout;


public abstract class Layout extends FrameLayout {
	
	public Layout(Context context) {
		super(context);
	
		setBackgroundColor(Color.GREEN);
		
		setLayoutParams(new LayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.MATCH_PARENT));
	}

	public void layoutControls(ArrayList<ViewWrapper> controls, int width, int height) {
		LayoutCalc calc = createCalc(controls, width, height);
		calc.layoutControls();
		calc = null;
	}

	protected abstract LayoutCalc createCalc(ArrayList<ViewWrapper> controls, int width, int height);
	
	
}
