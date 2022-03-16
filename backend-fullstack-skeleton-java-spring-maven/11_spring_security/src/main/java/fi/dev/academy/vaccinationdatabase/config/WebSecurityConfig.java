package fi.dev.academy.vaccinationdatabase.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

import javax.servlet.http.HttpSession;

@Configuration
@EnableGlobalMethodSecurity(prePostEnabled = true) // Makes possible to use @PreAuthorize("hasRole('ROLE_ADMIN')")
@EnableWebSecurity
@Order(2)
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

    // http://docs.oracle.com/javaee/7/api/javax/servlet/http/HttpSession.html
    @Autowired
    private HttpSession session;

    @Override
    protected void configure(HttpSecurity http) throws Exception {

        http.authorizeRequests()
                .antMatchers("/swagger-ui/**",
                        "/swagger-resources/**",
                        "/v2/api-docs/**",
                        "/actuator/**",
                        "/orders/", //Allow orders only for demonstration purposes for APISecurityConfig:
                        "/order/**") //API KEY (principal.request.header)
                .permitAll()
                .and()
                .authorizeRequests()
                .anyRequest().authenticated();

    }
}
