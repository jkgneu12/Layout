package ui.factory;

import java.util.ArrayList;
import java.util.HashMap;

import ui.activity.BaseActivity;
import ui.wrapper.ContainerWrapper;
import ui.wrapper.Wrapper;
import config.Config;

public class WrapperFactory {

	public WrapperFactory(){}

	public ArrayList<Wrapper> createWrapperForId(BaseActivity activity, ContainerWrapper parent, int id) {
		Config config = activity.getConfigStore().getConfig(id);
		
		ArrayList<Integer> childWrapperIds = config.childWrapperIds;
		
		HashMap<Integer, Config> configs = activity.getConfigStore().getConfigs(childWrapperIds);
		
		ArrayList<Wrapper> wrappers = new ArrayList<Wrapper>();
		
		for(int childWrapperId : childWrapperIds){
			Config innerConfig = configs.get(childWrapperId);
			wrappers.add(createWrapper(activity, parent, innerConfig));
		}

        return wrappers;
	}


	public Wrapper createAndInitWrapper(BaseActivity activity, ContainerWrapper parent, int id) {
		Config config = activity.getConfigStore().getConfig(id);
		return createWrapper(activity, parent, config);
	}
	
	private Wrapper createWrapper(BaseActivity activity, ContainerWrapper parent, Config config) {
		Wrapper wrapper = config.type.getWrapper(activity, parent, config);
		wrapper.createAndLayoutAndAddWrappers();
		return wrapper;
	}

	

}
