package api_challenge;

import java.util.Arrays;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.HttpClientBuilder;
import org.json.JSONArray;
import org.json.JSONObject;

public class StageIII {
	
	
	public static void main(String[] args){
        
        JSONObject resultJson;
        JSONObject token = new JSONObject();
        HttpClient httpClient = HttpClientBuilder.create().build();
       
    try {
        token.put("token", "WmHywKHRM7");
        HttpPost request = new HttpPost("http://challenge.code2040.org/api/prefix");
        StringEntity send =new StringEntity(token.toString());
        request.addHeader("content-type", "application/json");
        request.setEntity(send);
        HttpResponse response = httpClient.execute(request);
        HttpEntity httpEntity = response.getEntity();
        resultJson = api_challenge.Registration.getJson(httpEntity);
        JSONArray array = resultJson.getJSONObject("result").getJSONArray("array");
        String prefix = resultJson.getJSONObject("result").getString("prefix");
        
        for (int i = array.length() - 1; i >= 0; i--) {
        	
			if (array.getString(i).startsWith(prefix)){
				array.remove(i);
			}
		}
        
        JSONObject sendIndex = new JSONObject();
        sendIndex.put("token", "WmHywKHRM7");
        sendIndex.put("array", array);
        HttpPost requestTwo = new HttpPost("http://challenge.code2040.org/api/validateprefix");
        StringEntity sendFinal= new StringEntity(sendIndex.toString());
        requestTwo.addHeader("content-type", "application/json");
        requestTwo.setEntity(sendFinal);
        
    }catch (Exception e) {
          e.printStackTrace();}
   
}
	
}
