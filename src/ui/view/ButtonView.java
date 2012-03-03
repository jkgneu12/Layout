package ui.view;

import ui.activity.BaseActivity;
import ui.wrapper.ButtonWrapper;
import ui.wrapper.Wrapper;
import android.widget.Button;


/**
 * 
 * Extension of the Button 
 * 
 * Controlled by ButtonWrapper
 *
 */
public class ButtonView extends Button {

	private ButtonWrapper wrapper;
	
	public ButtonView(BaseActivity activity, Wrapper wrapper) {
		super(activity);
		this.setWrapper((ButtonWrapper)wrapper);
		setOnClickListener((ButtonWrapper)wrapper);
	}

	public void setWrapper(ButtonWrapper wrapper) {
		this.wrapper = wrapper;
	}

	public ButtonWrapper getWrapper() {
		return wrapper;
	}
}
