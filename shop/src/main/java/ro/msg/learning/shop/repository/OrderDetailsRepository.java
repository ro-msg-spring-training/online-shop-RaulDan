package ro.msg.learning.shop.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ro.msg.learning.shop.model.OrderDetail;

public interface OrderDetailsRepository extends JpaRepository<OrderDetail,Integer> {
}
