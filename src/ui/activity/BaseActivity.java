package ui.activity;

import java.util.ArrayList;
import java.util.HashMap;

import ui.factory.WrapperFactory;
import ui.fragment.BaseFragment;
import ui.wrapper.ContainerWrapper;
import ui.wrapper.FragmentContainerWrapper;
import ui.wrapper.Wrapper;
import android.app.Activity;
import android.content.res.Configuration;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import config.ConfigStore;

/**
 * This is our {@link Activity} for most screens. 
 * 
 * {@link BaseFragment}s will handle logic for different view types (list, form, find...). 
 * Each {@link ContainerWrapper} houses a {@link BaseFragment}
 *
 */
public class BaseActivity extends FragmentActivity {
	
	private FragmentContainerWrapper baseContainerWrapper;
	
	private ConfigStore configStore;

	private ArrayList<FragmentContainerWrapper> containerWrappers;
	
	private HashMap<Integer, Wrapper<?>> wrappers;
	
	
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        
        containerWrappers = new ArrayList<FragmentContainerWrapper>();
        wrappers = new HashMap<Integer, Wrapper<?>>();
        
        configStore = new ConfigStore(this);
		
		initBaseContainerWrapper(getIntent().getIntExtra("baseId", 1000));
		
		setTitle(baseContainerWrapper.getConfig().title);
    }
    
	protected void initBaseContainerWrapper(int screenId) {
		baseContainerWrapper = (FragmentContainerWrapper)new WrapperFactory().buildWrapper(this, null, screenId);
	}
    
    @Override
    protected void onResume() {
    	super.onResume();
    	baseContainerWrapper.initFragments();
    }

    /**
     * Creates a Fragment in each ContainerWrapper
     */
	
	
	public synchronized boolean replaceFragment(FragmentContainerWrapper newWrapper, FragmentContainerWrapper oldWrapper) {
		if(oldWrapper == newWrapper)
			return false;
		oldWrapper.hideFragment();
		return newWrapper.initFragment();
	}

	

    public void updateData(HashMap<String, Object> data){
    	baseContainerWrapper.updateData(data);
    }
    
    public Wrapper<?> getWrapperById(int id){
    	return wrappers.get(id);
    }
    
    
    public void addWrapper(int id, Wrapper<?> wrapper) {
    	wrappers.put(id, wrapper);
	}
    
    /**
     * Add a ContainerWrapper to the Activity. ContainerWrapper hold Fragments and we need to
     * be able to manage them from the Activity.
     */
    public void addContainerWrapper(FragmentContainerWrapper containerWrapper) {
		containerWrappers.add(containerWrapper);
	}
    
    @Override
	public void onConfigurationChanged(Configuration newConfig) {
		super.onConfigurationChanged(newConfig);
		relayout(true);
	}

	public void relayout(boolean reset) {
		baseContainerWrapper.relayout(reset);
		
	}
    
	public ConfigStore getConfigStore() {
		return configStore;
	}

	/**
	 * Get the full width of the device.
	 */
	public int getScreenWidth() {
		return getWindowManager().getDefaultDisplay().getWidth(); 
	}
	
	/**
	 * Get the full height of the device.
	 */
	public int getScreenHeight() {
		int h = getWindowManager().getDefaultDisplay().getHeight(); 
		//if(getActionBar() != null)
		//	h -= getActionBar().getHeight();
		return h - 200;
	}

}