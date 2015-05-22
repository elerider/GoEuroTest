import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;


/**
 * this class connects the program to the webserver using a string parameter
 * @author Emmanuel
 *
 */
public class Querry {
	/**
	 * this variable is the fixed part of the url
	 */
	private static final String LOCATION = "http://www.goeuro.com/GoEuroAPI/rest/api/v2/position/suggest/en/";
	/**
	 * variable part of the url
	 */
	private String city;
	/**
	 * use to store the string data
	 */
	private StringBuffer webPageData;
	/**
	 * used to read the data exchange during the connection
	 */
	private InputStream inputStream;
	

	public Querry(String city) {
		this.city = city;
		this.webPageData = new StringBuffer();
	}
	
	/**
	 * Connect to the web server of GoEuro and retrieve the String to the program
	 * @return a string containing the data
	 * @throws IOException handle IOException
	 */
	public String getData() throws IOException{
		try {
			URL url = new URL(LOCATION+city);
			inputStream = url.openStream();
			BufferedReader inputReader = new BufferedReader(new InputStreamReader(inputStream));
			System.out.println("Connection established: "+url.toString());
			String inputLine = null;
			while ((inputLine = inputReader.readLine()) != null) {
				webPageData.append(inputLine); 
			} 
			inputReader.close();
		} catch (MalformedURLException e) {
			e.printStackTrace();
			System.out.println("error in querrying Location JSON API");
		}finally{
			inputStream.close();
		}
		if(webPageData.toString().equals("[]")){
			return null;
		}
		else{
			return webPageData.toString();
		}		
	}
}