
package eu.incognito.umaandroid.model;

import java.io.Serializable;
import java.util.List;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class DiscoveryDocument implements Serializable
{

    @SerializedName("issuer")
    @Expose
    private String issuer;
    @SerializedName("authorization_endpoint")
    @Expose
    private String authorizationEndpoint;
    @SerializedName("token_endpoint")
    @Expose
    private String tokenEndpoint;
    @SerializedName("introspection_endpoint")
    @Expose
    private String introspectionEndpoint;
    @SerializedName("end_session_endpoint")
    @Expose
    private String endSessionEndpoint;
    @SerializedName("jwks_uri")
    @Expose
    private String jwksUri;
    @SerializedName("grant_types_supported")
    @Expose
    private List<String> grantTypesSupported = null;
    @SerializedName("response_types_supported")
    @Expose
    private List<String> responseTypesSupported = null;
    @SerializedName("response_modes_supported")
    @Expose
    private List<String> responseModesSupported = null;
    @SerializedName("registration_endpoint")
    @Expose
    private String registrationEndpoint;
    @SerializedName("token_endpoint_auth_methods_supported")
    @Expose
    private List<String> tokenEndpointAuthMethodsSupported = null;
    @SerializedName("token_endpoint_auth_signing_alg_values_supported")
    @Expose
    private List<String> tokenEndpointAuthSigningAlgValuesSupported = null;
    @SerializedName("scopes_supported")
    @Expose
    private List<String> scopesSupported = null;
    @SerializedName("resource_registration_endpoint")
    @Expose
    private String resourceRegistrationEndpoint;
    @SerializedName("permission_endpoint")
    @Expose
    private String permissionEndpoint;
    @SerializedName("policy_endpoint")
    @Expose
    private String policyEndpoint;
    private final static long serialVersionUID = -58716955781499193L;

    public String getIssuer() {
        return issuer;
    }

    public void setIssuer(String issuer) {
        this.issuer = issuer;
    }

    public String getAuthorizationEndpoint() {
        return authorizationEndpoint;
    }

    public void setAuthorizationEndpoint(String authorizationEndpoint) {
        this.authorizationEndpoint = authorizationEndpoint;
    }

    public String getTokenEndpoint() {
        return tokenEndpoint;
    }

    public void setTokenEndpoint(String tokenEndpoint) {
        this.tokenEndpoint = tokenEndpoint;
    }

    public String getIntrospectionEndpoint() {
        return introspectionEndpoint;
    }

    public void setIntrospectionEndpoint(String introspectionEndpoint) {
        this.introspectionEndpoint = introspectionEndpoint;
    }

    public String getEndSessionEndpoint() {
        return endSessionEndpoint;
    }

    public void setEndSessionEndpoint(String endSessionEndpoint) {
        this.endSessionEndpoint = endSessionEndpoint;
    }

    public String getJwksUri() {
        return jwksUri;
    }

    public void setJwksUri(String jwksUri) {
        this.jwksUri = jwksUri;
    }

    public List<String> getGrantTypesSupported() {
        return grantTypesSupported;
    }

    public void setGrantTypesSupported(List<String> grantTypesSupported) {
        this.grantTypesSupported = grantTypesSupported;
    }

    public List<String> getResponseTypesSupported() {
        return responseTypesSupported;
    }

    public void setResponseTypesSupported(List<String> responseTypesSupported) {
        this.responseTypesSupported = responseTypesSupported;
    }

    public List<String> getResponseModesSupported() {
        return responseModesSupported;
    }

    public void setResponseModesSupported(List<String> responseModesSupported) {
        this.responseModesSupported = responseModesSupported;
    }

    public String getRegistrationEndpoint() {
        return registrationEndpoint;
    }

    public void setRegistrationEndpoint(String registrationEndpoint) {
        this.registrationEndpoint = registrationEndpoint;
    }

    public List<String> getTokenEndpointAuthMethodsSupported() {
        return tokenEndpointAuthMethodsSupported;
    }

    public void setTokenEndpointAuthMethodsSupported(List<String> tokenEndpointAuthMethodsSupported) {
        this.tokenEndpointAuthMethodsSupported = tokenEndpointAuthMethodsSupported;
    }

    public List<String> getTokenEndpointAuthSigningAlgValuesSupported() {
        return tokenEndpointAuthSigningAlgValuesSupported;
    }

    public void setTokenEndpointAuthSigningAlgValuesSupported(List<String> tokenEndpointAuthSigningAlgValuesSupported) {
        this.tokenEndpointAuthSigningAlgValuesSupported = tokenEndpointAuthSigningAlgValuesSupported;
    }

    public List<String> getScopesSupported() {
        return scopesSupported;
    }

    public void setScopesSupported(List<String> scopesSupported) {
        this.scopesSupported = scopesSupported;
    }

    public String getResourceRegistrationEndpoint() {
        return resourceRegistrationEndpoint;
    }

    public void setResourceRegistrationEndpoint(String resourceRegistrationEndpoint) {
        this.resourceRegistrationEndpoint = resourceRegistrationEndpoint;
    }

    public String getPermissionEndpoint() {
        return permissionEndpoint;
    }

    public void setPermissionEndpoint(String permissionEndpoint) {
        this.permissionEndpoint = permissionEndpoint;
    }

    public String getPolicyEndpoint() {
        return policyEndpoint;
    }

    public void setPolicyEndpoint(String policyEndpoint) {
        this.policyEndpoint = policyEndpoint;
    }

}
