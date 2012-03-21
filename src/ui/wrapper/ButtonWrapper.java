package ui.wrapper;

import java.util.HashMap;

import navigation.ScreenFlowManager;
import ui.activity.BaseActivity;
import ui.view.ButtonView;
import android.content.res.ColorStateList;
import android.view.View;
import android.view.View.OnClickListener;
import config.WrapperConfig;

/**
 * Wrapper for a {@link ButtonView}
 * 
 * Controls logic specific to ButtonView(s)
 */

public class ButtonWrapper extends Wrapper implements OnClickListener {

	public ButtonWrapper(BaseActivity activity, ContainerWrapper parent, WrapperConfig config) {
		super(activity, parent, config);
	}

	@Override
	public Wrapper createWrapper() {
		view = new ButtonView(activity, this);
		getButtonView().setTextColor(new ColorStateList(
				new int[][] {
						new int[] { android.R.attr.state_pressed},
						new int[] { android.R.attr.state_selected},
						
						new int[] {android.R.attr.state_focused},
						new int[] {}
				}, 
				new int[] {
						styleConfig.pressedTextColor,
						styleConfig.selectedTextColor,
						styleConfig.focusedTextColor,
						styleConfig.defaultTextColor
				})
		);
		getButtonView().setGravity(config.innerAlignment.value());
		return super.createWrapper();
	}
	
	@Override
	public void layoutWrapper() {}
	
	public void finializeWrappers(){}
	
	@Override
	public void updateData(Object data) {
		setText((String)data);
	}

	public ButtonView getButtonView() {
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
			//ScreenFlowManager.navigateToPreviousScreen(activity);
			getActivity().updateData(new HashMap<String,Object>());
	}

	

}
