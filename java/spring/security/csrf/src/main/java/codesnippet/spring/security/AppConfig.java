package codesnippet.spring.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class AppConfig
{
    @Bean
    public InMemoryUserDetailsManager userDetailsManager() {
        UserDetails john = User.builder()
            .username("john")
            .password("{noop}test123")
            .roles("EMPLOYEE")
            .build();

        UserDetails mary = User.builder()
            .username("mary")
            .password("{noop}test123")
            .roles("MANAGER")
            .build();

        UserDetails susan = User.builder()
            .username("susan")
            .password("{noop}test123")
            .roles("ADMIN")
            .build();

        return new InMemoryUserDetailsManager(john, mary, susan);
    }

    /**
     * @apiNote It's used for creating custom login page.
     *          Use spring default login page if we comment the code
     * @param httpSecurity
     * @return
     * @throws Exception
     */
    @Bean
    public SecurityFilterChain filterChain(HttpSecurity httpSecurity) throws Exception {
        return httpSecurity
            // Restrict access based on the HttpServletRequest
            .authorizeRequests(
                configurer -> configurer.anyRequest().authenticated() // Any request to the app must be authenticated(ie logged in)
            )
            .formLogin( // we are customizing the form login process
                configurer -> configurer
                    .loginPage("/custom-loginPage") // show our custom form at the request mapping "/custom-login-page"
                                                        // Note that we need to create a controller for this request mapping
                    .loginProcessingUrl("/authenticate-theUser") // Login form should POST data to this URL"/authenticate-theUser" for processing
                    // ie, check user id and password
                    // No controller request mapping required for this.
                    // We get this for free because of spring magic, which will handle everything for us behind the scenes if we use in-memory authentication
                    .permitAll() // Allow everyone to see login page. No need to be logged in.
            )
            .logout(
                configurer -> configurer.permitAll() // Add logout support for default URL /logout
                                                     // Note that we need to POST data to default logout URL: /logout
            )
            // .cors().and().csrf().disable()
            .build();
    }
}