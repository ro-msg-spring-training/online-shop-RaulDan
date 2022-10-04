package ro.msg.learning.shop.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ro.msg.learning.shop.configurations.strategy.StrategyConfiguration;
import ro.msg.learning.shop.exceptions.MissingStockException;
import ro.msg.learning.shop.exceptions.OrderNotCreatedException;
import ro.msg.learning.shop.model.*;
import ro.msg.learning.shop.repository.ProductOrderRepository;
import ro.msg.learning.shop.service.*;

import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class OrderServiceImpl implements ProductOrderService {
    private final StrategyConfiguration configuration;
    private final CustomerService customerService;
    private final OrderDetailService orderDetailService;
    private final EmailService emailService;
    private final IncomePerLocationService incomeService;
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
//            saveIncomesForLocations(locations,orderDetails);
            orderDetails.forEach(e -> e.setProductOrder(productOrder));
            orderDetails.forEach(orderDetailService::save);
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

            emailService.sendEmail(customer.getEmail(),productOrder.getCreatedAt().format(formatter),orderDetails);
            return productOrder;
        } catch (MissingStockException e) {
            throw new OrderNotCreatedException(e.getMessage() + " | " + "Could not create the order you asked!");
        }
    }

//    private void saveIncomesForLocations(List<Location> locations,List<OrderDetail> orderDetails){
//        for (int i=0;i<)
//    }

    @Override
    public void deleteAll() {
        productOrderRepository.deleteAll();
    }
}
