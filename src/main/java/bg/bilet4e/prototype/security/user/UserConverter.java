package bg.bilet4e.prototype.security.user;

import org.springframework.stereotype.Component;

import bg.bilet4e.prototype.user.customer.Customer;
import bg.bilet4e.prototype.user.customer.CustomerType;

@Component
public class UserConverter {

    public User toUser(Customer customer) {
        int id = customer.getId();
        String username = customer.getUsername();
        String password = customer.getPassword();
        CustomerType customerType = customer.getType();

        UserType userType = switch (customerType) {
            case CUSTOMER -> UserType.CUSTOMER;
            case CUSTOMER_AND_OWNER -> UserType.OWNER;
        };

        return new User(id, username, password, userType);
    }
}
