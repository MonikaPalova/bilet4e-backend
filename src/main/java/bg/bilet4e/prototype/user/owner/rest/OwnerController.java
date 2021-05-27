package bg.bilet4e.prototype.user.owner.rest;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import bg.bilet4e.prototype.user.owner.Owner;
import bg.bilet4e.prototype.user.owner.OwnerService;

@RestController
@RequestMapping(value = OwnerController.API_BASE_PATH)
class OwnerController {

    static final String API_BASE_PATH = "api/v1/owners";

    private final OwnerService ownerService;
    private final OwnerDTOConverter converter;

    @Autowired
    OwnerController(OwnerService ownerService, OwnerDTOConverter converter) {
        this.ownerService = ownerService;
        this.converter = converter;
    }

    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    ResponseEntity<?> getOwners() {
        List<Owner> owners = ownerService.fetchAll();

        owners.forEach(owner -> System.out.println(owner));
        owners.forEach(converter::toDTO);
        return ResponseEntity.ok(owners);
    }

    @GetMapping(path = "/{ownerId}", produces = MediaType.APPLICATION_JSON_VALUE)
    ResponseEntity<?> getOwner(@PathVariable final int ownerId) {
        Optional<Owner> owner = ownerService.fetchById(ownerId);
        if (owner.isEmpty()) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND,
                    "Owner with id [" + ownerId + "] doesn't exist.");
        }

        OwnerDTO dto = converter.toDTO(owner.get());
        return ResponseEntity.ok(dto);
    }
}