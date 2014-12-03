package api_challenge;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.HttpClientBuilder;
import org.json.JSONObject;

public class StageI {
	
	public static void main(String[] args){
			
			JSONObject resultJson;
			JSONObject token = new JSONObject();
			JSONObject reversedJson = new JSONObject();
			HttpClient httpClient = HttpClientBuilder.create().build();
			
		    try {
		    	token.put("token", "WmHywKHRM7");
		        HttpPost request = new HttpPost("http://challenge.code2040.org/api/getstring");
		        StringEntity send =new StringEntity(token.toString());
		        request.addHeader("content-type", "application/json");
		        request.setEntity(send);
		        HttpResponse response = httpClient.execute(request);
	        	HttpEntity httpEntity = response.getEntity();
				resultJson = api_challenge.Registration.getJson(httpEntity);
				String receivedString = resultJson.getString(resultJson.names().get(0).toString());
				String reversedString = new StringBuilder(receivedString).reverse().toString();
				reversedJson.put("token", "WmHywKHRM7");
				reversedJson.put("string", reversedString);
				HttpPost requestTwo = new HttpPost("http://challenge.code2040.org/api/validatestring");
				StringEntity sendFinal= new StringEntity(reversedJson.toString());
				requestTwo.addHeader("content-type", "application/json");
		        requestTwo.setEntity(sendFinal);
	
		    }catch (Exception e) {
		    	  e.printStackTrace();}
		    
		}
	
}
