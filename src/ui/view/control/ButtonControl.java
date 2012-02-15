package ui.view.control;

import ui.activity.BaseActivity;
import ui.view.IView;
import android.content.Context;
import android.view.KeyEvent;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.FrameLayout.LayoutParams;
import config.ControlConfig;

public class ButtonControl extends Button implements IControl {

	private BaseActivity activity;
	private ControlConfig config;
	
	private IView parent;
	
	public ButtonControl(Context context, IView parent, ControlConfig config) {
		super(context);
		this.activity = (BaseActivity)context;
		this.parent = parent;
		this.config = config;
	}
	
	public void updateData(){
		setText("123");
	}
	
	@Override
	public boolean onTouchEvent(MotionEvent event) {
		if(event.getAction() == MotionEvent.ACTION_DOWN)
			getActivity().updateData();
		return super.onTouchEvent(event);
	}
	
	public View getView() {
		return this;
	}

	public BaseActivity getActivity() {
		return activity;
	}

	public ControlConfig getControlConfig() {
		return config;
	}

	
	
	/*Supers*/	
	
	public void setX(int x) {
		super.setX(x);
	}

	public void setY(int y) {
		super.setY(y);
	}
	
	public void setTranslationX(int x){
		super.setTranslationX(x);
	}

	public void setTanslationY(int y){
		super.setTranslationY(y);
	}

	public void setLayoutParams(LayoutParams params) {
		super.setLayoutParams(params);
	}

	public void setText(String title) {
		super.setText(title);
	}
	
	@Override
	public void setPadding(int left, int top, int right, int bottom) {
		super.setPadding(left, top, right, bottom);
	}
	
	public String getLayoutType() {
		return config.getLayoutType();
	}
	
	public IView getViewParent() {
		return parent;
	}

}
