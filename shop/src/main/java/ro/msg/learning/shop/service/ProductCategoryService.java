package ro.msg.learning.shop.service;

import ro.msg.learning.shop.model.ProductCategory;

import java.util.Optional;

public interface ProductCategoryService {

     Optional<ProductCategory> findByName(String name);
}
