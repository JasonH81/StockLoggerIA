package jason.InternalAssessment;

import java.io.IOException;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class StockAPI {

	
	public StockAPI() {
		
	}
	
	public void getStockSymbol(String stockName) {
		
	}
	
	public void getStockData(String stockSymbol) {
		    String API_KEY = "5619e9d0cdf646bbb95934a8d34bc04d";

		        //String symbol = "AAPL";
		        String urlString = "https://api.twelvedata.com/price?symbol=" + stockSymbol + "&apikey=" + API_KEY;
		        System.out.println(urlString);		        
		        try {
		            URL url = new URL(urlString);
		            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
		            conn.setRequestMethod("GET");

		            BufferedReader in = new BufferedReader(new InputStreamReader(conn.getInputStream()));
		            String inputLine;
		            StringBuilder response = new StringBuilder();

		            while ((inputLine = in.readLine()) != null) {
		                response.append(inputLine);
		            }
		            in.close();
		            
		            String stockPrice = response.toString();
		            stockPrice = stockPrice.substring(10, stockPrice.length()-2);
		            System.out.println(stockSymbol + " price: " + stockPrice);
		            

		        } catch (Exception e) {
		            e.printStackTrace();
		        }
		    
	}
}