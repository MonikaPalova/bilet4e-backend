package bg.bilet4e.prototype.user.customer;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Component;

@Component
public interface CustomerService {

    List<Customer> fetchAll();

    Customer create(String username, String password);

    Optional<Customer> fetchById(int customerId);

    Optional<Customer> fetchByUsername(String username);

    void update(Customer customer);
}
