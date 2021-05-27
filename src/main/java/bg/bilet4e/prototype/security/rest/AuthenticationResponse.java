package bg.bilet4e.prototype.security.rest;

import java.io.Serializable;

import bg.bilet4e.prototype.security.user.UserType;

public class AuthenticationResponse implements Serializable {

    private static final long serialVersionUID = -8091879091924046844L;

    private final int userId;
    private final String token;
    private final UserType type;

    public AuthenticationResponse(int userId, UserType type, String token) {
        this.userId = userId;
        this.token = token;
        this.type = type;
    }

    public int getUserId() {
        return userId;
    }

    public static long getSerialversionuid() {
        return serialVersionUID;
    }

    public String getToken() {
        return token;
    }

    public UserType getType() {
        return type;
    }
}