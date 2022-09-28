package ro.msg.learning.shop.service;

import ro.msg.learning.shop.model.Customer;

import java.util.List;

public interface CustomerService {

    List<Customer> findAll();
    void save(Customer customer);
    void deleteAll();

    Customer findCustomerByUsername(String username);
}
