package fi.dev.academy.vaccinationdatabase.config;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;


// https://stackoverflow.com/questions/48446708/securing-spring-boot-api-with-api-key-and-secret
// Securing REST API endpoints
// https://github.com/gregwhitaker/springboot-apikey-example/tree/master/src/main/java/springboot/apikey/example/auth
@Configuration
@EnableWebSecurity
@Order(1)
public class APISecurityConfig extends WebSecurityConfigurerAdapter {

    private static final Logger log = LoggerFactory.getLogger(APISecurityConfig.class);

    // https://www.baeldung.com/spring-value-annotation
    @Value("${principal.request.header}")
    private String principalRequestHeader;

    @Value("${principal.request.value}")
    private String principalRequestValue;

    @Override
    protected void configure(HttpSecurity http) throws Exception {


        ApiKeyAuthFilter filter = new ApiKeyAuthFilter(principalRequestHeader);
        filter.setAuthenticationManager(new ApiKeyAuthManager());

        http
                //.antMatcher("/my_api_name/orders/500") // spring-boot-starter-data-rest
                .antMatcher("/order/500")
                .csrf().disable() //TODO: token
                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                .and()
                .addFilter(filter) // ApiKeyAuthFilter: Key from API call in HttpServletRequest is not visible but if
                // it is not the right one then Spring Security won't proceed to ApiKeyAuthManager.authenticate()
                .authorizeRequests()
                .anyRequest()
                .authenticated();
    }

}
