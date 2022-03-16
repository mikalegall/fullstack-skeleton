package fi.dev.academy.vaccinationdatabase.config;

import lombok.Data;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.web.authentication.preauth.AbstractPreAuthenticatedProcessingFilter;

import javax.servlet.http.HttpServletRequest;

@Data
public class ApiKeyAuthFilter extends AbstractPreAuthenticatedProcessingFilter {

    private static final Logger log = LoggerFactory.getLogger(ApiKeyAuthFilter.class);

    private String principalRequestHeader;

    public ApiKeyAuthFilter(String principalRequestHeader) {
        this.principalRequestHeader = principalRequestHeader;
    }


    @Override
    protected Object getPreAuthenticatedPrincipal(HttpServletRequest request) {
        log.info("Someone poked at " + request.getRequestURI() + " by " + request.getProtocol() + " from " + request.getRemoteHost() + " (" +
                request.getLocalName() + ") which is " + request.getServerName() + ":" + request.getServerPort());
        // ApiKeyAuthFilter: Key from API call in HttpServletRequest is not visible but if it is not the right one
        // then Spring Security won't proceed to ApiKeyAuthFilter.getPreAuthenticatedCredentials neither to
        // ApiKeyAuthManager.authenticate()
        return request.getHeader(principalRequestHeader);
    }


    // ApiKeyAuthFilter: If Key from API call was the right one ("getPreAuthenticatedPrincipal()") and if it has some
    // Value then here we will return the Value. But there is no need to use the Value attribute when using
    // Key in the API call (in meaning of "API Key"; there also exist Key-Value pairs which can be useful such as in
    // credentials like Username-Password)
    @Override
    protected Object getPreAuthenticatedCredentials(HttpServletRequest request) {

        String tempVariable;

        if (request.getHeader(principalRequestHeader) == "") {
            tempVariable = "[NO_VALUE]";
        } else {
            tempVariable = request.getHeader(principalRequestHeader);
        }

        log.info("Someone poked at " + request.getRequestURI() + " by " + request.getProtocol() + " from " + request.getRemoteHost() + " (" +
                request.getLocalName() + ") which is " + request.getServerName() + ":" + request.getServerPort() +
                " (with the right API Key) using Key's Value = " + tempVariable);

        return request.getHeader(principalRequestHeader);
    }
}
