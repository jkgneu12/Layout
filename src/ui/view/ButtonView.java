package ui.view;

import ui.activity.BaseActivity;
import ui.wrapper.ButtonWrapper;
import android.graphics.Canvas;
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
	
	public ButtonView(BaseActivity activity, ButtonWrapper wrapper) {
		super(activity);
		this.setWrapper(wrapper);
		setOnClickListener(wrapper);
	}
	
	public void setWrapper(ButtonWrapper wrapper) {
		this.wrapper = wrapper;
	}

	public ButtonWrapper getWrapper() {
		return wrapper;
	}
	
	@Override
	protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
		// TODO Auto-generated method stub
		super.onMeasure(widthMeasureSpec, heightMeasureSpec);
	}
	
	@Override
	protected void onLayout(boolean changed, int left, int top, int right,
			int bottom) {
		// TODO Auto-generated method stub
		super.onLayout(changed, left, top, right, bottom);
	}
	@Override
	public void draw(Canvas canvas) {
		// TODO Auto-generated method stub
		super.draw(canvas);
	}
}
