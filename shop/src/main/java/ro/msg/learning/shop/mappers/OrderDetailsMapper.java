package ro.msg.learning.shop.mappers;

import org.springframework.stereotype.Component;
import ro.msg.learning.shop.dtos.orders.OrderProductDto;
import ro.msg.learning.shop.model.OrderDetail;

@Component
public class OrderDetailsMapper {

    public OrderDetail toOrderDetailFromOrderProductDto(OrderProductDto orderProductDto){
        return OrderDetail.builder().quantity(orderProductDto.getQuantity()).product(orderProductDto.getProduct()).build();
    }
}
