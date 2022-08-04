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
import ro.msg.learning.shop.service.ProductOrderService;
import ro.msg.learning.shop.service.ProductService;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequiredArgsConstructor
@CrossOrigin
public class OrderController {
    private final ProductOrderService orderService;
    private final ProductService productService;
    private final OrderMapper orderMapper;
    private final OrderDetailsMapper orderDetailsMapper;

    @PostMapping(value = "/createOrder")
    public ResponseEntity<ProductOrder> createOrder(@RequestBody OrderCreationDto orderCreationDto) {

        List<OrderDetail> orderDetails = obtainOrderDetails(orderCreationDto);

        return new ResponseEntity<>(orderService.createOrder(orderMapper.toOrderFromDto(orderCreationDto), orderDetails), HttpStatus.OK);
    }

    private List<OrderDetail> obtainOrderDetails(OrderCreationDto orderCreationDto){
        return orderCreationDto.getProducts().stream().map(e -> OrderDetail.builder()
                .product(productService.getProduct(e.getProductId()))
                .quantity(e.getQuantity()).build()).collect(Collectors.toList());
    }
}
