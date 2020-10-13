package eu.incognito.umaandroid.util;

public class Endpoints {

    private static final String PROTOCOL = "http";
    private static final String BASE_URL = "://localhost:8180";
    private static final String REALM = "/auth/realms/springboot-uma";
    private static final String DISCOVERY_DOC = "/.well-known/uma2-configuration";

    public static String getPROTOCOL() {
        return PROTOCOL;
    }

    public static String getBaseUrl() {
        return BASE_URL;
    }

    public static String getREALM() {
        return REALM;
    }

    public static String getDiscoveryDoc() {
        return DISCOVERY_DOC;
    }
}
