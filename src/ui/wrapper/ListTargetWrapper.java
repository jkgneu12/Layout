package ui.wrapper;

import ui.activity.BaseActivity;
import config.WrapperConfig;

public /*abstract*/ class ListTargetWrapper extends FragmentContainerWrapper {
	
	private ListWrapper list;
	private FragmentContainerWrapper target;
	

	public ListTargetWrapper(BaseActivity activity, ContainerWrapper parent, WrapperConfig config) {
		super(activity, parent, config);
	}

}
