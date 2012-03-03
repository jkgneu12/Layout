package ui.view;

import ui.activity.BaseActivity;
import ui.wrapper.Wrapper;
import android.widget.TextView;

public class CustomTextView extends TextView {
	
	private Wrapper wrapper;

	public CustomTextView(BaseActivity activity, Wrapper wrapper) {
		super(activity);
		this.wrapper = wrapper;
	}
}
