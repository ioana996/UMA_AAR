package eu.incognito.umaandroid.op;

import android.content.Context;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import org.greenrobot.eventbus.EventBus;
import org.json.JSONObject;
import eu.incognito.umaandroid.UmaAndroidLibrary;
import eu.incognito.umaandroid.model.DiscoveryDocument;
import eu.incognito.umaandroid.model.PermissionTicket;
import eu.incognito.umaandroid.model.Resource;
import eu.incognito.umaandroid.model.RptAccessToken;
import eu.incognito.umaandroid.util.RequestType;
import eu.incognito.umaandroid.util.ResponseEvent;
import eu.incognito.umaandroid.util.StatusCode;
import timber.log.Timber;

/**
 * Created by ioana.stroinea on 13-October-20.
 *
 * The UmaRequests class is a wrapper over the basic HTTP requests
 * implemented in HttpRequestHandler class. Here are present all the
 * operations performed by a UMA client to the Resource Server and
 * the Authorization Server. It implements custom listeners for each
 * http request.
 */

public class UmaRequests {

    UmaAndroidLibrary umaAndroidLibrary;

    public UmaRequests(Context context) {
        umaAndroidLibrary = UmaAndroidLibrary.init(context);
    }

    public void getDiscoveryDocument(String url) {

        umaAndroidLibrary.httpRequestHandler.sendGet(url, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                GsonBuilder gsonBuilder = new GsonBuilder();
                Gson gson = gsonBuilder.create();
                UmaAndroidLibrary.discoveryDocument = gson.fromJson(
                        response.toString(),
                        DiscoveryDocument.class);
                ResponseEvent responseEvent = new ResponseEvent(response,
                        RequestType.DISCOVERY_DOCUMENT);
                EventBus.getDefault().post(responseEvent);
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Timber.d("Error - Failed to get Discovery Document!");
                umaAndroidLibrary.error = "Error - Failed to get Discovery Document!";
            }
        });
    }

    public void getPermissionTicket(String url) {
        umaAndroidLibrary.httpRequestHandler.sendGet(url, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                switch (umaAndroidLibrary.httpRequestHandler.responseCode) {
                    case StatusCode.UNAUTHORIZED:
                        GsonBuilder gsonBuilder = new GsonBuilder();
                        Gson gson = gsonBuilder.create();
                        UmaAndroidLibrary.permissionTicket = gson.fromJson(
                                response.toString(),
                                PermissionTicket.class);
                        break;
                    case StatusCode.ACCESS_FORBIDDEN:
                        umaAndroidLibrary.error = "Access to resource is forbidden!";
                        break;
                }
                ResponseEvent responseEvent = new ResponseEvent(response,
                        RequestType.PERMISSION_TICKET);
                EventBus.getDefault().post(responseEvent);
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Timber.d("Failed to get Permission Ticket!");
                umaAndroidLibrary.error = "Error - Access to resource is forbidden!";
            }
        });
    }

    public void getResource (String url, String access_token) {
        umaAndroidLibrary.httpRequestHandler.sendAuthorizedGet(url, access_token, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                GsonBuilder gsonBuilder = new GsonBuilder();
                Gson gson = gsonBuilder.create();
                UmaAndroidLibrary.resource = gson.fromJson(
                        response.toString(),
                        Resource.class);
                ResponseEvent responseEvent = new ResponseEvent(response,
                        RequestType.RESOURCE_ACCESS);
                EventBus.getDefault().post(responseEvent);
                }
            }
        , new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Timber.d("Failed to get Resource!");
                umaAndroidLibrary.error = "Error - Failed to get Resource!";
            }
        });
    }

    public void getAccessToken (String url, String access_token, String permissionTicket){
        umaAndroidLibrary.httpRequestHandler.sendAuthorizedPost(url, access_token, permissionTicket, new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        GsonBuilder gsonBuilder = new GsonBuilder();
                        Gson gson = gsonBuilder.create();
                        UmaAndroidLibrary.rptAccessToken = gson.fromJson(
                                response.toString(),
                                RptAccessToken.class);
                        ResponseEvent responseEvent = new ResponseEvent(response,
                                RequestType.RPT_ACCESS_TOKEN);
                        EventBus.getDefault().post(responseEvent);
                    }
                }
                , new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Timber.d("Failed to get Requesting Party Token!");
                        umaAndroidLibrary.error = "Error - Failed to get Requesting Party Token!";
                    }
                });
    }
}
