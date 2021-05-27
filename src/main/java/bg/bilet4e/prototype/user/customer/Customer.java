package bg.bilet4e.prototype.user.customer;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.validation.constraints.NotBlank;

import org.springframework.beans.factory.annotation.Value;

import bg.bilet4e.prototype.shop.Shop;

@Entity
public class Customer {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;

    @NotBlank
    @Column(unique = true)
    private String username;

    @NotBlank
    private String encryptedPassword;

    protected CustomerType type;

    @Value("-1")
    private int ticketId;

    @OneToMany(targetEntity = Shop.class)
    private final List<Shop> favoriteShops = new ArrayList<>();

    public Customer() {
        this.type = CustomerType.CUSTOMER;
    }

    public Customer(String username, String password) {
        this.username = username;
        this.encryptedPassword = password;
        this.type = CustomerType.CUSTOMER;
    }

    public int getId() {
        return id;
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return encryptedPassword;
    }

    public CustomerType getType() {
        return type;
    }
}
