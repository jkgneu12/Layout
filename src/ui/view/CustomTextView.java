package ui.view;

import ui.activity.BaseActivity;
import ui.wrapper.TextWrapper;
import android.widget.TextView;

public class CustomTextView extends TextView {
	
	private TextWrapper wrapper;

	public CustomTextView(BaseActivity activity, TextWrapper wrapper) {
		super(activity);
		this.wrapper = wrapper;
	}
}
