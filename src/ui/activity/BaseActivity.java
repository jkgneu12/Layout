package ui.activity;

import ui.view.wrapper.ScreenContainer;
import ui.view.wrapper.ViewWrapper;
import android.app.Activity;
import android.graphics.Color;
import android.os.Bundle;
import config.ConfigStore;

public class BaseActivity extends Activity {
	
	ScreenContainer baseView;
	
	ConfigStore configStore;
	
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        int width = getScreenWidth();
		int height = getScreenHeight();
		
		configStore = new ConfigStore(this);
        
        baseView = new ScreenContainer(this, null, ViewWrapper.INVALID);
        
        baseView.setScreenId(1000);
        
        baseView.setWidth(width);
        baseView.setHeight(height);
        
        baseView.setBackgroundColor(Color.GRAY);
        
        baseView.init();
        
        setContentView(baseView.getLayout());
    }

    public void updateData(){
    	baseView.updateData();
    }
    
    
	public ConfigStore getConfigStore() {
		return configStore;
	}

	public ScreenContainer getBaseView() {
		return baseView;
	}

	
	public int getScreenWidth() {
		return getWindowManager().getDefaultDisplay().getWidth();
	}
	
	public int getScreenHeight() {
		return getWindowManager().getDefaultDisplay().getHeight();
	}

	
	
}