package ro.msg.learning.shop.dtos.csv;

import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import lombok.Builder;
import lombok.Data;

import java.math.BigDecimal;

@Data
@Builder
@JsonPropertyOrder({"productName","quantity","productCategoryName","productSupplierName","productImage","price","weight"})
public class ExportStockDto {

   private String productName;
   private Integer quantity;
   private String productCategoryName;
   private String productSupplierName;
   private String productImage;
   private BigDecimal price;
   private Double weight;

}
