package eu.incognito.umaandroid.model;

import java.io.Serializable;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class PermissionTicket implements Serializable
{

    @SerializedName("as_uri")
    @Expose
    private String asUri;
    @SerializedName("ticket")
    @Expose
    private String ticket;
    private final static long serialVersionUID = -650993139846877795L;

    public String getAsUri() {
        return asUri;
    }

    public void setAsUri(String asUri) {
        this.asUri = asUri;
    }

    public String getTicket() {
        return ticket;
    }

    public void setTicket(String ticket) {
        this.ticket = ticket;
    }

}
