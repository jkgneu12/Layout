package ui.wrapper;

import navigation.ScreenFlowManager;
import ui.activity.BaseActivity;
import ui.view.ButtonView;
import android.view.View;
import android.view.View.OnClickListener;

/**
 * Wrapper for a {@link ButtonView}
 * 
 * Controls logic specific to ButtonView(s)
 */

public class ButtonWrapper extends Wrapper implements OnClickListener {

	public ButtonWrapper(BaseActivity activity, ContainerWrapper parent, int id) {
		super(activity, parent, id);
	}

	@Override
	public void createLayoutAndAddWrappers() {
		view = new ButtonView(activity, this);
		setText(getTitle());
		view.setPadding(paddingLeft, paddingTop, paddingRight, paddingBottom);
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
		if(navigationId != INVALID)
			ScreenFlowManager.navigateToNextScreen(activity, getNavigationId());
		else
			ScreenFlowManager.navigateToPreviousScreen(activity);
			//getActivity().updateData();
	}

	

}
