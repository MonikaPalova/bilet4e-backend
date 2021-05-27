package bg.bilet4e.prototype.shop.rest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import bg.bilet4e.prototype.shop.Shop;
import bg.bilet4e.prototype.user.owner.Owner;
import bg.bilet4e.prototype.user.owner.rest.OwnerDTO;
import bg.bilet4e.prototype.user.owner.rest.OwnerDTOConverter;

@Component
public class ShopDTOConverter {

    private final OwnerDTOConverter ownerConverter;

    @Autowired
    ShopDTOConverter(OwnerDTOConverter ownerConverter) {
        this.ownerConverter = ownerConverter;
    }

    ShopDTO toDTO(Shop shop) {
        int id = shop.getId();
        String name = shop.getName();
        Owner owner = shop.getOwner();
        OwnerDTO ownerDto = ownerConverter.toDTO(owner);

        return new ShopDTO(id, name, ownerDto);
    }

}
