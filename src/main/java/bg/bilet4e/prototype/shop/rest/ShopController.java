package bg.bilet4e.prototype.shop.rest;

import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import bg.bilet4e.prototype.shop.Shop;
import bg.bilet4e.prototype.shop.ShopService;

@RestController
@RequestMapping(value = ShopController.API_BASE_PATH)
class ShopController {

    static final String API_BASE_PATH = "api/v1/shops";

    private final ShopService shopService;
    private final ShopDTOConverter converter;

    @Autowired
    ShopController(ShopService shopService, ShopDTOConverter converter) {
        this.shopService = shopService;
        this.converter = converter;
    }

    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    ResponseEntity<?> getShops() {
        List<Shop> shops = shopService.fetchAll();

        shops.forEach(converter::toDTO);
        return ResponseEntity.ok(shops);
    }

    @GetMapping(path = "/{shopId}", produces = MediaType.APPLICATION_JSON_VALUE)
    ResponseEntity<?> getShop(@PathVariable final int shopId) {
        Optional<Shop> shop = shopService.fetchById(shopId);
        if (shop.isEmpty()) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND,
                    "Shop with id [" + shopId + "] doesn't exist.");
        }

        ShopDTO dto = converter.toDTO(shop.get());
        return ResponseEntity.ok(dto);
    }

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> create(@Valid @RequestBody ShopRequest shopRequest) {
        int ownerId = shopRequest.getOwnerId();
        String name = shopRequest.getName();
        Shop createdShop = shopService.create(ownerId, name);

        return ResponseEntity.ok(converter.toDTO(createdShop));
    }

}