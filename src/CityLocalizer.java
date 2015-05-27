import java.io.IOException;


/**
 * Main class of the application
 * @author Emmanuel
 *
 */
public class CityLocalizer {
	public CityLocalizer(){}
	
	
	/**
	 * main method of the program
	 * @param args contains the input string
	 */
	public static void main(String[] args) {
		CityLocalizer goEuro = new CityLocalizer();
		try {
			goEuro.go(args);
		} catch (IOException e) {
			
			e.printStackTrace();
		}
	}
	/**
	 * Core algorithm of the program
	 * @param args arg of the main method
	 * @throws IOException
	 */
	public void go(String[] args) throws IOException{
		if(args.length > 0){
			Querry querry = new Querry(args[0]);
			String jsonString = querry.getData();
			if(jsonString != null){
//				StringToJSON jsons = new StringToJSON(jsonString);
//				ArrayList<JSONObject> jsonList = new ArrayList<JSONObject>();
//				jsonList = jsons.getJSON();
				CsvFileWriter writer = new CsvFileWriter(jsonString);
				writer.writeCsvFile();
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