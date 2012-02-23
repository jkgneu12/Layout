package config;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashMap;

import android.content.Context;
import android.graphics.Color;

import com.verivo.R;

public class ConfigStore {
	
	private HashMap<Integer, ControlConfig> controls;
	private HashMap<Integer, ScreenConfig> screens;
	
	public ConfigStore(Context context) {
		controls = new  HashMap<Integer, ControlConfig>();
		screens = new  HashMap<Integer, ScreenConfig>();
		
		parseConfig(context);
	}
	
	public void parseConfig(Context context){
		try {
			
			InputStream is = context.getResources().openRawResource(R.raw.basicregion);
			BufferedReader in = new BufferedReader(new InputStreamReader(is));
			String line = in.readLine();
			while(line != null && line.trim().length() > 0){
				line = line.trim();
				
				if(isScreen(line)){
					
					ScreenConfig s = new ScreenConfig();
					
					line = in.readLine();
					
					while (line != null && line.trim().length() > 0) {
						line = line.trim();
						
						String[] parts = line.split(":");
						
						setScreenProperty(s, parts[0], parts[1]);
						
						line = in.readLine();
					}
					
					screens.put(s.getId(), s);
					
				} else {
				
					ControlConfig c = new ControlConfig();
					
					c.setType(line);
					
					line = in.readLine();
					
					while (line != null && line.trim().length() > 0) {
						line = line.trim();
						
						String[] parts = line.split(":");
						
						setControlProperty(c, parts[0], parts[1]);
						
						line = in.readLine();
					}
					
					controls.put(c.getId(), c);
				}
				line = in.readLine();
			}
			in.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}


	private boolean isScreen(String line) {
		return line.equals("screen");
	}
	
	private void setScreenProperty(ScreenConfig s, String property, String value) {
		if(property.equals("controls")) s.setControlIds(createControls(value));
		else if(property.equals("id")) s.setId(Integer.parseInt(value));
	}

	private ArrayList<Integer> createControls(String value) {
		ArrayList<Integer> controlIds = new ArrayList<Integer>();
		
		String[] parts = value.split(",");
		for(String screenId : parts){
			controlIds.add(Integer.parseInt(screenId));
		}
		
		return controlIds;
	}

	private void setControlProperty(ControlConfig c, String property, String value) {
		if(property.equals("height")) c.setHeight(Integer.parseInt(value));
		else if(property.equals("width")) c.setWidth(Integer.parseInt(value));
		else if(property.equals("screen-alignment")) c.setScreenAlignment(value);
		else if(property.equals("inner-alignment")) c.setInnerAlignment(value);
		else if(property.equals("background-color")) c.setBackgroundColor(Color.parseColor(value));
		else if(property.equals("padding-left")) c.setPaddingLeft(Integer.parseInt(value));
		else if(property.equals("padding-top")) c.setPaddingTop(Integer.parseInt(value));
		else if(property.equals("padding-bottom")) c.setPaddingBottom(Integer.parseInt(value));
		else if(property.equals("padding-right")) c.setPaddingRight(Integer.parseInt(value));
		else if(property.equals("margin-left")) c.setMarginLeft(Integer.parseInt(value));
		else if(property.equals("margin-top")) c.setMarginTop(Integer.parseInt(value));
		else if(property.equals("margin-bottom")) c.setMarginBottom(Integer.parseInt(value));
		else if(property.equals("margin-right")) c.setMarginRight(Integer.parseInt(value));
		else if(property.equals("title")) c.setTitle(value);
		else if(property.equals("id")) c.setId(Integer.parseInt(value));
		else if(property.equals("layout")) c.setLayoutType(value);
		else if(property.equals("target-screen")) c.setTargetScreenId(Integer.parseInt(value));
		else if(property.equals("controls")) c.setControlIds(createControls(value));
	}
	
	public ControlConfig getControlConfig(int controlId){
		return controls.get(controlId);
	}

	public ScreenConfig getScreenConfig(int controlId){
		return screens.get(controlId);
	}

	public HashMap<Integer, ControlConfig> getControls() {
		return controls;
	}

	public HashMap<Integer, ControlConfig> getControlConfigs(ArrayList<Integer> controlIds) {
		HashMap<Integer, ControlConfig> controlConfigs = new HashMap<Integer, ControlConfig>();
		
		for(Integer id : controlIds){
			controlConfigs.put(id, controls.get(id));
		}
		
		return controlConfigs;
	}
}
