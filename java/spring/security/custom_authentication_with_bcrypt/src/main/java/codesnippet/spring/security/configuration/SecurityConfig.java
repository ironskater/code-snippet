package codesnippet.spring.security.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.LogoutConfigurer;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

import codesnippet.spring.security.handler.CustomAuthenticationHandler;
import codesnippet.spring.security.service.UserService;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    // Bcrypt bean definition
    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    // authenticationProvider bean definition
    @Bean
    public DaoAuthenticationProvider authenticationProvider(UserService userService,
        PasswordEncoder passwordEncoder) {

        DaoAuthenticationProvider auth = new DaoAuthenticationProvider();
        auth.setUserDetailsService(userService); // set the custom user details service
        auth.setPasswordEncoder(passwordEncoder); // set the password encoder - bcrypt
        return auth;
    }

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http, CustomAuthenticationHandler handler) throws Exception {

        return http
            // .cors().configurationSource(request -> new CorsConfiguration().applyPermitDefaultValues()).and()
            .authorizeRequests(configurer -> configurer.antMatchers("/").hasRole("EMPLOYEE")
                // ** means all subdirectories recursively
                .antMatchers("/leaders/**").hasRole("MANAGER")
                .antMatchers("/systems/**").hasRole("ADMIN")
                // 針對所有http請求須通過授權認證才可訪問, 故啟用這項將導致無法進入showRegistrationForm
                // .anyRequest().authenticated()
            )
            .formLogin(
                // show our custom form at the request mapping "/showMyLoginPage"
                // Note that we need to create a controller for this request mapping
                configurer -> configurer.loginPage("/showMyLoginPage")

                // Login form should POST data to this URL "/authenticateTheUser" for processing
                    .loginProcessingUrl("/authenticateTheUser")
                    .successHandler(handler)
                    // Allow everyone to see login page. No need to be logged in.
                    .permitAll()
                    // Add logout support for default URL "/logout"
                    // Note that we need to POST data to default logout URL: "/logout"
            ).logout(LogoutConfigurer::permitAll)
            // if the user is not authorized, rederict to access-denied page
            .exceptionHandling(configurer -> configurer
                .accessDeniedPage("/access-denied")
            ).build();
    }
}
