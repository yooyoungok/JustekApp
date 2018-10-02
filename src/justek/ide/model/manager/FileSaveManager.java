package justek.ide.model.manager;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
//import java.util.Iterator;

import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.json.simple.JSONObject;

public class FileSaveManager {

	static final String Tag = "FileSaveManager";

	private static FileSaveManager instance;

	JSONObject jsonObject;

	public static FileSaveManager getInstance () {
		if(instance==null) {
			instance = new FileSaveManager();
		}
		return instance;
	}

	public FileSaveManager() {
		jsonObject = new JSONObject();
	}

	@SuppressWarnings("unchecked")
	public void setAxisChangeData(String key, JSONObject value) {
		System.out.println(Tag+"== setAxisChangeData \n"+value.toString());
		jsonObject.put(key, value);
		this.saveAxisSetupFile();
	}

	public JSONObject getAxisChangeData(String key) {
		System.out.println(Tag+"== getAxisChangeData \n ==> "+this.jsonObject.toString());
		return (JSONObject)this.jsonObject.get(key);
	}

	public void saveAxisSetupFile() {
		System.out.println(Tag+"== saveAxisSetupFile");
		try (FileWriter file = new FileWriter("./home/json/Justek.json")) {
			file.write(this.jsonObject.toJSONString());
			System.out.println("Successfully Copied JSON Object to File...");
			System.out.println("\nJSON Object: " + jsonObject);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public void makeFile() {
		File file = new File("./home/json/Justek.json");	
		try {
			boolean result = file.createNewFile();
			if(result) {
				System.out.println(Tag+"== File Make Success == readAxisSetupFile");
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public JSONObject readAxisSetupFile() {
		System.out.println(Tag+"== readAxisSetupFile");
		
		JSONParser parser = new JSONParser();
		JSONObject mJsonObject = null;
		Object obj;
		try {
			obj = parser.parse(new FileReader("./home/json/Justek.json"));
			
			if(obj!=null) {
				mJsonObject = (JSONObject) obj;
				this.jsonObject = mJsonObject;
				System.out.println(mJsonObject.toJSONString());
			}
	
		} catch (FileNotFoundException e) {
			System.out.println(Tag+"== FileNotFoundException == readAxisSetupFile");
			this.makeFile();
//			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (ParseException e) {
			System.out.println(Tag+"== ParseException == readAxisSetupFile");
//			e.printStackTrace();
		}
		
		return jsonObject;
	}

}
