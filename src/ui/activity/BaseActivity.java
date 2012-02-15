package ui.activity;

import ui.view.BaseView;
import android.app.Activity;
import android.os.Bundle;
import config.ConfigStore;

public class BaseActivity extends Activity {
	
	BaseView baseView;
	
	ConfigStore configStore;
	
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        int width = getScreenWidth();
		int height = getScreenHeight();
		
		configStore = new ConfigStore(this);
        
        baseView = new BaseView(this, 1000, width, height);
        
        baseView.init();
        
        setContentView(baseView.getLayout());
    }

    public void updateData(){
    	baseView.updateData();
    }
    
    
	public ConfigStore getConfigStore() {
		return configStore;
	}

	public BaseView getBaseView() {
		return baseView;
	}

	
	public int getScreenWidth() {
		return getWindowManager().getDefaultDisplay().getWidth();
	}
	
	public int getScreenHeight() {
		return getWindowManager().getDefaultDisplay().getHeight();
	}

	
	
}