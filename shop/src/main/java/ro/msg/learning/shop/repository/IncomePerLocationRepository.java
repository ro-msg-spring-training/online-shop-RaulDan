package ro.msg.learning.shop.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ro.msg.learning.shop.model.IncomePerLocation;
import ro.msg.learning.shop.model.Location;

import java.util.List;
import java.util.Optional;

public interface IncomePerLocationRepository extends JpaRepository<IncomePerLocation,Integer> {
    Optional<IncomePerLocation> findFirstByLocationAndOrderCreationDate(Location location,String orderCreationDate);

    List<IncomePerLocation> getAllByOrderCreationDate(String date);
}
