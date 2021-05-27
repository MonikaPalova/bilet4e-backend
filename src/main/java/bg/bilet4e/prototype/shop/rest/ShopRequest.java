package bg.bilet4e.prototype.shop.rest;

import javax.validation.constraints.NotBlank;

import com.sun.istack.NotNull;

public class ShopRequest {

    @NotNull
    private int ownerId;

    @NotBlank
    private String name;

    public ShopRequest(int ownerId, String name) {
        this.ownerId = ownerId;
        this.name = name;
    }

    public int getOwnerId() {
        return ownerId;
    }

    public String getName() {
        return name;
    }

    public void setOwnerId(int ownerId) {
        this.ownerId = ownerId;
    }

    public void setName(String name) {
        this.name = name;
    }
}
