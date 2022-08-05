package ro.msg.learning.shop.service;


import ro.msg.learning.shop.exceptions.OrderNotCreatedException;
import ro.msg.learning.shop.model.OrderDetail;
import ro.msg.learning.shop.model.ProductOrder;

import java.util.List;

public interface ProductOrderService {

    ProductOrder createOrder(ProductOrder productOrder, List<OrderDetail> orderDetails) throws OrderNotCreatedException;

    void deleteAll();
}
