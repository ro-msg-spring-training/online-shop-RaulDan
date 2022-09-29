package ro.msg.learning.shop.configurations.security;

import lombok.RequiredArgsConstructor;
import org.springframework.core.annotation.Order;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

@RequiredArgsConstructor
public class BasicSecurity extends WebSecurityConfigurerAdapter implements ISecurity{

    private final AppBasicAuthEntryPoint authEntryPoint;
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        System.out.println("basic");
        // "/addProduct","/editProduct/**","/deleteProduct/**","/product/**","/allProducts"
        http.cors().and().csrf().disable().authorizeHttpRequests().antMatchers("/addProduct","/editProduct/**","/deleteProduct/**","/product/**","/allProducts")
                .permitAll().antMatchers("/security/**").authenticated().and().httpBasic().authenticationEntryPoint(authEntryPoint);


    }
}
