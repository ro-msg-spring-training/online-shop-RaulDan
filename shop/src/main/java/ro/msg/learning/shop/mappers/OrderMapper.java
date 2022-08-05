package ro.msg.learning.shop.mappers;

import org.springframework.stereotype.Component;
import ro.msg.learning.shop.dtos.orders.OrderCreationDto;
import ro.msg.learning.shop.model.ProductOrder;

@Component
public class OrderMapper {

    public ProductOrder toOrderFromDto(OrderCreationDto orderCreationDto){
        return ProductOrder.builder().createdAt(orderCreationDto.getCreatedAt()).addressCity(orderCreationDto.getDeliveryAddressCity())
                .addressCountry(orderCreationDto.getDeliveryAddressCountry()).addressCounty(orderCreationDto.getDeliveryAddressCounty())
                .addressStreetAddress(orderCreationDto.getDeliveryAddressStreet()).build();

    }
}
