package bg.bilet4e.prototype.security.rest;

import javax.validation.Valid;

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
import bg.bilet4e.prototype.security.user.UserType;

@Controller
public class SecurityController {

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private JwtTokenUtil jwtTokenUtil;

    @Autowired
    private JwtUserDetailsService userDetailsService;

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

        return ResponseEntity.ok(new AuthenticationResponse(token));
    }

    // TODO User to UserRequest if changes to user
    @PostMapping("/register")
    public ResponseEntity<?> saveUser(@Valid @RequestBody User user) throws Exception {
        User createdUser = userDetailsService.save(user); // TODO use DTO converter here
        String username = createdUser.getUsername();
        int id = createdUser.getId();
        UserType type = createdUser.getType();

        return ResponseEntity.ok(new RegisterResponse(id, username, type)); // TODO dont return
                                                                            // password?
    }
}
