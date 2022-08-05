package ro.msg.learning.shop.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ro.msg.learning.shop.model.ProductOrder;

public interface ProductOrderRepository extends JpaRepository<ProductOrder,Integer> {
}
