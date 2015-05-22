import java.io.IOException;
import java.util.ArrayList;
import org.json.JSONException;
import org.json.JSONObject;


public class Main {
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