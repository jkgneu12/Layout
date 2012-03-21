package ui.layout;
import ui.activity.BaseActivity;
import ui.layout.calc.LayoutCalc;
import ui.wrapper.ContainerWrapper;
import ui.wrapper.Wrapper;
import android.view.View;
import android.widget.FrameLayout;


public abstract class Layout extends FrameLayout {
	
	private ContainerWrapper wrapper;

	public Layout(BaseActivity activity, ContainerWrapper wrapper) {
		super(activity);
		this.wrapper = wrapper;
	
		//GradientDrawable d = new GradientDrawable();
		//d.setColor(Color.GREEN);
		
		//setBackgroundDrawable(d);
		
		//setBackgroundColor(Color.GREEN);
		
		setLayoutParams(new LayoutParams(LayoutParams.FILL_PARENT, LayoutParams.FILL_PARENT));
		
		setOnClickListener(wrapper);
	}

	public void layoutChildWrappers() {
		LayoutCalc calc = createCalc(wrapper);
		calc.layoutViews();
		calc = null;
	}

	protected abstract LayoutCalc createCalc(ContainerWrapper c);

	public Wrapper getWrapper() {
		return wrapper;
		
	}

	public void resetLayout() {}
	
	public View self() {
		return this;
	}
	
}
