package ui.view.control;

import ui.activity.BaseActivity;
import ui.view.IView;
import android.widget.FrameLayout.LayoutParams;
import config.ControlConfig;

public interface IControl extends IView {
	
	/**
	 * Return self as a native View
	 * @return
	 */
	
	public void updateData();
	
	public BaseActivity getActivity();
	
	public ControlConfig getControlConfig();
	
	public void setX(int x);

	public void setY(int y);
	
	public void setTranslationX(int x);

	public void setTanslationY(int y);

	public void setLayoutParams(LayoutParams params);

	public int getId();

	public void setId(int id);

	public void setBackgroundColor(int backgroundColor);

	public void setText(String title);

	public void setGravity(int value);

	public void setPadding(int paddingLeft, int paddingTop, int paddingRight, int paddingBottom);

	public void invalidate();

	

	
}
