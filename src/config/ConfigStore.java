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

import enums.EAlignment;
import enums.ELayoutType;
import enums.EWrapperType;

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
			InputStream is = activity.getResources().openRawResource(R.raw.basictabs);
			BufferedReader in = new BufferedReader(new InputStreamReader(is));
			
			String line = in.readLine();
			while(line != null && line.trim().length() > 0){
				line = line.trim();
				
				Config c = new Config();
				
				c.type = EWrapperType.stringToWrapperType(line);
				
				line = in.readLine();
				
				while (line != null && line.trim().length() > 0) {
					line = line.trim();
					
					String[] parts = line.split(":");
					
					setConfigProperty(c, parts[0], parts[1]);
					
					line = in.readLine();
				}
				
				configs.put(c.id, c);
				
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
		if(property.equals("height")) config.height = Integer.parseInt(value);
		else if(property.equals("width")) config.width = Integer.parseInt(value);
		else if(property.equals("screen-alignment")) config.screenAlignment = EAlignment.stringToAlignment(value);
		else if(property.equals("inner-alignment")) config.innerAlignment = EAlignment.stringToAlignment(value);
		else if(property.equals("background-color")) config.backgroundColor = Color.parseColor(value);
		else if(property.equals("padding-left")) config.paddingLeft = Integer.parseInt(value);
		else if(property.equals("padding-top")) config.paddingTop = Integer.parseInt(value);
		else if(property.equals("padding-bottom")) config.paddingBottom = Integer.parseInt(value);
		else if(property.equals("padding-right")) config.paddingRight = Integer.parseInt(value);
		else if(property.equals("margin-left")) config.marginLeft = Integer.parseInt(value);
		else if(property.equals("margin-top")) config.marginTop = Integer.parseInt(value);
		else if(property.equals("margin-bottom")) config.marginBottom = Integer.parseInt(value);
		else if(property.equals("margin-right")) config.marginRight = Integer.parseInt(value);
		else if(property.equals("title")) config.title = value;
		else if(property.equals("id")) config.id = Integer.parseInt(value);
		else if(property.equals("layout")) config.layoutType = ELayoutType.stringToLayoutType(value);
		else if(property.equals("children")) config.childWrapperIds = createIds(value);
		else if(property.equals("targets")) config.targetWrapperIds = createIds(value);
		else if(property.equals("navigation")) config.navigationId = Integer.parseInt(value);
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
