package ui.wrapper;

import ui.activity.BaseActivity;
import ui.view.CustomTextView;
import config.Config;

public class TextWrapper extends Wrapper {

	public TextWrapper(BaseActivity activity, ContainerWrapper parent, Config config) {
		super(activity, parent, config);
	}
	
	@Override
	public void createAndLayoutAndAddWrappers() {
		view = new CustomTextView(activity, this);
		getTextView().setTextColor(config.textColor);
		super.createAndLayoutAndAddWrappers();
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
