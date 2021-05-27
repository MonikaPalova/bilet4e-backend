package bg.bilet4e.prototype.user.owner.rest;

import javax.validation.constraints.NotBlank;

class OwnerRequest {

    @NotBlank
    private String username;

    @NotBlank
    private String password;

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }
}
