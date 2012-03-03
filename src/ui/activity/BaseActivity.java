package ui.activity;

import java.util.ArrayList;
import java.util.HashMap;

import ui.fragment.BaseFragment;
import ui.wrapper.BaseContainerWrapper;
import ui.wrapper.ContainerWrapper;
import ui.wrapper.Wrapper;
import android.app.Activity;
import android.app.FragmentTransaction;
import android.os.Bundle;

import com.verivo.R;

import config.ConfigStore;

/**
 * This is our {@link Activity} for most screens. 
 * 
 * {@link BaseFragment}s will handle logic for different view types (list, form, find...). 
 * Each {@link ContainerWrapper} houses a {@link BaseFragment}
 *
 */
public class BaseActivity extends Activity {
	
	private BaseContainerWrapper baseContainerWrapper;
	
	private ConfigStore configStore;

	private ArrayList<ContainerWrapper> containerWrappers;
	
	private HashMap<Integer, Wrapper> wrappers;
	
	
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        
        containerWrappers = new ArrayList<ContainerWrapper>();
        wrappers = new HashMap<Integer, Wrapper>();
        
        configStore = new ConfigStore(this);
		
		initBaseContainerWrapper();
    }

	protected void initBaseContainerWrapper() {
		baseContainerWrapper = new BaseContainerWrapper(this, null, 1000);
        
        baseContainerWrapper.createLayoutAndAddWrappers();
        
        baseContainerWrapper.finishLayoutWrappers();
        
        baseContainerWrapper.finializeWrappers();
	}
    
    @Override
    protected void onResume() {
    	super.onResume();
    	initFragments();
    }

    /**
     * Creates a Fragment in each ContainerWrapper
     */
	public void initFragments() {
		for(ContainerWrapper sc : containerWrappers)
    		sc.initFragment(false);
	}
	
	public void replaceFragment(ContainerWrapper newWrapper, ContainerWrapper oldWrapper) {
		if(oldWrapper.getFragment() != null){
			FragmentTransaction trans = getFragmentManager().beginTransaction();
			//trans.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_CLOSE);
			trans.setCustomAnimations(R.animator.slide_in, R.animator.slide_out);
			trans.remove(oldWrapper.getFragment());
			trans.commit();
		}
		newWrapper.initFragment(true);
	}

    public void updateData(){
    	getBaseContainerWrapper().updateData();
    }
    
    public Wrapper getWrapperById(int id){
    	return wrappers.get(id);
    }
    
    
    public void addWrapper(int id, Wrapper wrapper) {
    	wrappers.put(id, wrapper);
	}
    
    /**
     * Add a ContainerWrapper to the Activity. ContainerWrapper hold Fragments and we need to
     * be able to manage them from the Activity.
     */
    public void addContainerWrapper(ContainerWrapper containerWrapper) {
		containerWrappers.add(containerWrapper);
	}
    
    
	public ConfigStore getConfigStore() {
		return configStore;
	}

	public BaseContainerWrapper getBaseContainerWrapper() {
		return baseContainerWrapper;
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
		return getWindowManager().getDefaultDisplay().getHeight();
	}

	public void relayout() {
		baseContainerWrapper.layoutWrappers();
	}

}