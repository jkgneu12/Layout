package ui.view;

import ui.activity.BaseActivity;
import ui.wrapper.TabWrapper;
import ui.wrapper.Wrapper;

public class TabButtonView extends ButtonView {

	private TabWrapper tabWrapper;
	
	public TabButtonView(BaseActivity context, Wrapper wrapper, TabWrapper tabWrapper) {
		super(context, wrapper);
		this.tabWrapper = tabWrapper;
	}

	public TabWrapper getTabWrapper() {
		return tabWrapper;
	}
}
