import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

import org.json.JSONObject;


/**
 * this class writes CVS files
 * @author Emmanuel
 *
 */
public class CsvFileWriter {
	 
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
	 
	 /**
	  * this method writes the cvs file using a list of json entries
	 * @param jsons arraylist of json entries
	 */
	public void writeCsvFile(ArrayList<JSONObject> jsons){
		 FileWriter fileWriter = null;
		 
		 try {
			 fileWriter = new FileWriter("GoEuroTest");
			 fileWriter.append(FILE_HEADER);
			 fileWriter.append(NEW_LINE_SEPARATOR);
	
			 for(JSONObject item : jsons){
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
