package codesnippet.spring.security.security;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter
{
    @Override
    protected void
        configure(AuthenticationManagerBuilder auth) throws Exception
    {
        /**
         * https://www.baeldung.com/spring-security-5-default-password-encoder
         *
         * Before Spring Security 4, we can ignore PasswordEncoder.
         * However, from Spring Security 5 or springboot 2, if we use the same configurations, we will get the exception after we enter user/pw:
         *
         *      java.lang.IllegalArgumentException: There is no PasswordEncoder mapped for the id "null"
         *
         * The error tells us that the given password couldn't be decoded since no password encoder was configured for our in-memory authentication.
         * And We can fix this error by defining a DelegatingPasswordEncoder with the PasswordEncoderFactories class.
         */
        PasswordEncoder encoder = PasswordEncoderFactories.createDelegatingPasswordEncoder();

        // in-memory authentication
        auth.inMemoryAuthentication()
            .withUser("john").password(encoder.encode("pw1")).roles("employee")
            .and()
            .withUser("mary").password(encoder.encode("pw2")).roles("manager")
            .and()
            /**
             * If, for any reason, we don't want to encode the configured password, we can make use of the NoOpPasswordEncoder.
             * To do so, we simply prefix the passphrase we provide to the password() method with the {noop} identifier:
             */
            .withUser("susan").password("{noop}pw3").roles("admin");
    }

    /**
     * Uncomment it to enable HTTP Basic Authentication
     */
    // @Override
    // protected void
    //     configure(HttpSecurity http) throws Exception
    // {
    //     // http
    //     //   .authorizeRequests()
    //     //   .anyRequest()
    //     //   .authenticated()
    //     //   .and()
    //     //   .httpBasic();
    // }
}
