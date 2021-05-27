package bg.bilet4e.prototype.shop.rest;

import javax.validation.constraints.NotBlank;

public class ShopRequest {

    @NotBlank
    private String ownerId;

    @NotBlank
    private String name;

    public ShopRequest(String ownerId, String name) {
        this.ownerId = ownerId;
        this.name = name;
    }

    public String getOwnerId() {
        return ownerId;
    }

    public String getName() {
        return name;
    }

    public void setOwnerId(String ownerId) {
        this.ownerId = ownerId;
    }

    public void setName(String name) {
        this.name = name;
    }
}
