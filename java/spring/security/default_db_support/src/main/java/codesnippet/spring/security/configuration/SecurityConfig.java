package codesnippet.spring.security.configuration;

import javax.sql.DataSource;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.provisioning.JdbcUserDetailsManager;
import org.springframework.security.provisioning.UserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class SecurityConfig
{
    // We replace InMemoryUserDetailsManager with this one
    @Bean
    public UserDetailsManager userDetailsManager(DataSource dataSource) {
        return new JdbcUserDetailsManager(dataSource);
    }

    // @Bean
    // public InMemoryUserDetailsManager userDetailsManager() {
    //     UserDetails john = User.builder()
    //         .username("john")
    //         .password("{noop}test123")
    //         .roles("EMPLOYEE")
    //         .build();

    //     UserDetails mary = User.builder()
    //         .username("mary")
    //         .password("{noop}test123")
    //         .roles("EMPLOYEE", "MANAGER")
    //         .build();

    //     UserDetails susan = User.builder()
    //         .username("susan")
    //         .password("{noop}test123")
    //         .roles("EMPLOYEE", "ADMIN")
    //         .build();

    //     return new InMemoryUserDetailsManager(john, mary, susan);
    // }

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
                configurer ->
                    configurer.antMatchers("/").hasRole("EMPLOYEE")
                                // ** means all subdirectories recursively
                                .antMatchers("/leaders/**").hasRole("MANAGER")
                                .antMatchers("/systems/**").hasRole("ADMIN")
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
            // if the user is not authorized, rederict to access-denied page
            .exceptionHandling(configurer -> configurer.accessDeniedPage("/access-denied"))
            .build();
    }
}
