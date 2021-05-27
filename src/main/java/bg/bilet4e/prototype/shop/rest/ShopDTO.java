package bg.bilet4e.prototype.shop.rest;

import bg.bilet4e.prototype.shop.Coordinates;

public class ShopDTO {

    private int id;

    private String name;

//    private Address address;
//
    private Coordinates coordinates; // TODO create dto

//    private WeeklyWorkingTime workTime;

    private int ownerId;

//    private List<Image> images;

    public ShopDTO(int id, String name, int ownerId, Coordinates coordinates) {
        this.id = id;
        this.name = name;
        this.ownerId = ownerId;
        this.coordinates = coordinates;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public int getOwnerId() {
        return ownerId;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setOwnerId(int ownerId) {
        this.ownerId = ownerId;
    }

    public Coordinates getCoordinates() {
        return coordinates;
    }

    public void setCoordinates(Coordinates coordinates) {
        this.coordinates = coordinates;
    }
}