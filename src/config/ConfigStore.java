package config;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashMap;

import ui.activity.BaseActivity;
import android.graphics.Color;

import com.verivo.R;

/**
 * Creates and holds the Config(s)
 * 
 */
public class ConfigStore {
	
	private HashMap<Integer, Config> configs;
	
	public ConfigStore(BaseActivity activity) {
		configs = new  HashMap<Integer, Config>();
		
		parseConfigFile(activity);
	}
	
	/**
	 * Open the config file and create Config(s)
	 */
	public void parseConfigFile(BaseActivity activity){
		try {
			InputStream is = activity.getResources().openRawResource(R.raw.basicvert);
			BufferedReader in = new BufferedReader(new InputStreamReader(is));
			
			String line = in.readLine();
			while(line != null && line.trim().length() > 0){
				line = line.trim();
				
				Config c = new Config();
				
				c.setType(line);
				
				line = in.readLine();
				
				while (line != null && line.trim().length() > 0) {
					line = line.trim();
					
					String[] parts = line.split(":");
					
					setConfigProperty(c, parts[0], parts[1]);
					
					line = in.readLine();
				}
				
				configs.put(c.getId(), c);
				
				line = in.readLine();
			}
			in.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	/**
	 * Sets the property to a value in a Config
	 */
	private void setConfigProperty(Config config, String property, String value) {
		if(property.equals("height")) config.setHeight(Integer.parseInt(value));
		else if(property.equals("width")) config.setWidth(Integer.parseInt(value));
		else if(property.equals("screen-alignment")) config.setScreenAlignment(value);
		else if(property.equals("inner-alignment")) config.setInnerAlignment(value);
		else if(property.equals("background-color")) config.setBackgroundColor(Color.parseColor(value));
		else if(property.equals("padding-left")) config.setPaddingLeft(Integer.parseInt(value));
		else if(property.equals("padding-top")) config.setPaddingTop(Integer.parseInt(value));
		else if(property.equals("padding-bottom")) config.setPaddingBottom(Integer.parseInt(value));
		else if(property.equals("padding-right")) config.setPaddingRight(Integer.parseInt(value));
		else if(property.equals("margin-left")) config.setMarginLeft(Integer.parseInt(value));
		else if(property.equals("margin-top")) config.setMarginTop(Integer.parseInt(value));
		else if(property.equals("margin-bottom")) config.setMarginBottom(Integer.parseInt(value));
		else if(property.equals("margin-right")) config.setMarginRight(Integer.parseInt(value));
		else if(property.equals("title")) config.setTitle(value);
		else if(property.equals("id")) config.setId(Integer.parseInt(value));
		else if(property.equals("layout")) config.setLayoutType(value);
		else if(property.equals("children")) config.setChildWrapperIds(createIds(value));
		else if(property.equals("targets")) config.setTargetWrapperIds(createIds(value));
		else if(property.equals("navigation")) config.setNavigationId(Integer.parseInt(value));
	}
	

	/**
	 * Parses a comma delimited String to create an ArrayList of ids.
	 */
	private ArrayList<Integer> createIds(String value) {
		ArrayList<Integer> ids = new ArrayList<Integer>();
		
		String[] parts = value.split(",");
		for(String id : parts){
			ids.add(Integer.parseInt(id));
		}
		
		return ids;
	}
	
	/**
	 * Return the Config mapped to an id
	 */
	public Config getConfig(int id){
		return configs.get(id);
	}

	/**
	 * Converts and returns an ArrayList of ids to a HashMap of ids to Config(s)
	 */
	public HashMap<Integer, Config> getConfigs(ArrayList<Integer> ids) {
		HashMap<Integer, Config> idToConfig = new HashMap<Integer, Config>();
		
		if(ids != null){
			for(Integer id : ids)
				idToConfig.put(id, configs.get(id));
		}
		
		return idToConfig;
	}
}
