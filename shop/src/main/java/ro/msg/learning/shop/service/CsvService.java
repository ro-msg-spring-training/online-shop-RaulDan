package ro.msg.learning.shop.service;

import ro.msg.learning.shop.model.Stock;

import java.util.List;

public interface CsvService {

    List<Stock> allStocksFromLocation(Integer idLocation);
}
