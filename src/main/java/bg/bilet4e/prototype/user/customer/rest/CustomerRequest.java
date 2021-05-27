package bg.bilet4e.prototype.user.customer.rest;

import javax.validation.constraints.NotBlank;

class CustomerRequest {

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
