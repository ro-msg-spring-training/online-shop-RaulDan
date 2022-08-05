package ro.msg.learning.shop.configurations;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import ro.msg.learning.shop.utils.SecurityType;

@Configuration
@EnableWebSecurity
public class SecurityConfiguration extends WebSecurityConfigurerAdapter {

    @Value("${app.security}")
    private String securityType;
    private BasicSecurity basicSecurity;
    private FormSecurity formSecurity;

    @Bean
    void chooseSecurityType(){

        if(securityType.equals(SecurityType.BASIC_SECURITY.toString())){
            basicSecurity=new BasicSecurity();

        }
    }
}
