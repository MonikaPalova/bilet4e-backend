package bg.bilet4e.prototype.shop.rest;

import java.util.Map;

import bg.bilet4e.prototype.shop.Coordinates;
import bg.bilet4e.prototype.ticket.TicketType;

public class ShopDTO {

    private int id;

    private String name;

//    private Address address;
//
    private Coordinates coordinates; // TODO create dto

//    private WeeklyWorkingTime workTime;

    private int ownerId;

//    private List<Image> images;

    private Map<TicketType, Integer> stock;
    
    public ShopDTO(int id, String name, int ownerId, Coordinates coordinates,Map<TicketType, Integer> stock) {
        this.id = id;
        this.name = name;
        this.ownerId = ownerId;
        this.coordinates = coordinates;
        this.stock=stock;
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

    public Map<TicketType, Integer> getStock() {
        return stock;
    }

    public void setStock(Map<TicketType, Integer> stock) {
        this.stock = stock;
    }
}