package bg.bilet4e.prototype.user.owner.rest;

import java.util.List;

import bg.bilet4e.prototype.shop.rest.ShopDTO;

public class OwnerDTO {

    private final int id;

    private final String username;

    private final List<ShopDTO> favoriteShops;

    private final List<ShopDTO> shops;

    public OwnerDTO(int id, String username, List<ShopDTO> favoriteShops, List<ShopDTO> shops) {
        this.id = id;
        this.username = username;
        this.favoriteShops = favoriteShops;
        this.shops = shops;
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

    public List<ShopDTO> getShops() {
        return shops;
    }
}
