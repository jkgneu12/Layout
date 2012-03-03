package ui.layout;
import ui.activity.BaseActivity;
import ui.layout.calc.LayoutCalc;
import ui.wrapper.ContainerWrapper;
import ui.wrapper.Wrapper;
import android.graphics.Color;
import android.widget.FrameLayout;


public abstract class Layout extends FrameLayout {
	
	private Wrapper wrapper;

	public Layout(BaseActivity activity, Wrapper wrapper) {
		super(activity);
		this.wrapper = wrapper;
	
		setBackgroundColor(Color.GREEN);
		
		setLayoutParams(new LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.MATCH_PARENT));
	}

	public void layoutWrappers(ContainerWrapper c, int width, int height) {
		LayoutCalc calc = createCalc(c, width, height);
		calc.layoutViews();
		calc = null;
	}

	protected abstract LayoutCalc createCalc(ContainerWrapper c, int width, int height);

	public Wrapper getWrapper() {
		return wrapper;
		
	}

	public float getXFraction() {
        return getX() / getWidth(); // TODO: guard divide-by-zero
    }

    public void setXFraction(float xFraction) {
        // TODO: cache width
        final int width = getWidth();
        setX((width > 0) ? (xFraction * width) : -9999);
    }
	
	
}
