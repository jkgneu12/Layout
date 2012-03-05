package ui.wrapper;

import ui.activity.BaseActivity;
import ui.view.CustomTextView;
import android.content.res.ColorStateList;
import config.ViewConfig;

public class TextWrapper extends Wrapper {

	public TextWrapper(BaseActivity activity, ContainerWrapper parent, ViewConfig config) {
		super(activity, parent, config);
	}
	
	@Override
	public void createAndLayoutAndAddWrappers() {
		view = new CustomTextView(activity, this);
		getTextView().setTextColor(new ColorStateList(
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
		super.createAndLayoutAndAddWrappers();
	}
	
	public void finializeWrappers(){}

	@Override
	public void updateData(Object data) {
		setText((String)data);
	}
	
	private CustomTextView getTextView(){
		return (CustomTextView)getView();
	}

	@Override
	public void setText(String text) {
		getTextView().setText(text);
		
	}

}
