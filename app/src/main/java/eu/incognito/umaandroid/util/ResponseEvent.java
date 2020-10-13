package eu.incognito.umaandroid.util;

import org.json.JSONObject;

public class ResponseEvent {
    JSONObject response;
    RequestType requestType;

    public ResponseEvent(JSONObject response, RequestType requestType) {

        this.response = response;
        this.requestType = requestType;
    }

    public void setResponse(JSONObject response) {
        this.response = response;
    }
    public void setRequestType(RequestType requestType) {
        this.requestType = requestType;
    }
    public JSONObject getResponse() {
        return this.response;
    }
    public RequestType getRequestType() {
        return this.requestType;
    }
}
