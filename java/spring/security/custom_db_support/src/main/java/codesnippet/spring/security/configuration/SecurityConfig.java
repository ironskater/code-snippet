package codesnippet.spring.security.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.web.cors.CorsConfiguration;
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

    /**
     * @apiNote It's used for creating custom login page. Use spring default login page if we
     *          comment the code
     * @param httpSecurity
     * @return SecurityFilterChain
     * @throws Exception
     */
    @Bean
    public SecurityFilterChain filterChain(HttpSecurity httpSecurity,
            CustomAuthenticationHandler handler) throws Exception {
        return httpSecurity.cors()
                .configurationSource(request -> new CorsConfiguration().applyPermitDefaultValues())
                .and()
                // .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS).and()
                // Restrict access based on the HttpServletRequest
                .authorizeRequests(configurer -> configurer.antMatchers("/").hasRole("EMPLOYEE")
                        // ** means all subdirectories recursively
                        .antMatchers("/leaders/**").hasRole("MANAGER").antMatchers("/systems/**")
                        .hasRole("ADMIN")
                        // 針對所有http請求須通過授權認證才可訪問
                        .anyRequest().authenticated())
                .formLogin( // we are customizing the form login process
                        configurer -> configurer.loginPage("/custom-loginPage") // show our custom
                                                                                // form at the
                                                                                // request mapping
                                                                                // "/custom-login-page"
                                                                                // Note that we need
                                                                                // to create a
                                                                                // controller for
                                                                                // this request
                                                                                // mapping
                                .loginProcessingUrl("/authenticate-theUser") // Login form should
                                                                             // POST data to this
                                                                             // URL"/authenticate-theUser"
                                                                             // for processing
                                .successHandler(handler)
                                // ie, check user id and password
                                // No controller request mapping required for
                                // th@EnableTransactionManagement // To allow us to use
                                // @Transactional in springis.
                                // We get this for free because of spring magic, which will handle
                                // everything for us behind the scenes if we use in-memory
                                // authentication
                                .permitAll() // Allow everyone to see login page. No need to be
                                             // logged in.
                ).logout(configurer -> configurer.permitAll() // Add logout support for default URL
                                                              // /logout
                                                              // Note that we need to POST data to
                                                              // default logout URL: /logout
                )
                // if the user is not authorized, rederict to access-denied page
                .exceptionHandling(configurer -> configurer.accessDeniedPage("/access-denied"))
                .build();
    }
}
