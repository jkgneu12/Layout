package navigation;

import android.content.Intent;
import ui.activity.BaseActivity;

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
