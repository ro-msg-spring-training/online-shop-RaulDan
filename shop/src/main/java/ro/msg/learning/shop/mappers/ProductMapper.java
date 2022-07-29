package ro.msg.learning.shop.mappers;

import org.springframework.stereotype.Component;
import ro.msg.learning.shop.dtos.ProductDto;
import ro.msg.learning.shop.model.Product;
import ro.msg.learning.shop.model.ProductCategory;
import ro.msg.learning.shop.model.Supplier;

@Component
public class ProductMapper {

    public Product toProduct(ProductDto productDto){

        return Product.builder().name(productDto.getName()).description(productDto.getDescription())
                .price(productDto.getPrice()).weight(productDto.getWeight())
                .supplier(Supplier.builder().name(productDto.getSupplierName()).build())
                .category(ProductCategory.builder().name(productDto.getProductCategory()).build())
                .imageUrl(productDto.getImageUrl()).build();
    }

    public ProductDto toProductDto(Product product){
        return ProductDto.builder().id(product.getId())
                .name(product.getName()).description(product.getDescription())
                .price(product.getPrice()).weight(product.getWeight())
                .supplierName(product.getSupplier().getName())
                .productCategory(product.getCategory().getName()).imageUrl(product.getImageUrl()).build();
    }
}
