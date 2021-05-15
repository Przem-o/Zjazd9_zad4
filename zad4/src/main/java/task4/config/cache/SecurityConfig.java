package task4.config.cache;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    public static final String ADMIN_ROLE = "ADMIN";
   // public static final String USER_ROLE = "USER";
    public static final String MANAGER_ROLE = "MANAGER";
    public static final String SALES_ROLE = "SALES";

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        PasswordEncoder encoder = PasswordEncoderFactories.createDelegatingPasswordEncoder();
        auth
                .inMemoryAuthentication()
                .withUser("admin")
                .password(encoder.encode("admin"))
                .roles(ADMIN_ROLE, MANAGER_ROLE, SALES_ROLE)
                .and()
//                .withUser("user")
//                .password(encoder.encode("user"))
//                .roles(USER_ROLE)
//                .and()
                .withUser("manager")
                .password(encoder.encode("manager"))
                .roles(MANAGER_ROLE)
                .and()
                .withUser("sales")
                .password(encoder.encode("sales"))
                .roles(SALES_ROLE);
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .csrf()
                .disable() //wylaczenie csrf tylko do testow
                .authorizeRequests()
                .anyRequest()
                .authenticated()
                .and()
                .httpBasic();
    }
}


