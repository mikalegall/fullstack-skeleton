package fi.dev.academy.vaccinationdatabase.config;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;

public class ApiKeyAuthManager implements AuthenticationManager {

    private static final Logger log = LoggerFactory.getLogger(ApiKeyAuthManager.class);

    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        // Process comes here if the Key from API call was the right one. When using so called API Key there shouldn't
        // be any Value. If the right Key is enriched with some Value deny access by throwing exception
        if (authentication.getPrincipal() != "") {
            log.info("Access denied"); // For security reasons don't tell the exact reason
            throw new BadCredentialsException("Right combination was not found");
        } else {
            authentication.setAuthenticated(true); // Allow access
            log.info("Access granted");
            return authentication;
        }

    }

}
