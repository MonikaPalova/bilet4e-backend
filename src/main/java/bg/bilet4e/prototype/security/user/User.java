package bg.bilet4e.prototype.security.user;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

public class User {

    @NotNull
    private int id;

    @NotBlank
    private String username;

    @NotBlank
    private String password;

    @NotNull
    private UserType type;

    User(int id, String username, String password, UserType type) {
        this.id = id;
        this.username = username;
        this.password = password;
        this.type = type;
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    public UserType getType() {
        return type;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setPassword(String password) {
        this.password = password;
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
