package bg.bilet4e.prototype.user.customer;

import java.util.Optional;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Component;

@Component
public interface CustomerRepository extends CrudRepository<Customer, Integer> {

    Optional<Customer> findByUsername(String username);
}