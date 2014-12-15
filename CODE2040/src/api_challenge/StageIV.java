package api_challenge;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.HttpClientBuilder;
import org.joda.time.DateTime;
import org.json.JSONObject;

public class StageIV {

	public static void main(String[] args) throws Exception{
        
        JSONObject resultJson;
        JSONObject token = new JSONObject();
        HttpClient httpClient = HttpClientBuilder.create().build();
   
        token.put("token", "WmHywKHRM7");
        HttpPost request = new HttpPost("http://challenge.code2040.org/api/time");
        StringEntity send =new StringEntity(token.toString());
        request.addHeader("content-type", "application/json");
        request.setEntity(send);
        HttpResponse response = httpClient.execute(request);
        HttpEntity httpEntity = response.getEntity();
        resultJson = api_challenge.Registration.getJson(httpEntity);
        String datestamp = resultJson.getJSONObject("result").getString("datestamp");
        int interval = resultJson.getJSONObject("result").getInt("interval");
        DateTime parsedDate = DateTime.parse(datestamp);
        parsedDate = parsedDate.plusSeconds(interval);
        
        JSONObject sendDate = new JSONObject();
        sendDate.put("token", "WmHywKHRM7");
        sendDate.put("datestamp", parsedDate);
        HttpPost requestTwo = new HttpPost("http://challenge.code2040.org/api/validatetime");
        StringEntity sendFinal= new StringEntity(sendDate.toString());
        requestTwo.addHeader("content-type", "application/json");
        requestTwo.setEntity(sendFinal);
	}
	
}
