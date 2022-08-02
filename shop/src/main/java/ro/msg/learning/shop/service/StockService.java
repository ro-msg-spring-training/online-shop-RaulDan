package ro.msg.learning.shop.service;

import ro.msg.learning.shop.model.Location;
import ro.msg.learning.shop.model.Stock;

import java.util.List;

public interface StockService {

    List<Stock> findAll();
    List<Stock> findAllByLocation(Location location);
    void editQuantity(Stock stock,Integer quantity);
}
