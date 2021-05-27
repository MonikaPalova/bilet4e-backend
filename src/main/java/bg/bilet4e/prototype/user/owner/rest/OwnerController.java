package bg.bilet4e.prototype.user.owner.rest;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import bg.bilet4e.prototype.shop.rest.ShopDTO;
import bg.bilet4e.prototype.user.owner.Owner;
import bg.bilet4e.prototype.user.owner.OwnerService;

@RestController
@RequestMapping(value = OwnerController.API_BASE_PATH)
class OwnerController {

    static final String API_BASE_PATH = "api/v1/owners";

    private final OwnerService ownerService;
    private final OwnerDTOConverter ownerConverter;

    @Autowired
    OwnerController(OwnerService ownerService, OwnerDTOConverter ownerConverter) {
        this.ownerService = ownerService;
        this.ownerConverter = ownerConverter;
    }

    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    ResponseEntity<?> getOwners() {
        List<Owner> owners = ownerService.fetchAll();

        return ResponseEntity.ok(ownerConverter.toDTOs(owners));
    }

    @GetMapping(path = "/{ownerId}", produces = MediaType.APPLICATION_JSON_VALUE)
    ResponseEntity<?> getOwner(@PathVariable final int ownerId) {
        Owner owner = ownerService.fetchById(ownerId) //
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND,
                        "Owner with id [" + ownerId + "] doesn't exist."));

        return ResponseEntity.ok(ownerConverter.toDTO(owner));
    }

    @GetMapping(path = "/{ownerId}/shops", produces = MediaType.APPLICATION_JSON_VALUE)
    ResponseEntity<?> getOwnerShops(@PathVariable final int ownerId) {
        Owner owner = ownerService.fetchById(ownerId) //
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND,
                        "Owner with id [" + ownerId + "] doesn't exist."));

        List<ShopDTO> shopDtos = ownerConverter.toDTO(owner).getShops();
        return ResponseEntity.ok(shopDtos);
    }
}