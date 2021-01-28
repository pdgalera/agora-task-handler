package pe.indigital.proxy.request;

public class CreateSessionRequest {

    private String profileId;

    private String state;

    private CreateEventAttributesRequest attributes;

    public String getProfileId() {
        return profileId;
    }

    public void setProfileId(String profileId) {
        this.profileId = profileId;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public CreateEventAttributesRequest getAttributes() {
        return attributes;
    }

    public void setAttributes(CreateEventAttributesRequest attributes) {
        this.attributes = attributes;
    }
}
