package api_challenge;
 
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.HttpClientBuilder;
import org.json.JSONArray;
import org.json.JSONObject;
 
public class StageII {
 
        public static void main(String[] args){
               
                JSONObject resultJson;
                JSONObject token = new JSONObject();
                HttpClient httpClient = HttpClientBuilder.create().build();
               
            try {
                token.put("token", "WmHywKHRM7");
                HttpPost request = new HttpPost("http://challenge.code2040.org/api/haystack");
                StringEntity send =new StringEntity(token.toString());
                request.addHeader("content-type", "application/json");
                request.setEntity(send);
                HttpResponse response = httpClient.execute(request);
                HttpEntity httpEntity = response.getEntity();
	            resultJson = api_challenge.Registration.getJson(httpEntity);
	           
	            JSONObject result = resultJson.getJSONObject("result");
	            JSONArray haystack = result.getJSONArray("haystack");
	            String needle = result.getString("needle");
	           
	            int index;
	           
	            for(index = 0; index < haystack.length(); index++)
	            {
	                    if(haystack.getString(index).equals(needle))
	                    {
	                            break;
	                    }
	            }
	           
	            JSONObject sendIndex = new JSONObject();
	            sendIndex.put("token", "WmHywKHRM7");
	            sendIndex.put("needle", index);
	            HttpPost requestTwo = new HttpPost("http://challenge.code2040.org/api/validateneedle");
	            StringEntity sendFinal= new StringEntity(sendIndex.toString());
	            requestTwo.addHeader("content-type", "application/json");
                requestTwo.setEntity(sendFinal);
               
            }catch (Exception e) {
                  e.printStackTrace();}
           
        }
}