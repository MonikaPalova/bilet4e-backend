package bg.bilet4e.prototype.security.rest;

import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import bg.bilet4e.prototype.security.JwtTokenUtil;
import bg.bilet4e.prototype.security.JwtUserDetailsService;
import bg.bilet4e.prototype.security.user.User;
import bg.bilet4e.prototype.security.user.UserService;
import bg.bilet4e.prototype.security.user.UserType;

@Controller
public class SecurityController {

    private static final Logger LOGGER = LoggerFactory.getLogger(SecurityController.class);

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private JwtTokenUtil jwtTokenUtil;

    @Autowired
    private JwtUserDetailsService userDetailsService;

    @Autowired
    private UserService userService;

    @PostMapping("/authenticate")
    public ResponseEntity<?> authenticate(
            @Valid @RequestBody AuthenticationRequest authenticationRequest) {
        String username = authenticationRequest.getUsername();
        String password = authenticationRequest.getPassword();
        authenticationManager
                .authenticate(new UsernamePasswordAuthenticationToken(username, password));

        final UserDetails userDetails = userDetailsService
                .loadUserByUsername(authenticationRequest.getUsername());
        final String token = jwtTokenUtil.generateToken(userDetails);

        int userId = userService.fetchByUsername(username).getId();
        return ResponseEntity.ok(new AuthenticationResponse(userId, token));
    }

    // TODO User to UserRequest if changes to user
    @PostMapping("/register")
    public ResponseEntity<?> saveUser(@Valid @RequestBody User user) throws Exception {
        User createdUser = userDetailsService.save(user);
        String username = createdUser.getUsername();
        int id = createdUser.getId();
        UserType type = createdUser.getType();

        LOGGER.info("registered user with id [{}] and username [{}]", id, username);
        return ResponseEntity.ok(new RegisterResponse(id, username, type));
    }
}
