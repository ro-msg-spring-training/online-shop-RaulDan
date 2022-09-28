package ro.msg.learning.shop.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ro.msg.learning.shop.configurations.security.SecurityConfiguration;
import ro.msg.learning.shop.model.Customer;
import ro.msg.learning.shop.service.CustomerService;
import ro.msg.learning.shop.service.SecurityService;

@Service
@RequiredArgsConstructor
public class SecurityServiceImpl implements SecurityService {

    private final CustomerService customerService;
    private final SecurityConfiguration securityConfiguration;
    @Override
    public Customer findCustomerByUsername(String username) {
        securityConfiguration.chooseSecurityType();
        return customerService.findCustomerByUsername(username);
    }
}
