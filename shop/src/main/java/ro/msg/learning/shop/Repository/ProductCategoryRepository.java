package ro.msg.learning.shop.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ro.msg.learning.shop.model.ProductCategory;

public interface ProductCategoryRepository extends JpaRepository<ProductCategory,Integer> {
}
