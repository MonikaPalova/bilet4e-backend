package bg.bilet4e.prototype.shop.rest;

import bg.bilet4e.prototype.user.owner.rest.OwnerDTO;

public class ShopDTO {

    private int id;

    private String name;

//    private Address address;
//
//    private Coordinates coordinates;

//    private WeeklyWorkingTime workTime;

    private OwnerDTO owner;

//    private List<Image> images;

    public ShopDTO(int id, String name, OwnerDTO owner) {
        this.id = id;
        this.name = name;
        this.owner = owner;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public OwnerDTO getOwner() {
        return owner;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setOwner(OwnerDTO owner) {
        this.owner = owner;
    }

}