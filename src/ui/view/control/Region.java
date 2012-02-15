package ui.view.control;

import ui.activity.BaseActivity;
import ui.factory.ControlFactory;
import ui.layout.FlowLayout;
import ui.layout.Layout;
import ui.view.Container;
import ui.view.IView;
import android.graphics.Color;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.MeasureSpec;
import android.view.View.OnTouchListener;
import android.widget.Button;
import android.widget.FrameLayout.LayoutParams;
import config.ControlConfig;

public class Region extends Container implements IControl {

	private ControlConfig controlConfig;
	
	private boolean collapsed = false;
	private int expandedWidth = 0;
	private int expandedHeight = 0;
	
	
	public Region(BaseActivity context, IView parent, ControlConfig config) {
		super(context, parent, config.getWidth(), config.getHeight());
		this.controlConfig = config;
		
		init();
		
	}
	
	public void init() {
		layout = new FlowLayout(activity);
		Button button = new Button(activity);
		button.setOnTouchListener(new OnTouchListener() {
			
			public boolean onTouch(View v, MotionEvent event) {
				if(event.getAction() == MotionEvent.ACTION_DOWN)
					toggleCollapse();
				return true;
			}
		});
		button.setText("Toggle");
		button.setBackgroundColor(Color.CYAN);
		button.measure(MeasureSpec.UNSPECIFIED, MeasureSpec.UNSPECIFIED);
		button.setLayoutParams(new LayoutParams(button.getMeasuredWidth(), button.getMeasuredWidth()));
		
		layout = Layout.create(getLayoutType(), activity);//new FlowLayout(activity);
		
		activity.getBaseView().getLayout().addView(button);
		
		createControls();
		layoutControls();
		addControls();
	}

	public void createControls() {
		controls = new ControlFactory().createControls(activity, this, controlConfig);
	}
	
	public void addControls() {
		super.addControls();
	}
	
	public void toggleCollapse(){
		if(collapsed){
			setLayoutParams(new LayoutParams(expandedWidth, expandedHeight));
			collapsed = false;
		} else {
			expandedWidth = layout.getMeasuredWidth();
			expandedHeight = layout.getMeasuredHeight();
			setLayoutParams(new LayoutParams(expandedWidth, 20));
			collapsed = true;
		}
	}
	

	public ControlConfig getControlConfig() {
		return controlConfig;
	}
	
	public String getLayoutType() {
		return controlConfig.getLayoutType();
	}
	
	public void setText(String title) {
		//setTitle??
	}
}
