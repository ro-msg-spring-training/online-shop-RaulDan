package ro.msg.learning.shop.service;

import ro.msg.learning.shop.exceptions.MissingStockException;
import ro.msg.learning.shop.model.OrderDetail;
import ro.msg.learning.shop.model.Stock;

import java.util.List;

public interface IStrategy {
    public static final String ERROR_Message="There is no sufficient stock to create the order you asked!";
    List<Stock> createOrder(List<OrderDetail> products) throws MissingStockException;
}
