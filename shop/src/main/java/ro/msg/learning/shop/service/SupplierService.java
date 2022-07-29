package ro.msg.learning.shop.service;

import ro.msg.learning.shop.model.Product;
import ro.msg.learning.shop.model.Supplier;

import java.util.Optional;

public interface SupplierService {

    Optional<Supplier> findByName(String name);


}
