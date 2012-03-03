package ui.wrapper;

import ui.activity.BaseActivity;
import ui.view.CustomTextView;

public class TextWrapper extends Wrapper {

	public TextWrapper(BaseActivity activity, ContainerWrapper parent, int id) {
		super(activity, parent, id);
	}
	
	@Override
	public void createLayoutAndAddWrappers() {
		setView(new CustomTextView(activity, this));
	}
	
	public void finializeWrappers(){}

	@Override
	public void updateData() {
		setText("456");
	}
	
	private CustomTextView getTextView(){
		return (CustomTextView)getView();
	}

	@Override
	public void setText(String text) {
		getTextView().setText(text);
		
	}

}
