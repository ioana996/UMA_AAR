package eu.incognito.umaandroid.model;

import java.io.Serializable;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class RptAccessToken implements Serializable
{

    @SerializedName("access_token")
    @Expose
    private String accessToken;
    private final static long serialVersionUID = -5663445601062465559L;

    public String getAccessToken() {
        return accessToken;
    }

    public void setAccessToken(String accessToken) {
        this.accessToken = accessToken;
    }

}
