package ro.msg.learning.shop.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ro.msg.learning.shop.model.ProductOrder;

public interface ProductOrderRepository extends JpaRepository<ProductOrder,Integer> {
}
