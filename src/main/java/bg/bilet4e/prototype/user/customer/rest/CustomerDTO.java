package bg.bilet4e.prototype.user.customer.rest;

import java.util.List;

import bg.bilet4e.prototype.shop.rest.ShopDTO;

class CustomerDTO {

    private int id;

    private String username;
    
    private final List<ShopDTO> favoriteShops;

    public CustomerDTO(int id, String username, List<ShopDTO> favoriteShops) {
        this.favoriteShops = favoriteShops;
        this.id = id;
        this.username = username;
    }

    public int getId() {
        return id;
    }

    public String getUsername() {
        return username;
    }
    
    public List<ShopDTO> getFavoriteShops() {
        return favoriteShops;
    }
}
