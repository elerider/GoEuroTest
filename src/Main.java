import java.io.IOException;
import java.util.ArrayList;
import org.json.JSONException;
import org.json.JSONObject;


/**
 * Main class of the application
 * @author Emmanuel
 *
 */
public class Main {
	
	/**
	 * main methods of the program
	 * @param args contains the string use to query the web server
	 * @throws IOException handles IOExecptions
	 * @throws JSONException handles JSON exceptions
	 */
	public static void main(String[] args) throws IOException, JSONException{
		if(args.length > 0){
			Querry querry = new Querry(args[0]);
			String jsonString = querry.getData();
			if(jsonString != null){
				StringToJSON jsons = new StringToJSON(jsonString);
				ArrayList<JSONObject> jsonList = new ArrayList<JSONObject>();
				jsonList = jsons.getJSON();
				CsvFileWriter writer = new CsvFileWriter();
				writer.writeCsvFile(jsonList);
			}
			else{
				System.out.print("Please use a valid input");
			}
		}
		else{
			System.out.println("Please use an input");
		}
	}
}