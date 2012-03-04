package navigation;

import ui.activity.BaseActivity;
import android.content.Intent;

public class ScreenFlowManager {

	public static void navigateToNextScreen(BaseActivity currentActivity, int id){
		Intent intent = new Intent(currentActivity, BaseActivity.class);
		intent.putExtra("baseId", id);
		currentActivity.startActivity(intent);
	}
	
	public static void navigateToPreviousScreen(BaseActivity currentActivity){
		currentActivity.finish();
	}
}
