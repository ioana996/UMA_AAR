package eu.incognito.umaandroid.model;

import java.io.Serializable;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Resource implements Serializable
{

    @SerializedName("resource_name")
    @Expose
    private String resourceName;
    @SerializedName("resource_value")
    @Expose
    private String resourceValue;
    @SerializedName("loa")
    @Expose
    private Integer loa;
    private final static long serialVersionUID = 2253165734864473107L;

    public String getResourceName() {
        return resourceName;
    }

    public void setResourceName(String resourceName) {
        this.resourceName = resourceName;
    }

    public String getResourceValue() {
        return resourceValue;
    }

    public void setResourceValue(String resourceValue) {
        this.resourceValue = resourceValue;
    }

    public Integer getLoa() {
        return loa;
    }

    public void setLoa(Integer loa) {
        this.loa = loa;
    }

}