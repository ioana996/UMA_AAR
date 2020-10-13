package eu.incognito.umaandroid;

import android.content.Context;
import com.android.volley.RequestQueue;
import com.android.volley.toolbox.Volley;
import eu.incognito.umaandroid.model.DiscoveryDocument;
import eu.incognito.umaandroid.model.PermissionTicket;
import eu.incognito.umaandroid.model.Resource;
import eu.incognito.umaandroid.model.RptAccessToken;
import eu.incognito.umaandroid.op.HttpRequestHandler;

/**
 * Created by ioana.stroinea on 13-October-20.
 *
 * Singleton initialization of the UMA Library. Here are present
 * all the instances of the models used for http response parsing
 * inside the library.
 */

public class UmaAndroidLibrary {
    private static UmaAndroidLibrary umaAndroidLibrary;
    public static Context ctx;
    public static HttpRequestHandler httpRequestHandler;
    public static RequestQueue requestQueue;
    public static DiscoveryDocument discoveryDocument;
    public static PermissionTicket permissionTicket;
    public static Resource resource;
    public static RptAccessToken rptAccessToken;
    public static String error;


    public UmaAndroidLibrary(Context context){
        ctx = context;
        httpRequestHandler = new HttpRequestHandler();
        requestQueue = Volley.newRequestQueue(context);
        discoveryDocument = new DiscoveryDocument();
        permissionTicket = new PermissionTicket();
        resource = new Resource();
        rptAccessToken = new RptAccessToken();
    }

    public static synchronized UmaAndroidLibrary init(Context context) {
        if(umaAndroidLibrary == null) {
            umaAndroidLibrary = new UmaAndroidLibrary(context);
        }
        return umaAndroidLibrary;
    }

}
