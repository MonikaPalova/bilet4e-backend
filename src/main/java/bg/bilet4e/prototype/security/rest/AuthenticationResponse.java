package bg.bilet4e.prototype.security.rest;

import java.io.Serializable;

public class AuthenticationResponse implements Serializable {

    private static final long serialVersionUID = -8091879091924046844L;

    private final int userId;
    private final String accessToken;

    public AuthenticationResponse(int userId, String accessToken) {
        this.userId = userId;
        this.accessToken = accessToken;
    }

    public String getToken() {
        return this.accessToken;
    }

    public int getUserId() {
        return userId;
    }
}