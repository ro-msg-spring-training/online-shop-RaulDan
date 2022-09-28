package ro.msg.learning.shop.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ro.msg.learning.shop.configurations.strategy.StrategyConfiguration;
import ro.msg.learning.shop.exceptions.MissingStockException;
import ro.msg.learning.shop.exceptions.OrderNotCreatedException;
import ro.msg.learning.shop.model.*;
import ro.msg.learning.shop.repository.ProductOrderRepository;
import ro.msg.learning.shop.service.CustomerService;
import ro.msg.learning.shop.service.OrderDetailService;
import ro.msg.learning.shop.service.ProductOrderService;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class OrderServiceImpl implements ProductOrderService {
    private final StrategyConfiguration configuration;
    private final CustomerService customerService;
    private final OrderDetailService orderDetailService;
    private final ProductOrderRepository productOrderRepository;

    @Override
    public ProductOrder createOrder(ProductOrder productOrder, List<OrderDetail> orderDetails) {
        try {
            List<Stock> stockList = configuration.findStrategy().createOrder(orderDetails);
            List<Location> locations = stockList.stream().map(Stock::getLocation).collect(Collectors.toList());
            productOrder.setShippedFrom(locations.get(0));
            Customer customer = customerService.findAll().get(0);
            productOrder.setCustomer(customer);
            productOrderRepository.save(productOrder);
            orderDetails.forEach(e -> e.setProductOrder(productOrder));
            orderDetails.forEach(orderDetailService::save);
            return productOrder;
        } catch (MissingStockException e) {
            throw new OrderNotCreatedException(e.getMessage() + " | " + "Could not create the order you asked!");
        }
    }

    @Override
    public void deleteAll() {
        productOrderRepository.deleteAll();
    }
}
