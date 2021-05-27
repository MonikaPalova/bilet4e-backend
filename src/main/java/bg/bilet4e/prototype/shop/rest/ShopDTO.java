package bg.bilet4e.prototype.shop.rest;

public class ShopDTO {

    private int id;

    private String name;

//    private Address address;
//
//    private Coordinates coordinates;

//    private WeeklyWorkingTime workTime;

    private int ownerId;

//    private List<Image> images;

    public ShopDTO(int id, String name, int ownerId) {
        this.id = id;
        this.name = name;
        this.ownerId = ownerId;
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

}