package ro.msg.learning.shop.dtos.orders;

import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.List;

@Data
@Builder
public class OrderCreationDto {

    private LocalDateTime createdAt;
    private String deliveryAddressCountry;
    private String deliveryAddressCity;
    private String deliveryAddressCounty;
    private String deliveryAddressStreet;
    private List<OrderProductDto> products;
}
