package ui.view.wrapper;

import ui.view.ButtonControl;
import android.content.Context;

public class ButtonWrapper extends ViewWrapper {

	public ButtonWrapper(Context context, ViewWrapper parent, int id) {
		super(context, parent, id);
	}

	@Override
	public void init() {
		view = new ButtonControl(activity, this);
		setText(getTitle());
		view.setPadding(paddingLeft, paddingTop, paddingRight, paddingBottom);
		super.init();
	}
	
	@Override
	public void updateData() {
		setText("123");
	}

	private ButtonControl getButtonView() {
		return (ButtonControl)getView();
	}

	@Override
	public void setText(String text) {
		getButtonView().setText(text);
	}

	

}
