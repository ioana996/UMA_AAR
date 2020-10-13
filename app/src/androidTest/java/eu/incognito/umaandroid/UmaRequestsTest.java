package eu.incognito.umaandroid;

import android.content.Context;

import androidx.test.ext.junit.runners.AndroidJUnit4;
import androidx.test.platform.app.InstrumentationRegistry;

import org.junit.After;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.experimental.categories.Categories;
import org.junit.runner.RunWith;

import java.io.IOException;
import java.io.InputStream;

import eu.incognito.umaandroid.op.UmaRequests;
import eu.incognito.umaandroid.util.JsonFileReader;
import okhttp3.HttpUrl;
import okhttp3.mockwebserver.Dispatcher;
import okhttp3.mockwebserver.MockResponse;
import okhttp3.mockwebserver.MockWebServer;
import okhttp3.mockwebserver.RecordedRequest;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

@RunWith(AndroidJUnit4.class)
@Ignore
public class UmaRequestsTest {
    public MockWebServer server = new MockWebServer();
    public MockResponse mockResponse;
    public Context context;
    public UmaRequests umaRequests;
    public UmaAndroidLibrary umaAndroidLibrary;
    public HttpUrl baseUrl;
    public String jsonString;
    public InputStream is;

    public void initTest() {
        context = InstrumentationRegistry.getInstrumentation().getTargetContext();
        umaAndroidLibrary = UmaAndroidLibrary.init(context);
        umaRequests = new UmaRequests(context);
    }

    public void createServerResponse() throws IOException {
        is = context.getResources().getAssets().open("DiscoveryDocument.json");
        jsonString = JsonFileReader.fileToString(is);

        mockResponse = new MockResponse().addHeader("Content-Type", "application/json; charset=utf-8")
                                    .setBody(jsonString);
        server.enqueue(mockResponse);
        server.enqueue(new MockResponse().setResponseCode(201)
                .setHeader("Warning", "199 - \"UMA Authorization Server Unreachable\""));
    }

