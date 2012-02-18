package ui.view.wrapper;

import ui.view.TextControl;
import android.content.Context;

public class TextWrapper extends ViewWrapper {

	public TextWrapper(Context context, ViewWrapper parent, int id) {
		super(context, parent, id);
	}
	
	@Override
	public void init() {
		setView(new TextControl(activity, this));
	}

	@Override
	public void updateData() {
		setText("456");
	}
	
	private TextControl getTextView(){
		return (TextControl)getView();
	}

	@Override
	public void setText(String text) {
		getTextView().setText(text);
		
	}

}
