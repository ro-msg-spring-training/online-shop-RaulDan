package ro.msg.learning.shop.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import ro.msg.learning.shop.dtos.orders.OrderCreationDto;
import ro.msg.learning.shop.mappers.OrderDetailsMapper;
import ro.msg.learning.shop.mappers.OrderMapper;
import ro.msg.learning.shop.model.OrderDetail;
import ro.msg.learning.shop.model.ProductOrder;
import ro.msg.learning.shop.service.IStrategy;
import ro.msg.learning.shop.service.OrderService;
import ro.msg.learning.shop.service.ProductService;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequiredArgsConstructor
@CrossOrigin
public class OrderController {
    private final OrderService orderService;
    private final ProductService productService;
    private final OrderMapper orderMapper;
    private final OrderDetailsMapper orderDetailsMapper;

    @PostMapping(value = "/createOrder")
    public ResponseEntity<ProductOrder> createOrder(@RequestBody OrderCreationDto orderCreationDto) {

        orderCreationDto.getProducts().forEach(e -> e.setProduct(productService.getProduct(e.getProductId())));
        ProductOrder productOrder = orderMapper.toOrderFromDto(orderCreationDto);
        List<OrderDetail> orderDetails = orderCreationDto.getProducts().stream().map(orderDetailsMapper::toOrderDetailFromOrderProductDto).collect(Collectors.toList());
        return new ResponseEntity<>(orderService.createOrder(productOrder, orderDetails), HttpStatus.OK);
    }
}
