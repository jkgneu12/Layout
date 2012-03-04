package ui.wrapper;

import navigation.ScreenFlowManager;
import ui.activity.BaseActivity;
import ui.view.ButtonView;
import android.view.View;
import android.view.View.OnClickListener;
import config.Config;

/**
 * Wrapper for a {@link ButtonView}
 * 
 * Controls logic specific to ButtonView(s)
 */

public class ButtonWrapper extends Wrapper implements OnClickListener {

	public ButtonWrapper(BaseActivity activity, ContainerWrapper parent, Config config) {
		super(activity, parent, config);
	}

	@Override
	public void createLayoutAndAddWrappers() {
		view = new ButtonView(activity, this);
		getButtonView().setTextColor(config.textColor);
		super.createLayoutAndAddWrappers();
	}
	
	public void finializeWrappers(){}
	
	@Override
	public void updateData() {
		setText("123");
	}

	private ButtonView getButtonView() {
		return (ButtonView)getView();
	}

	@Override
	public void setText(String text) {
		getButtonView().setText(text);
	}

	public void onClick(View v) {
		if(config.navigationId != INVALID)
			ScreenFlowManager.navigateToNextScreen(activity, config.navigationId);
		else
			ScreenFlowManager.navigateToPreviousScreen(activity);
			//getActivity().updateData();
	}

	

}
