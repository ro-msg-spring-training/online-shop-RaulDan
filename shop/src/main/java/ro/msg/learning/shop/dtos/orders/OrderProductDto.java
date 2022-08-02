package ro.msg.learning.shop.dtos.orders;

import lombok.Builder;
import lombok.Data;
import ro.msg.learning.shop.model.Product;

@Data
@Builder
public class OrderProductDto {

    private Integer productId;
    private Integer quantity;
    private Product product;
}
