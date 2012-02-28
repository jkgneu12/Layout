package ui.activity;

import java.util.ArrayList;

import ui.view.wrapper.BaseContainer;
import ui.view.wrapper.ScreenContainer;
import ui.view.wrapper.ViewWrapper;
import android.app.Activity;
import android.graphics.Color;
import android.os.Bundle;
import config.ConfigStore;

public class BaseActivity extends Activity {
	
	private BaseContainer baseView;
	
	private ConfigStore configStore;

	private ArrayList<ScreenContainer> screenContainers;
	
	
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
		
		configStore = new ConfigStore(this);
		
		screenContainers = new ArrayList<ScreenContainer>();
		
		baseView = new BaseContainer(this, null, ViewWrapper.INVALID);
        
        baseView.createLayoutAndAddControls();
        
        baseView.finishLayoutControls();
        
        
    }
    
    @Override
    protected void onResume() {
    	super.onResume();
    	for(ScreenContainer sc : screenContainers)
    		sc.initFragment();
    }

    public void updateData(){
    	getBaseView().updateData();
    }
    
    
	public ConfigStore getConfigStore() {
		return configStore;
	}

	public BaseContainer getBaseView() {
		return baseView;
	}

	
	public int getScreenWidth() {
		return getWindowManager().getDefaultDisplay().getWidth();
	}
	
	public int getScreenHeight() {
		return getWindowManager().getDefaultDisplay().getHeight();
	}

	public void addScreenContainer(ScreenContainer screenContainer) {
		screenContainers.add(screenContainer);
	}

	
	
}