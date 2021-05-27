package bg.bilet4e.prototype.user.customer.rest;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import bg.bilet4e.prototype.user.customer.Customer;
import bg.bilet4e.prototype.user.customer.CustomerService;

@RestController
class CustomerController {

    private final CustomerService customerService;
    private final CustomerDTOConverter converter;

    @Autowired
    CustomerController(CustomerService customerService, CustomerDTOConverter converter) {
        this.customerService = customerService;
        this.converter = converter;
    }

    @GetMapping("/customers")
    ResponseEntity<?> getCustomers() {
        List<Customer> customers = customerService.fetchAll();

        customers.forEach(customer -> System.out.println(customer));
        customers.forEach(converter::toDTO);
        return ResponseEntity.ok(customers);
    }

    @GetMapping("/customers/{customerId}")
    ResponseEntity<?> getCustomer(@PathVariable final int customerId) {
        Optional<Customer> customer = customerService.fetchById(customerId);
        if (customer.isEmpty()) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND,
                    "Customer with id [" + customerId + "] doesn't exist.");
        }

        CustomerDTO dto = converter.toDTO(customer.get());
        return ResponseEntity.ok(dto);
    }
}