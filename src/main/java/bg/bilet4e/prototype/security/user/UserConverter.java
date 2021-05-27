package bg.bilet4e.prototype.security.user;

import bg.bilet4e.prototype.user.customer.Customer;
import bg.bilet4e.prototype.user.customer.CustomerType;
import org.springframework.stereotype.Component;

@Component
public class UserConverter {

    public User toUser(Customer customer) {
        String username = customer.getUsername();
        String password = customer.getPassword();
        CustomerType customerType = customer.getType();

        UserType userType = switch (customerType) {
            case CUSTOMER -> UserType.CUSTOMER;
            case CUSTOMER_AND_OWNER -> UserType.OWNER;
        };

        return new User(username, password, userType);
    }
}
