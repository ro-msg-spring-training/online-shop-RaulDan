package ro.msg.learning.shop.configurations;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
public class FormSecurity extends WebSecurityConfigurerAdapter implements ISecurity{

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        System.out.println("form");
        http.authorizeHttpRequests().antMatchers(
                "/addProduct","/editProduct/**","/deleteProduct/**","/product/**","/allProducts"
        ).permitAll();
    }
}
