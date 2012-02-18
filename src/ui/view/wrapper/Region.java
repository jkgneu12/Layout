package ui.view.wrapper;

import ui.activity.BaseActivity;
import ui.factory.ControlFactory;
import ui.layout.FlowLayout;
import ui.layout.Layout;
import android.graphics.Color;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.MeasureSpec;
import android.view.View.OnTouchListener;
import android.widget.Button;
import android.widget.FrameLayout.LayoutParams;

public class Region extends Container {

	private boolean collapsed = false;
	private int expandedWidth = 0;
	private int expandedHeight = 0;
	
	
	public Region(BaseActivity context, ViewWrapper parent, int id) {
		super(context, parent, id);
		
		init();
		
	}
	
	public void init() {
		view = new FlowLayout(activity);
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
		
		view = Layout.create(getLayoutType(), activity);//new FlowLayout(activity);
		
		activity.getBaseView().getLayout().addView(button);
		
		createControls();
		layoutControls();
		addControls();
	}

	public void createControls() {
		controls = new ControlFactory().createControlsForControl(activity, this, id);
	}
	
	public void toggleCollapse(){
		if(collapsed){
			view.setLayoutParams(new LayoutParams(expandedWidth, expandedHeight));
			collapsed = false;
		} else {
			expandedWidth = view.getMeasuredWidth();
			expandedHeight = view.getMeasuredHeight();
			view.setLayoutParams(new LayoutParams(expandedWidth, 20));
			collapsed = true;
		}
	}

	@Override
	public void setText(String title) {
		// TODO Auto-generated method stub
		
	}
}