    private final Dispatcher dispatcher = new Dispatcher() {

        @Override
        public MockResponse dispatch(RecordedRequest request) throws InterruptedException {
            switch (request.getPath()) {
                case "/discoveryDocument":
                    try {
                        is = context.getResources().getAssets().open("DiscoveryDocument.json");
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                    jsonString = JsonFileReader.fileToString(is);
                    return new MockResponse().addHeader("Content-Type", "application/json; charset=utf-8")
                            .setBody(jsonString);
                case "/permissionTicketOnError":
                    return new MockResponse().setResponseCode(203)
                            .setHeader("Warning", "199 - \"UMA Authorization Server Unreachable\"");
                case "/permissionTicketOnSuccess":
                    try {
                        is = context.getResources().getAssets().open("PermissionTicket.json");
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                    jsonString = JsonFileReader.fileToString(is);
                    return new MockResponse().setResponseCode(201)
                            .setHeader("WWW-Authenticate", "UMA realm=\"springboot-uma\"")
                            .setBody(jsonString);
                case "/resource":
                    try {
                        is = context.getResources().getAssets().open("Resource.json");
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                    jsonString = JsonFileReader.fileToString(is);
                    return new MockResponse().setResponseCode(200)
                            .setHeader("Content-Type", "application/json; charset=utf-8")
                            .setBody(jsonString);
                case "/rptAccessToken":
                    try {
                        is = context.getResources().getAssets().open("RptAccessToken.json");
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                    jsonString = JsonFileReader.fileToString(is);
                    return new MockResponse().setResponseCode(200)
                            .setHeader("Content-Type", "application/json; charset=utf-8")
                            .setBody(jsonString);
            }
            return new MockResponse().setResponseCode(500);
        }
    };

    @Before
    public void startServer() {
        try {
           initTest();
           //createServerResponse();
           server.setDispatcher(dispatcher);
           server.start();
           //HttpUrl baseUrl = server.url("http://localhost:8180");
    } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Test
    //@Ignore
    public void testGetDiscoveryDocument() throws InterruptedException, IOException {

        baseUrl = server.url("/discoveryDocument");
        umaRequests.getDiscoveryDocument(baseUrl.toString());

        Thread.sleep(2000);
        //await().atMost(5, SECONDS).until();
        RecordedRequest request = server.takeRequest();
        assertEquals("GET /discoveryDocument HTTP/1.1", request.getRequestLine());
        assertEquals("http://097509e86123.ngrok.io/auth/realms/springboot-uma/protocol/openid-connect/auth",
                UmaAndroidLibrary.discoveryDocument.getAuthorizationEndpoint());
        assertEquals("http://097509e86123.ngrok.io/auth/realms/springboot-uma/authz/protection/resource_set",
                umaAndroidLibrary.discoveryDocument.getResourceRegistrationEndpoint());
        //assertEquals(9, umaAndroidLibrary.httpRequestHandler.responseCode);
        //assertEquals("header", umaAndroidLibrary.httpRequestHandler.header);
    }

    @Test
    @Ignore
    public void testGetPermissionTicketOnError() throws InterruptedException {
        baseUrl = server.url("/permissionTicketOnError");
        umaRequests.getPermissionTicket(baseUrl.toString());
        Thread.sleep(2000);
        RecordedRequest request = server.takeRequest();
        assertEquals("GET /permissionTicketOnError HTTP/1.1", request.getRequestLine());
        //assertEquals("199 - 'UMA Authorization Server Unreachable'", umaAndroidLibrary.permissionTicket);
        assertEquals(203, umaAndroidLibrary.httpRequestHandler.responseCode);
        assertEquals("199 - \"UMA Authorization Server Unreachable\"",
                umaAndroidLibrary.httpRequestHandler.header.get("Warning"));
        assertEquals("Error - Access to resource is forbidden!", umaAndroidLibrary.error);
    }

    @Test
    @Ignore
    public void testGetPermissionTicketOnSuccess() throws InterruptedException {
        baseUrl = server.url("/permissionTicketOnSuccess");
        umaRequests.getPermissionTicket(baseUrl.toString());
        Thread.sleep(2000);
        RecordedRequest request = server.takeRequest();
        assertEquals("GET /permissionTicketOnSuccess HTTP/1.1", request.getRequestLine());
        //assertEquals("199 - 'UMA Authorization Server Unreachable'", umaAndroidLibrary.permissionTicket);
        //assertEquals(9, umaAndroidLibrary.httpRequestHandler.responseCode);
        assertEquals("UMA realm=\"springboot-uma\"",
                umaAndroidLibrary.httpRequestHandler.header.get("WWW-Authenticate"));
        assertEquals("016f84e8-f9b9-11e0-bd6f-0021cc6004de", umaAndroidLibrary.permissionTicket.getTicket());
    }

    @Test
    @Ignore
    public void testGetResource() throws InterruptedException {
        baseUrl = server.url("/resource");
        String access_token = "access_token";
        umaRequests.getResource(baseUrl.toString(), access_token);
        Thread.sleep(2000);
        RecordedRequest request = server.takeRequest();
        assertEquals("GET /resource HTTP/1.1", request.getRequestLine());
        assertEquals("Test Resource", umaAndroidLibrary.resource.getResourceName());
    }

    @Test
    @Ignore
    public void testGetAccessToken() throws InterruptedException {
        baseUrl = server.url("/rptAccessToken");
        String access_token = "Access Token";
        String permissionTicket = "Permission Ticket";
        umaRequests.getAccessToken(baseUrl.toString(), access_token, permissionTicket);
        Thread.sleep(2000);
        RecordedRequest request = server.takeRequest();
        assertEquals("POST /rptAccessToken HTTP/1.1", request.getRequestLine());
        assertEquals("Test Access Token", umaAndroidLibrary.rptAccessToken.getAccessToken());
    }

    @After
    public void shutdownServer() {
        try {
            server.shutdown();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
