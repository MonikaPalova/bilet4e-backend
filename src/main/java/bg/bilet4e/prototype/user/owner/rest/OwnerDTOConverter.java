package bg.bilet4e.prototype.user.owner.rest;

import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import bg.bilet4e.prototype.shop.Shop;
import bg.bilet4e.prototype.shop.rest.ShopDTO;
import bg.bilet4e.prototype.shop.rest.ShopDTOConverter;
import bg.bilet4e.prototype.user.owner.Owner;

@Component
public class OwnerDTOConverter {

    private final ShopDTOConverter shopConverter;

    @Autowired
    OwnerDTOConverter(ShopDTOConverter shopConverter) {
        this.shopConverter = shopConverter;
    }

    public OwnerDTO toDTO(Owner owner) {
        int id = owner.getId();
        String username = owner.getUsername();

        List<Shop> favoriteShops = owner.getFavoriteShops();
        List<ShopDTO> favoriteShopDtos = shopConverter.toDTOs(favoriteShops);

        List<Shop> shops = owner.getShops();
        List<ShopDTO> shopDtos = shopConverter.toDTOs(shops);

        return new OwnerDTO(id, username, favoriteShopDtos, shopDtos);
    }

    public List<OwnerDTO> toDTOs(Collection<Owner> owners) {
        return owners.stream() //
                .map(this::toDTO)//
                .collect(Collectors.toList());
    }
}
