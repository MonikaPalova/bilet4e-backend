package bg.bilet4e.prototype.security.rest;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import bg.bilet4e.prototype.security.user.UserType;

public class RegisterResponse {

    @NotNull
    private int id;

    @NotBlank
    private String username;

    @NotNull
    private UserType type;

    public RegisterResponse(int id, String username, UserType type) {
        this.id = id;
        this.username = username;
        this.type = type;
    }

    public String getUsername() {
        return username;
    }

    public UserType getType() {
        return type;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setType(UserType type) {
        this.type = type;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}
