package bg.bilet4e.prototype.security;

import java.io.IOException;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

@Component
public class JwtTokenFilter extends OncePerRequestFilter {

    private static final String AUTHORIZATION_HEADER = "Authorization";
    private static final String AUTHORIZATION_TYPE = "Bearer ";

    @Autowired
    private JwtUserDetailsService jwtUserDetailsService;

    @Autowired
    private JwtTokenUtil jwtTokenUtil;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response,
            FilterChain chain) throws ServletException, IOException {
        final String requestAuthorizationHeader = request.getHeader(AUTHORIZATION_HEADER);

        String jwtToken = extractAccessTokenFromAuthorizationHeader(requestAuthorizationHeader);
        String username = extractUsernameFromAccessToken(jwtToken);

        if (username != null && SecurityContextHolder.getContext().getAuthentication() == null) {

            UserDetails userDetails = this.jwtUserDetailsService.loadUserByUsername(username);

            if (jwtTokenUtil.validateToken(jwtToken, userDetails)) {

                UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken = new UsernamePasswordAuthenticationToken(
                        userDetails, null, userDetails.getAuthorities());
                usernamePasswordAuthenticationToken
                        .setDetails(new WebAuthenticationDetailsSource().buildDetails(request));

                SecurityContextHolder.getContext()
                        .setAuthentication(usernamePasswordAuthenticationToken);
            }
        }

        response.setHeader("Access-Control-Allow-Origin", "*");
        response.setHeader("Access-Control-Allow-Methods", "GET, POST, PUT, DELETE, OPTIONS"); // TODO
        // clean
        response.setHeader("Access-Control-Max-Age", "3600");
        response.setHeader("Access-Control-Allow-Headers",
                "authorization, content-type, xsrf-token");
        response.addHeader("Access-Control-Expose-Headers", "authorization,xsrf-token");

        chain.doFilter(request, response);
    }

    private String extractAccessTokenFromAuthorizationHeader(String requestAuthorizationHeader) {
        if (requestAuthorizationHeader == null
                || !requestAuthorizationHeader.startsWith(AUTHORIZATION_TYPE)) {
            return null;
        }
        return requestAuthorizationHeader.substring(AUTHORIZATION_TYPE.length());
    }

    private String extractUsernameFromAccessToken(String accessToken) {
        return accessToken == null ? null : jwtTokenUtil.getUsernameFromToken(accessToken);
    }

}