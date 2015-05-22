import java.util.ArrayList;
import org.json.JSONException;
import org.json.JSONObject;


/**
 * this class convert the string of the webserver to json files
 * @author Emmanuel
 *
 */
public class StringToJSON {
	
	/**
	 * original string returned by the website
	 */
	private String originalString;
	/**
	 * list of strings
	 */
	private ArrayList<String> stringList;
	/**
	 * list of json objects
	 */
	private ArrayList<JSONObject> jsonList;
	
	public StringToJSON(String originalString) {
		this.originalString = originalString;
		this.stringList = new ArrayList<String>();
		this.jsonList = new ArrayList<JSONObject>();
		cleanString();
		splitString();
	}
	
	/**
	 * remove the first and the last character[ ] of the original string
	 */
	public void cleanString(){
		originalString = originalString.substring(1);
		if (originalString.length() > 0 && originalString.charAt(originalString.length()-1) ==']') {
			originalString = originalString.substring(0, originalString.length()-1);
		}
	}
	
	/**
	 * split the original string in many string
	 */
	public void splitString(){
		String buffer = originalString;
		if(buffer.contains("},{")){
			while(buffer.contains("},{")){
				int split = buffer.indexOf("},{");
				if(buffer.charAt(split+2) == '{'){
					stringList.add(buffer.substring(0,split+1)) ;
					buffer = buffer.substring(split+2);
				}
			}
		}
		else{
			stringList.add(buffer) ;
		}
	}
	
	/**
	 * create json elements for each string in stringList
	 * @return a list a json elements
	 */
	public ArrayList<JSONObject> getJSON(){
		for(int i=0;i<stringList.size();i++){
			try {
				JSONObject jsonObj = new JSONObject(stringList.get(i));
				jsonList.add(jsonObj);
			} catch (JSONException e) {
				e.printStackTrace();
				System.out.println("Error with JSON creation!: "+e.getMessage());
			}
		}
		return this.jsonList;
	}

}
