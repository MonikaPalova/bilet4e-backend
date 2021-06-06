package bg.bilet4e.prototype.shop.rest;

import javax.validation.Valid;
import javax.validation.constraints.NotBlank;

import bg.bilet4e.prototype.shop.Coordinates;

public class ShopRequest {

    @NotBlank
    private String ownerId;

    @NotBlank
    private String name;

    @Valid
    private Coordinates coordinates;

    public ShopRequest(String ownerId, String name, Coordinates coordinates) {
        this.ownerId = ownerId;
        this.name = name;
        this.coordinates = coordinates;
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

    public Coordinates getCoordinates() {
        return coordinates;
    }

    public void setCoordinates(Coordinates coordinates) {
        this.coordinates = coordinates;
    }
}
