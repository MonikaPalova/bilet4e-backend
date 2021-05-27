package bg.bilet4e.prototype.user.owner;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.OneToMany;

import bg.bilet4e.prototype.shop.Shop;
import bg.bilet4e.prototype.user.customer.Customer;
import bg.bilet4e.prototype.user.customer.CustomerType;

@Entity
public class Owner extends Customer {

    @OneToMany(targetEntity = Shop.class)
    private final List<Shop> shops = new ArrayList<>();

    public Owner() {
        this.type = CustomerType.CUSTOMER_AND_OWNER;
        // used by Spring
    }

    public Owner(String username, String password) {
        super(username, password);
        this.type = CustomerType.CUSTOMER_AND_OWNER;
    }

    public List<Shop> getShops() {
        return shops;
    }

    public void removeShop(int shopId) {
        shops.removeIf(shop -> shop.getId() == shopId);
    }

    public void addShop(Shop shop) {
        shops.add(shop);
    }
}
