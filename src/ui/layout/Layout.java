package ui.layout;
import ui.activity.BaseActivity;
import ui.layout.calc.LayoutCalc;
import ui.wrapper.ContainerWrapper;
import ui.wrapper.Wrapper;
import android.widget.FrameLayout;


public abstract class Layout extends FrameLayout {
	
	private ContainerWrapper wrapper;

	public Layout(BaseActivity activity, ContainerWrapper wrapper) {
		super(activity);
		this.wrapper = wrapper;
		
		setLayoutParams(new LayoutParams(LayoutParams.FILL_PARENT, LayoutParams.FILL_PARENT));
		
		setOnClickListener(wrapper);
	}

	public void layoutChildWrappers() {
		LayoutCalc calc = createCalc(wrapper);
		calc.layoutViews();
		calc = null;
	}

	protected abstract LayoutCalc createCalc(ContainerWrapper c);

	public Wrapper<Layout> getWrapper() {
		return wrapper;
		
	}

	public void resetLayout() {}
	
}
