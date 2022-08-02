package ro.msg.learning.shop.service;

import ro.msg.learning.shop.exceptions.MissingStockException;
import ro.msg.learning.shop.model.OrderDetail;
import ro.msg.learning.shop.model.Stock;

import java.util.List;

public interface IStrategy {
    List<Stock> createOrder(List<OrderDetail> products) throws MissingStockException;
}
