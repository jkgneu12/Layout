package ui.view;

import ui.view.wrapper.ViewWrapper;
import android.content.Context;
import android.view.MotionEvent;
import android.widget.Button;

public class ButtonControl extends Button {

	private ViewWrapper wrapper;
	
	public ButtonControl(Context context, ViewWrapper wrapper) {
		super(context);
		this.wrapper = wrapper;
	}
	
	@Override
	public boolean onTouchEvent(MotionEvent event) {
		if(event.getAction() == MotionEvent.ACTION_DOWN)
			wrapper.getActivity().updateData();
		return super.onTouchEvent(event);
	}
}
