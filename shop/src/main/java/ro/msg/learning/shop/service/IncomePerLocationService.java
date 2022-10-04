package ro.msg.learning.shop.service;

import ro.msg.learning.shop.model.Location;

import java.math.BigDecimal;

public interface IncomePerLocationService {

    void updateIncomePerLocation(Location location, String createdAt, BigDecimal price,Integer quantity);
}
