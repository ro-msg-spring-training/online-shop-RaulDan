package ro.msg.learning.shop.configurations.security;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import ro.msg.learning.shop.utils.SecurityType;

@Configuration
@RequiredArgsConstructor
public class SecurityConfiguration extends WebSecurityConfigurerAdapter {

    private final AppBasicAuthEntryPoint authEntryPoint;
    @Value("${app.security}")
    private String securityType;


    @Bean
    public ISecurity chooseSecurityType() {
        System.out.println(securityType);
        if (securityType.equals(SecurityType.BASIC_SECURITY.toString())) {
            return new BasicSecurity(authEntryPoint);
        }
        return new FormSecurity();
    }
}
