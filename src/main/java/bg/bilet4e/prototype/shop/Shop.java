package bg.bilet4e.prototype.shop;

import java.util.EnumMap;
import java.util.Map;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.validation.Valid;
import javax.validation.constraints.NotBlank;

import bg.bilet4e.prototype.ticket.TicketType;
import bg.bilet4e.prototype.user.owner.Owner;

@Entity
public class Shop {

    @Id
    @Column(name = "ID")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;

    @NotBlank
    @Column(name = "NAME")
    private String name;

//    @Valid
//    @Column(name = "ADDRESS")
//    private Address address;

    @Valid
    @Column(name = "COORDINATES")
    private Coordinates coordinates;

//    @Valid
//    @Column(name = "WORK_TIME")
//    private WeeklyWorkingTime workTime;

    @Column(name = "STOCK")
    private EnumMap<TicketType, Integer> stock;

    @ManyToOne
    @JoinColumn(name = "OWNER_ID")
    private Owner owner;

    public Shop() {
        // used by Spring
    }

    public Shop(String name, Owner owner, Coordinates coordinates) {
        this.name = name;
        this.owner = owner;
        this.coordinates = coordinates;
        this.stock = new EnumMap<>(TicketType.class);
        this.stock.putAll(
                Map.of(TicketType.ONE_TIME, 0, TicketType.ONE_DAY, 0, TicketType.THREE_DAYS, 0));
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public Owner getOwner() {
        return owner;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setOwner(Owner owner) {
        this.owner = owner;
    }

    public Coordinates getCoordinates() {
        return coordinates;
    }

    public void setCoordinates(Coordinates coordinates) {
        this.coordinates = coordinates;
    }

    public EnumMap<TicketType, Integer> getStock() {
        return stock;
    }

    public void setStock(EnumMap<TicketType, Integer> stock) {
        this.stock = stock;
    }

    public void updateStock(TicketType type, int quantity) {
        this.stock.put(type, quantity);
    }

//    @OneToMany(targetEntity = Image.class)
//    @Column(name = "IMAGES")
//    private List<Image> images;

}