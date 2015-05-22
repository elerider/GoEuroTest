import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;


public class Querry {
	private static final String LOCATION = "http://www.goeuro.com/GoEuroAPI/rest/api/v2/position/suggest/en/";
	private String city;
	private StringBuffer webPageData;
	private InputStream inputStream;
	

	public Querry(String city) {
		this.city = city;
		this.webPageData = new StringBuffer();
	}
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