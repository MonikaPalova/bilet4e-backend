package bg.bilet4e.prototype.security;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import bg.bilet4e.prototype.security.user.User;
import bg.bilet4e.prototype.security.user.UserService;
import bg.bilet4e.prototype.user.customer.Customer;

@Component
public class JwtUserDetailsService implements UserDetailsService {

    @Autowired
    private UserService userService;

    @Autowired
    private PasswordEncoder bcryptEncoder;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = fetchUser(username);
        String password = user.getPassword();

        return new org.springframework.security.core.userdetails.User( //
                username, password, new ArrayList<>());
    }

    public Customer save(User user) {
        user.setPassword(bcryptEncoder.encode(user.getPassword()));

        return userService.save(user);
    }

    private User fetchUser(String username) {
        return userService.fetchByUsername(username);
    }
}
