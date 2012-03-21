package ui.wrapper;

import ui.activity.BaseActivity;
import android.view.ViewGroup.LayoutParams;
import config.WrapperConfig;

public class ListRowWrapper extends ContainerWrapper {

	public ListRowWrapper(BaseActivity activity, ContainerWrapper parent, WrapperConfig config) {
		super(activity, parent, config);
	}

	@Override
	public Wrapper createWrapper() {
		super.createWrapper();
		LayoutParams oldParams = view.getLayoutParams();
		view.setLayoutParams(new android.widget.AbsListView.LayoutParams(oldParams.width, oldParams.height));
		return this;
	}
	
	@Override
	public void finializeWrappers() {}

	@Override
	public void finishLayoutWrappers() {}
}
