package eu.incognito.umaandroid;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;

import com.android.volley.Response;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;
import org.json.JSONObject;

import java.io.IOException;

import eu.incognito.umaandroid.model.DiscoveryDocument;
import eu.incognito.umaandroid.op.HttpRequestHandler;
import eu.incognito.umaandroid.op.UmaRequests;
import eu.incognito.umaandroid.util.RequestType;
import eu.incognito.umaandroid.util.ResponseEvent;
import timber.log.Timber;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        //String test_url = "http://localhost:8180/auth/realms/springboot-uma/.well-known/uma2-configuration";
        //String test_url = "https://www.google.com";
        String test_url = "http://02338dc69711.ngrok.io/auth/realms/springboot-uma/.well-known/uma2-configuration";

        super.onCreate(savedInstanceState);
        EventBus.getDefault().register(this);
        setContentView(R.layout.activity_main);

        Timber.plant(new Timber.DebugTree());

        UmaAndroidLibrary umaAndroidLibrary = UmaAndroidLibrary.init(this);
        UmaRequests umaRequests = new UmaRequests(this);
        umaRequests.getDiscoveryDocument(test_url);
        //Timber.i(UmaAndroidLibrary.discoveryDocument.getPermissionEndpoint());
        //Log.i("test", UmaAndroidLibrary.discoveryDocument.getAuthorizationEndpoint());
        //Timber.d(UmaAndroidLibrary.discoveryDocument.getTokenEndpoint());
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onEvent(ResponseEvent event) {
        switch(event.getRequestType()) {
            case DISCOVERY_DOCUMENT:
                Log.i("test", UmaAndroidLibrary.discoveryDocument.getAuthorizationEndpoint());
                Log.i("mesaj", event.getResponse().toString());
                break;
            case RESOURCE_ACCESS:
                Log.i("test", "Resource Access event");
                break;
            case PERMISSION_TICKET:
                Log.i("test", "Permission Ticket Event");
                break;
            case RPT_ACCESS_TOKEN:
                Log.i("test", "RPT Access Token Event");
                break;
        }
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        EventBus.getDefault().unregister(this);
    }

}
