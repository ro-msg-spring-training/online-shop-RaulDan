package ro.msg.learning.shop.service;

import ro.msg.learning.shop.model.Customer;

public interface SecurityService {

    Customer findCustomerByUsername(String username);
}
