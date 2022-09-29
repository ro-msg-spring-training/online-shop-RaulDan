package ro.msg.learning.shop.configurations.security;

import org.springframework.core.annotation.Order;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
@Order(99)
public class FormSecurity extends WebSecurityConfigurerAdapter implements ISecurity{

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        System.out.println("form");
        http.cors().and().csrf().disable().authorizeHttpRequests().antMatchers("/addProduct","/editProduct/**","/deleteProduct/**","/product/**","/allProducts")
                .permitAll().antMatchers("/security/**").authenticated().and().formLogin();
    }
}
