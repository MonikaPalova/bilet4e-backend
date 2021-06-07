package bg.bilet4e.prototype.shop.rest;

import java.util.List;
import java.util.Map;

import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import bg.bilet4e.prototype.shop.Coordinates;
import bg.bilet4e.prototype.shop.Shop;
import bg.bilet4e.prototype.shop.ShopService;
import bg.bilet4e.prototype.ticket.TicketType;

@RestController
@RequestMapping(value = ShopController.API_BASE_PATH)
@CrossOrigin(origins = { "http://localhost:4200", "https://bilet4e.herokuapp.com" })
class ShopController {

    static final String API_BASE_PATH = "api/v1/shops";

    private static final Logger LOGGER = LoggerFactory.getLogger(ShopController.class);

    private static final String SHOP_NOT_EXIST_ERR_MSG = "Shop with id [%s] doesn't exist.";

    private final ShopService shopService;
    private final ShopDTOConverter converter;

    @Autowired
    ShopController(ShopService shopService, ShopDTOConverter converter) {
        this.shopService = shopService;
        this.converter = converter;
    }

    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> getShops() {
        LOGGER.info("performing GET request /api/v1/shops");
        List<Shop> shops = shopService.fetchAll();

        return ResponseEntity.ok(converter.toDTOs(shops));
    }

    @GetMapping(path = "/{shopId}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> getShop(@PathVariable final int shopId) {
        LOGGER.info("performing GET request /api/v1/shops/{}", shopId);
        Shop shop = shopService.fetchById(shopId)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND,
                        String.format(SHOP_NOT_EXIST_ERR_MSG, shopId)));

        return ResponseEntity.ok(converter.toDTO(shop));
    }

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> create(@Valid @RequestBody ShopRequest shopRequest) {
        LOGGER.info("performing POST request /api/v1/shops");
        int ownerId = extractOwnerId(shopRequest);
        String name = shopRequest.getName();
        Coordinates coordinates = shopRequest.getCoordinates();
        Shop createdShop = shopService.create(ownerId, name, coordinates);

        LOGGER.info("created shop with id [{}] and ownerId [{}]", createdShop.getId(), ownerId);
        return ResponseEntity.ok(converter.toDTO(createdShop));
    }

    private int extractOwnerId(ShopRequest shopRequest) {
        String ownerId = shopRequest.getOwnerId();

        try {
            return Integer.parseInt(ownerId);
        } catch (NumberFormatException ex) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST,
                    "Invalid ownerId [" + ownerId + "]", ex);
        }
    }

    @DeleteMapping(path = "/{shopId}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> delete(@PathVariable final int shopId) {
        LOGGER.info("performing DELETE request /api/v1/shops/{}", shopId);
        shopService.deleteById(shopId);

        LOGGER.info("deleted shop with id [{}]", shopId);
        return ResponseEntity.ok().build();
    }

    @GetMapping(path = "/{shopId}/stock", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> getStock(@PathVariable final int shopId) {
        LOGGER.info("performing GET request /api/v1/shops/{}/stock", shopId);
        Shop shop = shopService.fetchById(shopId)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND,
                        String.format(SHOP_NOT_EXIST_ERR_MSG, shopId)));

        return ResponseEntity.ok(shop.getStock());
    }

    @PatchMapping(path = "/{shopId}/stock", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> updateStock(@PathVariable final int shopId,
            @RequestBody StockRequest stockRequest) {
        LOGGER.info("performing UPDATE request /api/v1/shops/{}/stock", shopId);
        Shop shop = shopService.fetchById(shopId)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND,
                        String.format(SHOP_NOT_EXIST_ERR_MSG, shopId)));

        Map<TicketType, Integer> newStock = stockRequest.getStock();
        newStock.forEach((type, quantity) -> shop.updateStock(type, quantity));
        shopService.update(shop);

        return ResponseEntity.ok().build();
    }

}
