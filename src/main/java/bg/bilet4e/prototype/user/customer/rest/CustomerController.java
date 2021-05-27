package bg.bilet4e.prototype.user.customer.rest;

import java.util.List;
import java.util.Optional;

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
import bg.bilet4e.prototype.shop.rest.ShopDTOConverter;
import bg.bilet4e.prototype.user.customer.Customer;
import bg.bilet4e.prototype.user.customer.CustomerService;

@RestController
@RequestMapping(value = CustomerController.API_BASE_PATH)
class CustomerController {

    static final String API_BASE_PATH = "api/v1/customers";

    private final CustomerService customerService;
    private final ShopService shopService;
    private final CustomerDTOConverter customerConverter;
    private final ShopDTOConverter shopConverter;

    @Autowired
    CustomerController(CustomerService customerService, CustomerDTOConverter converter,
            ShopDTOConverter shopConverter, ShopService shopService) {
        this.customerService = customerService;
        this.shopService = shopService;
        this.customerConverter = converter;
        this.shopConverter = shopConverter;
    }

    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    ResponseEntity<?> getCustomers() {
        List<Customer> customers = customerService.fetchAll();

        return ResponseEntity.ok(customerConverter.toDTOs(customers));
    }

    @GetMapping(path = "/{customerId}", produces = MediaType.APPLICATION_JSON_VALUE)
    ResponseEntity<?> getCustomer(@PathVariable final int customerId) {
        Optional<Customer> customer = customerService.fetchById(customerId);
        if (customer.isEmpty()) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND,
                    "Customer with id [" + customerId + "] doesn't exist.");
        }

        CustomerDTO dto = customerConverter.toDTO(customer.get());
        return ResponseEntity.ok(dto);
    }

    @GetMapping(path = "/{customerId}/favoriteShops", produces = MediaType.APPLICATION_JSON_VALUE)
    ResponseEntity<?> getFavoriteShops(@PathVariable final int customerId) {
        Customer customer = customerService.fetchById(customerId)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND,
                        "Customer with id [" + customerId + "] doesn't exist."));

        List<Shop> shops = customer.getFavoriteShops();
        return ResponseEntity.ok(shopConverter.toDTOs(shops));
    }

    @PostMapping(path = "/{customerId}/favoriteShops/{shopId}", produces = MediaType.APPLICATION_JSON_VALUE)
    ResponseEntity<?> addFavoriteShop(@PathVariable final int customerId,
            @PathVariable final int shopId) {
        Customer customer = customerService.fetchById(customerId)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND,
                        "Customer with id [" + customerId + "] doesn't exist."));

        Shop shop = shopService.fetchById(shopId)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND,
                        "Shop with id [" + shopId + "] doesn't exist."));

        customer.addFavoriteShop(shop);
        customerService.update(customer);

        return ResponseEntity.ok().build();
    }

    @DeleteMapping(path = "/{customerId}/favoriteShops/{shopId}", produces = MediaType.APPLICATION_JSON_VALUE)
    ResponseEntity<?> removeFavoriteShop(@PathVariable final int customerId,
            @PathVariable final int shopId) {
        Customer customer = customerService.fetchById(customerId)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND,
                        "Customer with id [" + customerId + "] doesn't exist."));

        customer.removeFavoriteShop(shopId);
        customerService.update(customer);

        return ResponseEntity.ok().build();
    }
}