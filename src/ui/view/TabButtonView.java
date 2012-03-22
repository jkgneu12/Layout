package ui.view;

import ui.activity.BaseActivity;
import ui.wrapper.TabButtonWrapper;
import ui.wrapper.TabWrapper;

public class TabButtonView extends ButtonView {

	private TabWrapper tabWrapper;
	
	public TabButtonView(BaseActivity context, TabButtonWrapper wrapper, TabWrapper tabWrapper) {
		super(context, wrapper);
		this.tabWrapper = tabWrapper;
	}

	public TabWrapper getTabWrapper() {
		return tabWrapper;
	}
}
