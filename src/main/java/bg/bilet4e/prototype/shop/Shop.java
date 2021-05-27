package bg.bilet4e.prototype.shop;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.validation.constraints.NotBlank;

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
//
//    @Valid
//    @Column(name = "COORDINATES")
//    private Coordinates coordinates;

//    @Valid
//    @Column(name = "WORK_TIME")
//    private WeeklyWorkingTime workTime;

    @ManyToOne
    @JoinColumn(name = "OWNER_ID")
    private Owner owner;

    public Shop() {
        //used by Spring
    }
    
    public Shop(String name, Owner owner) {
        this.name = name;
        this.owner = owner;
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

//    @OneToMany(targetEntity = Image.class)
//    @Column(name = "IMAGES")
//    private List<Image> images;
    
    
}