package ro.msg.learning.shop.service;

import ro.msg.learning.shop.exceptions.ProductNotFoundException;
import ro.msg.learning.shop.model.Product;
import ro.msg.learning.shop.utils.Status;

import java.util.Collection;

public interface ProductService {

    Status addProduct(Product product);

    Status editProduct(Integer id, Product product);

    Status deleteProduct(Integer idProduct);

    Product getProduct(Integer idProduct) throws ProductNotFoundException;

    Collection<Product> getAllProducts();

    void save(Product product);

    void deleteAll();
}
