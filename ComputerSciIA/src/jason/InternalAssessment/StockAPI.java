package jason.InternalAssessment;

import java.io.IOException;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class StockAPI {

	
	public StockAPI() {
		
	}
	
	public String getStockData(String stockSymbol) {
		String stockPrice = "";
	    String API_KEY = "5619e9d0cdf646bbb95934a8d34bc04d";
        String urlString = "https://api.twelvedata.com/price?symbol=" + stockSymbol + "&apikey=" + API_KEY;
        System.out.println(urlString);		        
        try {
            URL url = new URL(urlString);
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("GET");

            BufferedReader in = new BufferedReader(new InputStreamReader(connection.getInputStream()));
            String inputLine;
            StringBuilder response = new StringBuilder();

            while ((inputLine = in.readLine()) != null) {
                response.append(inputLine);
            }
            in.close();
            
            stockPrice = response.toString();
            stockPrice = stockPrice.substring(10, stockPrice.length()-2);
            System.out.println(stockSymbol + " price: " + stockPrice);
        }
        catch (Exception e) {
            e.printStackTrace();
        }
        return stockPrice;
	}
}