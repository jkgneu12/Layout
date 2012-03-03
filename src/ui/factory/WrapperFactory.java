package ui.factory;

import java.util.ArrayList;
import java.util.HashMap;

import ui.activity.BaseActivity;
import ui.wrapper.ButtonWrapper;
import ui.wrapper.ContainerWrapper;
import ui.wrapper.TabButtonWrapper;
import ui.wrapper.TabWrapper;
import ui.wrapper.TextWrapper;
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
			Wrapper wrapper = createWrapper(activity, parent, innerConfig);
			initWrapper(wrapper, innerConfig);
			wrappers.add(wrapper);
		}

        return wrappers;
	}


	public Wrapper createAndInitWrapper(BaseActivity activity, ContainerWrapper parent, int id) {
		Config config = activity.getConfigStore().getConfig(id);
		Wrapper wrapper = createWrapper(activity, parent, config);
		initWrapper(wrapper, config);
		return wrapper;
	}
	
	private Wrapper createWrapper(BaseActivity context, ContainerWrapper parent, Config config) {
		int id = config.id;
		switch (config.type) {
			case BUTTON: return new ButtonWrapper(context, parent, id);			
			case TEXT: return new TextWrapper(context, parent, id);	
			case CONTAINER: 
				return new ContainerWrapper(context, parent, id);
			case TAB: 
				return new TabWrapper(context, parent, id);
			case TABBUTTON: 
				return new TabButtonWrapper(context, parent, id);

			default:return null;
		}
	}
	
	private void initWrapper(Wrapper c, Config config) {
		c.setTitle(config.title);
		c.setId(config.id);
		c.setType(config.type);
		c.setWidth(config.width);
		c.setHeight(config.height);
		c.setPaddingLeft(config.paddingLeft);
		c.setPaddingTop(config.paddingTop);
		c.setPaddingRight(config.paddingRight);
		c.setPaddingBottom(config.paddingBottom);
		c.setMarginLeft(config.marginLeft);
		c.setMarginTop(config.marginTop);
		c.setMarginRight(config.marginRight);
		c.setMarginBottom(config.marginBottom);
		c.setScreenAlignment(config.screenAlignment);
		c.setInnerAlignment(config.innerAlignment);
		c.setBackgroundColor(config.backgroundColor);
		c.setTargetWrapperIds(config.targetWrapperIds);
		c.setNavigationId(config.navigationId);
		c.setLayoutType(config.layoutType);
		
		c.createLayoutAndAddWrappers();
	}

	

}
