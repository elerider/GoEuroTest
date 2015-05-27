import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

import org.json.JSONException;
import org.json.JSONObject;


/**
 * this class writes CVS files
 * @author Emmanuel
 *
 */
public class CsvFileWriter {
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
	 /**
	 * constant variable to write constant variable
	 */
	private static final String COMMA_DELIMITER = ",";
	 /**
	 * constant variable to write line separator
	 */
	private static final String NEW_LINE_SEPARATOR = "\n";
	 /**
	 * constant to write the header of the csv file
	 */
	private static final String FILE_HEADER = "_id,name,type,latitude,longitude";
	 
	public CsvFileWriter(String originalString) {
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
		this.jsonList = this.getJSON();
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
	 /**
	  * this method writes the cvs file using a list of json entries
	 * @param jsons arraylist of json entries
	 */
	public void writeCsvFile(){
		
		 FileWriter fileWriter = null;
		 
		 try {
			 fileWriter = new FileWriter("GoEuroTest");
			 fileWriter.append(FILE_HEADER);
			 fileWriter.append(NEW_LINE_SEPARATOR);
	
			 for(JSONObject item : jsonList){
				fileWriter.append(String.valueOf(item.getInt("_id")));
				fileWriter.append(COMMA_DELIMITER);
				fileWriter.append(item.getString("name"));
				fileWriter.append(COMMA_DELIMITER);
				fileWriter.append(item.getString("type"));
				fileWriter.append(COMMA_DELIMITER);
				fileWriter.append(String.valueOf(item.getJSONObject("geo_position").getDouble("latitude")));
				fileWriter.append(COMMA_DELIMITER);
				fileWriter.append(String.valueOf(item.getJSONObject("geo_position").getDouble("latitude")));
				fileWriter.append(NEW_LINE_SEPARATOR);
			 }
			 System.out.println("CSV file created successfully");
		} catch (Exception  e) {
			System.out.println("Error in CsvFileWriter");

			e.printStackTrace();
		}finally {
            try {
                fileWriter.flush();
                fileWriter.close();
            } catch (IOException e) {
                System.out.println("Error with flushing and closing the fileWriter");
                e.printStackTrace();
            }
        }	 
	 }

}
