package ui.view.control;

import ui.activity.BaseActivity;
import ui.view.IView;
import android.content.Context;
import android.view.View;
import android.widget.FrameLayout.LayoutParams;
import android.widget.TextView;
import config.ControlConfig;

public class TextControl extends TextView implements IControl {

	private BaseActivity activity;
	private ControlConfig config;
	
	private IView parent;
	
	public TextControl(Context context, IView parent, ControlConfig config) {
		super(context);
		this.activity = (BaseActivity)context;
		this.parent = parent;
		this.config = config;
	}
	
	public void updateData(){
		setText("123");
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

	public String getLayoutType() {
		return config.getLayoutType();
	}

	public IView getViewParent() {
		return parent;
	}

}
