package bg.bilet4e.prototype.user.owner.rest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import bg.bilet4e.prototype.user.owner.Owner;

@Component
class OwnerDTOConverter {

    @Autowired
    OwnerDTOConverter() {

    }

    OwnerDTO toDTO(Owner owner) {
        int id = owner.getId();
        String username = owner.getUsername();

        return new OwnerDTO(id, username);
    }
}
