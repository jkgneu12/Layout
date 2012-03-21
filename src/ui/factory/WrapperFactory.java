package ui.factory;

import java.util.ArrayList;
import java.util.HashMap;

import ui.activity.BaseActivity;
import ui.wrapper.ContainerWrapper;
import ui.wrapper.Wrapper;
import config.WrapperConfig;

public class WrapperFactory {

	public static final int INVALID = -1000;
	
	public WrapperFactory(){}

	public ArrayList<Wrapper> createChildWrappersForId(BaseActivity activity, ContainerWrapper parent, int id) {
		WrapperConfig config = activity.getConfigStore().getConfig(id);
		
		ArrayList<Integer> childWrapperIds = config.childWrapperIds;
		
		HashMap<Integer, WrapperConfig> configs = activity.getConfigStore().getConfigs(childWrapperIds);
		
		ArrayList<Wrapper> wrappers = new ArrayList<Wrapper>();
		
		for(int childWrapperId : childWrapperIds){
			WrapperConfig innerConfig = configs.get(childWrapperId);
			wrappers.add(initializeAndCreateWrapper(activity, parent, innerConfig));
		}

        return wrappers;
	}


	public Wrapper buildWrapper(BaseActivity activity, ContainerWrapper parent, int id) {
		WrapperConfig config = activity.getConfigStore().getConfig(id);
		
		Wrapper wrapper = initializeAndCreateWrapper(activity, parent, config);
		wrapper.layoutWrapper();
		wrapper.addChildViews();
		
		return wrapper;
	}
	
	private Wrapper initializeAndCreateWrapper(BaseActivity activity, ContainerWrapper parent, WrapperConfig config) {
		return config.type.initializeWrapper(activity, parent, config).createWrapper();
	}

	

}
