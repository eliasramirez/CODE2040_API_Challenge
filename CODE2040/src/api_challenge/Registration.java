package api_challenge;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.HttpClientBuilder;
import org.json.JSONObject;


public class Registration {

	
	public static void main(String[] args){
		
		JSONObject resultJson;
		JSONObject information = new JSONObject();
		HttpClient httpClient = HttpClientBuilder.create().build();
		
	    try {
	    	information.put("email", "eliasramirehz@gmail.com");
			information.put("github", "https://github.com/eliasramirez/CODE2040_API_Challenge");
	        HttpPost request = new HttpPost("http://challenge.code2040.org/api/register");
	        StringEntity send =new StringEntity(information.toString());
	        request.addHeader("content-type", "application/json");
	        request.setEntity(send);
	        HttpResponse response = httpClient.execute(request);
        	HttpEntity httpEntity = response.getEntity();
			resultJson = getJson(httpEntity);
			//The key of the received JSONObject is "result", but for expandability, I made is so it is open for change.
			String tokenString = resultJson.getString(resultJson.names().get(0).toString());
			System.out.println("Token: " + tokenString);
	        

	    }catch (Exception e) {
	    	  e.printStackTrace();}
	    
	}
	
	static JSONObject getJson(HttpEntity httpEntity) {				

	    	InputStream is = null;
	    	String received = "";
	    	JSONObject jObj = null;
	    	
			try {
				is = httpEntity.getContent();
			} catch (IllegalStateException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			} catch (IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}          
	         
			try {
				//inputsteamreader decondes into 8 single-byte graphic characters
				//and the bufferedreader reads the text from the input stream
				
				BufferedReader reader = new BufferedReader(new InputStreamReader(is, "iso-8859-1"), 8);
				StringBuilder sb = new StringBuilder();
				String line = null;
				while ((line = reader.readLine()) != null) {
					sb.append(line + "\n");
				}
				is.close();
				received = sb.toString();
				jObj = new JSONObject(received);
		        
			}catch(Exception e){
				e.printStackTrace();
			}

	        return jObj;
	}
}


