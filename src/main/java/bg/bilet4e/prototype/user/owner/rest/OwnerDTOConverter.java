package bg.bilet4e.prototype.user.owner.rest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import bg.bilet4e.prototype.user.owner.Owner;

@Component
public class OwnerDTOConverter {

    @Autowired
    OwnerDTOConverter() {

    }

    public OwnerDTO toDTO(Owner owner) {
        int id = owner.getId();
        String username = owner.getUsername();

        return new OwnerDTO(id, username);
    }
}
