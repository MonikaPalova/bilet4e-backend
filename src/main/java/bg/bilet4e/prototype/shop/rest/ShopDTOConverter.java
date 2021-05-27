package bg.bilet4e.prototype.shop.rest;

import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import bg.bilet4e.prototype.shop.Shop;
import bg.bilet4e.prototype.user.owner.Owner;

@Component
public class ShopDTOConverter {

    @Autowired
    ShopDTOConverter() {
    }

    ShopDTO toDTO(Shop shop) {
        int id = shop.getId();
        String name = shop.getName();
        Owner owner = shop.getOwner();

        return new ShopDTO(id, name, owner.getId(), shop.getCoordinates());
    }

    public List<ShopDTO> toDTOs(Collection<Shop> shops) {
        return shops.stream() //
                .map(this::toDTO)//
                .collect(Collectors.toList());
    }
}
