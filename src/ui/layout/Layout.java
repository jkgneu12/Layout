package ui.layout;
import ui.layout.calc.LayoutCalc;
import ui.view.wrapper.Container;
import android.content.Context;
import android.graphics.Color;
import android.widget.FrameLayout;


public abstract class Layout extends FrameLayout {
	
	public Layout(Context context) {
		super(context);
	
		setBackgroundColor(Color.GREEN);
		
		setLayoutParams(new LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.MATCH_PARENT));
	}

	public void layoutControls(Container c, int width, int height) {
		LayoutCalc calc = createCalc(c, width, height);
		calc.layoutControls();
		calc = null;
	}

	protected abstract LayoutCalc createCalc(Container c, int width, int height);
	
	@Override
	public void setTranslationX(float translationX) {
		super.setTranslationX(translationX);
	}
	
	@Override
	public void setTranslationY(float translationY) {
		// TODO Auto-generated method stub
		super.setTranslationY(translationY);
	}
	
	
}
