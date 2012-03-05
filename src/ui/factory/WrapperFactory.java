package ui.factory;

import java.util.ArrayList;
import java.util.HashMap;

import ui.activity.BaseActivity;
import ui.wrapper.ContainerWrapper;
import ui.wrapper.Wrapper;
import config.ViewConfig;

public class WrapperFactory {

	public static final int INVALID = -1000;
	
	public WrapperFactory(){}

	public ArrayList<Wrapper> createChildWrappersForId(BaseActivity activity, ContainerWrapper parent, int id) {
		ViewConfig config = activity.getConfigStore().getConfig(id);
		
		ArrayList<Integer> childWrapperIds = config.childWrapperIds;
		
		HashMap<Integer, ViewConfig> configs = activity.getConfigStore().getConfigs(childWrapperIds);
		
		ArrayList<Wrapper> wrappers = new ArrayList<Wrapper>();
		
		for(int childWrapperId : childWrapperIds){
			ViewConfig innerConfig = configs.get(childWrapperId);
			wrappers.add(createWrapper(activity, parent, innerConfig));
		}

        return wrappers;
	}


	public Wrapper createAndInitWrapper(BaseActivity activity, ContainerWrapper parent, int id) {
		ViewConfig config = activity.getConfigStore().getConfig(id);
		return createWrapper(activity, parent, config);
	}
	
	private Wrapper createWrapper(BaseActivity activity, ContainerWrapper parent, ViewConfig config) {
		Wrapper wrapper = config.type.getWrapper(activity, parent, config);
		wrapper.createAndLayoutAndAddWrappers(); 
		return wrapper;
	}

	

}
