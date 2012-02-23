package ui.layout.calc;

import java.util.ArrayList;

import ui.activity.BaseActivity;
import ui.view.wrapper.ViewWrapper;
import android.view.View.MeasureSpec;
import config.ControlConfig;

public abstract class LayoutCalc {

	protected ArrayList<ViewWrapper> controls;

	protected int parentWidth;
	protected int parentHeight;

	public LayoutCalc(ArrayList<ViewWrapper> controls, int parentWidth, int parentHeight) {
		this.controls = controls;
		this.parentWidth = parentWidth;
		this.parentHeight = parentHeight;
	}
	
	
	public abstract void layoutControls();

	public static int getMeasuredWidth(ViewWrapper c){
		int width = c.getWidth();
		if(width == ControlConfig.INVALID){
			c.getView().measure(MeasureSpec.UNSPECIFIED, MeasureSpec.UNSPECIFIED);
			width = c.getView().getMeasuredWidth();
		}
		return width;	
	}

	public static int getMeasuredHeight(ViewWrapper c){
		int height = c.getHeight();
		if(height == ControlConfig.INVALID){
			c.getView().measure(MeasureSpec.UNSPECIFIED, MeasureSpec.UNSPECIFIED);
			height = c.getView().getMeasuredHeight();
		}
		return height;	
	}
	
	public static int getFullWidth(ViewWrapper control) {
		return getMeasuredWidth(control) + control.getMarginLeft() + control.getMarginRight();
	}

	public static int getFullHeight(ViewWrapper control) {
		return getMeasuredHeight(control) + control.getMarginTop() + control.getMarginBottom();
	}
	
	public static int getMaxWidth(ViewWrapper view, BaseActivity activity) {
		ViewWrapper parent = view.getParent();
		if(parent != null){
			if(parent instanceof ViewWrapper){
				int configWidth = parent.getWidth();
			
				if(configWidth != ControlConfig.INVALID)
					return configWidth;
				else 
					return getMaxWidth(parent, activity);
			}
		}
		return activity.getScreenWidth();
	}
	
	public static int getMaxHeight(ViewWrapper view, BaseActivity activity) {
		ViewWrapper parent = view.getParent();
		if(parent != null){
			if(parent instanceof ViewWrapper){
				int configHeight = parent.getHeight();
			
				if(configHeight != ControlConfig.INVALID)
					return configHeight;
				else 
					return getMaxHeight(parent, activity);
			}
		}
		return activity.getScreenHeight();
	}
	
	protected abstract int calculateLeftSpacing(ViewWrapper c, int space);
	
	protected abstract int calculateRightSpacing(ViewWrapper c, int space);
}
