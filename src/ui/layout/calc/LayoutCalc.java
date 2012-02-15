package ui.layout.calc;

import java.util.ArrayList;

import ui.activity.BaseActivity;
import ui.view.IView;
import ui.view.control.IControl;
import android.view.View.MeasureSpec;
import config.ControlConfig;

public abstract class LayoutCalc {

	protected ArrayList<IControl> controls;

	protected int parentWidth;
	protected int parentHeight;

	public LayoutCalc(ArrayList<IControl> controls, int parentWidth, int parentHeight) {
		this.controls = controls;
		this.parentWidth = parentWidth;
		this.parentHeight = parentHeight;
	}
	
	
	public abstract void layoutControls();

	public static int getMeasuredWidth(IControl c){
		int width = c.getControlConfig().getWidth();
		if(width == ControlConfig.INVALID){
			c.getView().measure(MeasureSpec.UNSPECIFIED, MeasureSpec.UNSPECIFIED);
			width = c.getView().getMeasuredWidth();
		}
		return width;	
	}

	public static int getMeasuredHeight(IControl c){
		int height = c.getControlConfig().getHeight();
		if(height == ControlConfig.INVALID){
			c.getView().measure(MeasureSpec.UNSPECIFIED, MeasureSpec.UNSPECIFIED);
			height = c.getView().getMeasuredHeight();
		}
		return height;	
	}
	
	public static int getMaxWidth(IView view, BaseActivity activity) {
		IView parent = view.getViewParent();
		if(parent != null){
			if(parent instanceof IControl){
				int configWidth = ((IControl)parent).getControlConfig().getWidth();
			
				if(configWidth != ControlConfig.INVALID)
					return configWidth;
				else 
					return getMaxWidth(parent, activity);
			}
		}
		return activity.getScreenWidth();
	}
	
	public static int getMaxHeight(IView view, BaseActivity activity) {
		IView parent = view.getViewParent();
		if(parent != null){
			if(parent instanceof IControl){
				int configHeight = ((IControl)parent).getControlConfig().getHeight();
			
				if(configHeight != ControlConfig.INVALID)
					return configHeight;
				else 
					return getMaxHeight(parent, activity);
			}
		}
		return activity.getScreenHeight();
	}
	
	protected abstract int calculateLeftSpacing(IControl c, int space);
	
	protected abstract int calculateRightSpacing(IControl c, int space);
}
