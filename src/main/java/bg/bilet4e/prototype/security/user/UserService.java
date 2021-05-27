package bg.bilet4e.prototype.security.user;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ResponseStatusException;

import bg.bilet4e.prototype.user.customer.Customer;
import bg.bilet4e.prototype.user.customer.CustomerService;
import bg.bilet4e.prototype.user.owner.Owner;
import bg.bilet4e.prototype.user.owner.OwnerService;

@Component
public class UserService {

    private final CustomerService customerService;
    private final OwnerService ownerService;
    private final UserConverter userConverter;

    @Autowired
    public UserService(CustomerService customerService, OwnerService ownerService,
            UserConverter userConverter) {
        this.customerService = customerService;
        this.ownerService = ownerService;
        this.userConverter = userConverter;
    }

    public User fetchByUsername(String username) {
        Customer customer = customerService.fetchByUsername(username) //
                .orElseThrow(() -> new UsernameNotFoundException(
                        "User with username [" + username + "] not found"));

        User user = userConverter.toUser(customer);
        return user;
    }

    public User save(User user) {
        checkIfUsernameExists(user.getUsername());
        UserType userType = user.getType();

        Customer savedCustomer = switch (userType) {
            case CUSTOMER -> saveCustomer(user);
            case OWNER -> saveOwner(user);
            case ADMIN -> throw new UnsupportedOperationException("admin not supported yet");
        };

        return userConverter.toUser(savedCustomer);
    }

    private void checkIfUsernameExists(String username) {
        Optional<Customer> optional = customerService.fetchByUsername(username);

        if (optional.isPresent()) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST,
                    "Username [" + username + "] is already taken");
        }
    }

    private Customer saveCustomer(User user) {
        String username = user.getUsername();
        String password = user.getPassword();

        return customerService.create(username, password);
    }

    private Owner saveOwner(User user) {
        String username = user.getUsername();
        String password = user.getPassword();

        return ownerService.create(username, password);
    }
}
