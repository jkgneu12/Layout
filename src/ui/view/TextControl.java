package ui.view;

import ui.view.wrapper.ViewWrapper;
import android.content.Context;
import android.widget.TextView;

public class TextControl extends TextView {
	
	private ViewWrapper wrapper;

	public TextControl(Context context, ViewWrapper wrapper) {
		super(context);
		this.wrapper = wrapper;
	}
}
