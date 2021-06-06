package bg.bilet4e.prototype.user.customer.rest;

import java.security.Principal;
import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import bg.bilet4e.prototype.shop.Shop;
import bg.bilet4e.prototype.shop.ShopService;
import bg.bilet4e.prototype.shop.rest.ShopDTO;
import bg.bilet4e.prototype.user.customer.Customer;
import bg.bilet4e.prototype.user.customer.CustomerService;

@RestController
@RequestMapping(value = CustomerController.API_BASE_PATH)
class CustomerController {

    static final String API_BASE_PATH = "api/v1/customers";

    private static final Logger LOGGER = LoggerFactory.getLogger(CustomerController.class);

    private static final String CUSTOMER_NOT_EXIST_ERR_MSG = "Customer with id [%s] doesn't exist.";

    private final CustomerService customerService;
    private final ShopService shopService;
    private final CustomerDTOConverter customerConverter;

    @Autowired
    CustomerController(CustomerService customerService, CustomerDTOConverter converter, ShopService shopService) {
        this.customerService = customerService;
        this.shopService = shopService;
        this.customerConverter = converter;
    }

    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> getCustomers() {
        LOGGER.info("performing GET request /api/v1/customers");
        List<Customer> customers = customerService.fetchAll();

        return ResponseEntity.ok(customerConverter.toDTOs(customers));
    }

    @GetMapping(path = "/{customerId}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> getCustomer(@PathVariable final int customerId) {
        LOGGER.info("performing GET request /api/v1/customers/{}", customerId);
        Optional<Customer> customer = customerService.fetchById(customerId);
        if (customer.isEmpty()) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND,
                    String.format(CUSTOMER_NOT_EXIST_ERR_MSG, customerId));
        }

        CustomerDTO dto = customerConverter.toDTO(customer.get());
        return ResponseEntity.ok(dto);
    }

    @GetMapping(path = "/{customerId}/favoriteShops", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> getFavoriteShops(@PathVariable final int customerId, Principal principal) {
        LOGGER.info("performing GET request /api/v1/customers/{}/favoriteShops", customerId);
        Customer customer = customerService.fetchById(customerId)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND,
                        String.format(CUSTOMER_NOT_EXIST_ERR_MSG, customerId)));

        List<ShopDTO> shopDtos = customerConverter.toDTO(customer).getFavoriteShops();
        return ResponseEntity.ok(shopDtos);
    }

    @PostMapping(path = "/{customerId}/favoriteShops/{shopId}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> addFavoriteShop(@PathVariable final int customerId, @PathVariable final int shopId,
            Principal principal) {
        LOGGER.info("performing POST request /api/v1/customers/{}/favoriteShops/{}", customerId, shopId);
        Customer customer = customerService.fetchById(customerId)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND,
                        String.format(CUSTOMER_NOT_EXIST_ERR_MSG, customerId)));

        Shop shop = shopService.fetchById(shopId).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND,
                "Shop with id [" + shopId + "] doesn't exist."));

        customer.addFavoriteShop(shop);
        customerService.update(customer);

        return ResponseEntity.ok().build();
    }

    @DeleteMapping(path = "/{customerId}/favoriteShops/{shopId}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> removeFavoriteShop(@PathVariable final int customerId, @PathVariable final int shopId,
            Principal principal) {
        LOGGER.info("performing DELETE request /api/v1/customers/{}/favoriteShops/{}", customerId, shopId);
        Customer customer = customerService.fetchById(customerId)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND,
                        String.format(CUSTOMER_NOT_EXIST_ERR_MSG, customerId)));

        customer.removeFavoriteShop(shopId);
        customerService.update(customer);

        return ResponseEntity.ok().build();
    }
}