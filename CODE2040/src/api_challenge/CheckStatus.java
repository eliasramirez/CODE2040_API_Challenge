package api_challenge;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.HttpClientBuilder;
import org.json.JSONException;
import org.json.JSONObject;

public class CheckStatus {
	
	public static void main(String[] args) throws Exception {
		
		JSONObject resultJson;
        JSONObject token = new JSONObject();
        HttpClient httpClient = HttpClientBuilder.create().build();
   
        token.put("token", "WmHywKHRM7");
        HttpPost request = new HttpPost("http://challenge.code2040.org/api/status");
        StringEntity send =new StringEntity(token.toString());
        request.addHeader("content-type", "application/json");
        request.setEntity(send);
        HttpResponse response = httpClient.execute(request);
        HttpEntity httpEntity = response.getEntity();
        resultJson = api_challenge.Registration.getJson(httpEntity);
        System.out.println(resultJson);
	}
}
