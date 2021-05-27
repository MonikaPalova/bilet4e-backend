package bg.bilet4e.prototype.user.owner.rest;

import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

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

    public List<OwnerDTO> toDTOs(Collection<Owner> owners) {
        return owners.stream() //
                .map(this::toDTO)//
                .collect(Collectors.toList());
    }
}
