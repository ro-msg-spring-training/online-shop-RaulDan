package ro.msg.learning.shop.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ro.msg.learning.shop.model.Ids.StockId;
import ro.msg.learning.shop.model.Stock;

public interface StockRepository extends JpaRepository<Stock, StockId> {

}
