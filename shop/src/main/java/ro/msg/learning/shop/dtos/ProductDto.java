package ro.msg.learning.shop.dtos;

import lombok.Builder;
import lombok.Data;

import java.math.BigDecimal;

@Data
@Builder
public class ProductDto {

    private Integer id;
    private String name;
    private String description;
    private BigDecimal price;
    private Double weight;
    private String supplierName;
    private String productCategory;
    private String imageUrl;
}
