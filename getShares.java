import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Arrays;

public class GetShares {

	public GetShares() {
		// TODO Auto-generated constructor stub
	}
	
	
	public int getShares(String url){
		
		 int FB = 0;
		 int TW = 0;
		
		 String getFB = facebook(url);
		 String getTW = twitter(url);
		 
		 String[] splited = getFB.split("\\b+");
		 if (Arrays.asList(splited).contains("shares")){
			 getFB = getFB.substring(getFB.indexOf("shares\":") + 9, getFB.indexOf("}"));
			 FB = Integer.parseInt(getFB);
		 }

		 getTW = getTW.substring(getTW.indexOf("count\":") + 7, getTW.indexOf(","));
		 TW = Integer.parseInt(getTW);
		 
		 
		 return (FB + TW);
		 
		
	}
	
	// ---- Get Facebook Share Count ---
    private String facebook(String surl){
    	
    	String url ="http://graph.facebook.com/?id="+surl; 
		try {
		URL obj = new URL(url);
		HttpURLConnection con;
		
			con = (HttpURLConnection) obj.openConnection();
			
			
			con.setRequestMethod("GET");
			//add request header
			con.setRequestProperty("User-Agent", "Mozilla/5.0 (Macintosh; U; Intel Mac OS X 10.4; en-US; rv:1.9.2.2) Gecko/20100316 Firefox/3.6.2");

			// Send request
			//System.out.println("\nSending 'GET' request to URL : " + url);
			BufferedReader in = new BufferedReader(
			new InputStreamReader(con.getInputStream()));
			String outputLine;

			// reading output from Request
			StringBuffer response = new StringBuffer(); 
			while ((outputLine = in.readLine()) != null) {
			   response.append(outputLine);
			   
			}
			in.close();
			return response.toString();
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		return null;
	}
	
    
    // ---- Get Twitter Share Count ---
	private String twitter(String surl){
		
		String url ="https://cdn.api.twitter.com/1/urls/count.json?url="+surl; 
		try {
		URL obj = new URL(url);
		HttpURLConnection con;
		
			con = (HttpURLConnection) obj.openConnection();
			
			
			con.setRequestMethod("GET");
			//add request header
			con.setRequestProperty("User-Agent", "Mozilla/5.0 (Macintosh; U; Intel Mac OS X 10.4; en-US; rv:1.9.2.2) Gecko/20100316 Firefox/3.6.2");

			// Send request
			//System.out.println("\nSending 'GET' request to URL : " + url);
			BufferedReader in = new BufferedReader(
			new InputStreamReader(con.getInputStream()));
			String outputLine;

			// reading output from Request
			StringBuffer response = new StringBuffer(); 
			while ((outputLine = in.readLine()) != null) {
			   response.append(outputLine);
			   
			}
			in.close();
			return response.toString();
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return null;
	}

}
