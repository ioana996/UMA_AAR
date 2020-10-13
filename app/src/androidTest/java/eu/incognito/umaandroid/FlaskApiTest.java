package eu.incognito.umaandroid;

import android.content.Context;

import androidx.test.platform.app.InstrumentationRegistry;

import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.assertEquals;

import eu.incognito.umaandroid.op.UmaRequests;

public class FlaskApiTest {
    public static Context context;
    public static UmaAndroidLibrary umaAndroidLibrary;
    public static UmaRequests umaRequests;

    @Before
    public void initTest() {
        context = InstrumentationRegistry.getInstrumentation().getTargetContext();
        umaAndroidLibrary = UmaAndroidLibrary.init(context);
        umaRequests = new UmaRequests(context);
    }
    @Test
    public void testGetDocument() throws InterruptedException {
        umaRequests.getDiscoveryDocument("http://10.0.2.2:3333/discoveryDocument");
        Thread.sleep(5000);
        assertEquals("test flask", umaAndroidLibrary.discoveryDocument.getResourceRegistrationEndpoint());
    }
}
