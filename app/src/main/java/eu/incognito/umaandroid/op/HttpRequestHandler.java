package eu.incognito.umaandroid.op;


import com.android.volley.AuthFailureError;
import com.android.volley.NetworkResponse;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.toolbox.JsonObjectRequest;
import org.json.JSONObject;
import java.util.HashMap;
import java.util.Map;
import eu.incognito.umaandroid.UmaAndroidLibrary;

/**
 * Created by ioana.stroinea on 13-October-20.
 *
 * The HttpRequestHandler class defines basic methods to send HTTP requests.
 * The parseNetworkResponse overrides the method in its superclass in order to
 * make possible the extraction of the status code and headers from the network
 * response, along with the JSON body.
 */
public class HttpRequestHandler {
    public static int responseCode;
    public static Map<String, String> header;

    /* send simple GET request */
    public void sendGet(String url, Response.Listener<JSONObject> responseListener, Response.ErrorListener responseError) {
        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.GET,
                url, null, responseListener, responseError){
            protected Response<JSONObject> parseNetworkResponse(NetworkResponse response) {
                responseCode = response.statusCode;
                header = response.headers;
                return super.parseNetworkResponse(response);
            }
        };
        // Add the request to the RequestQueue.
        UmaAndroidLibrary.requestQueue.add(jsonObjectRequest);
    }

    /* send GET request along with bearer access token in its header */
    public void sendAuthorizedGet(String url, final String access_token,
                                  Response.Listener<JSONObject> responseListener,
                                  Response.ErrorListener errorListener) {
        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.GET,
                url, null, responseListener, errorListener) {
            @Override
            public Map<String, String> getHeaders() throws AuthFailureError {
                Map<String, String> header = new HashMap<String, String>();
                if (access_token != null) {
                    header.put("Authorization", "Bearer " + access_token);
                }
                return header;
            }
            protected Response<JSONObject> parseNetworkResponse(NetworkResponse response) {
                responseCode = response.statusCode;
                header = response.headers;
                return super.parseNetworkResponse(response);
            }
        };

        // Add the request to the RequestQueue.
        UmaAndroidLibrary.requestQueue.add(jsonObjectRequest);
    }

    /* send POST request along with bearer access token in its header */
    public void sendAuthorizedPost(String url, final String access_token,
                                   final String permissionTicket,
                                   Response.Listener<JSONObject> responseListener,
                                   Response.ErrorListener errorListener) {
        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.POST, url,
                null, responseListener, errorListener) {
            @Override
            public Map<String, String> getHeaders() throws AuthFailureError {
                Map<String, String> header = new HashMap<String, String>();
                if(access_token != null) {
                    header.put("Authorization", "Bearer " + access_token);
                }
                return header;
            }
            @Override
            protected Map<String, String> getParams()
            {
                Map<String, String>  params = new HashMap<String, String>();
                params.put("grant_type", "urn:ietf:params:oauth:grant-type:uma-ticket");
                params.put("ticket", permissionTicket);
                return params;
            }
            protected Response<JSONObject> parseNetworkResponse(NetworkResponse response) {
                responseCode = response.statusCode;
                header = response.headers;
                return super.parseNetworkResponse(response);
            }
        };
        UmaAndroidLibrary.requestQueue.add(jsonObjectRequest);
    }
}
