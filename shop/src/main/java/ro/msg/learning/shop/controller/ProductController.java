package ro.msg.learning.shop.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ro.msg.learning.shop.dtos.ProductDto;
import ro.msg.learning.shop.mappers.ProductMapper;
import ro.msg.learning.shop.model.Product;
import ro.msg.learning.shop.service.ProductService;
import ro.msg.learning.shop.utils.Status;

import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequiredArgsConstructor
@CrossOrigin
public class ProductController {

    private final ProductService productService;
    private final ProductMapper productMapper;

    @PostMapping(value = "/addProduct")
    public ResponseEntity<Status> addProduct(@RequestBody ProductDto productDto){
        Product product=productMapper.toProduct(productDto);
        return new ResponseEntity<>(productService.addProduct(product), HttpStatus.OK);
    }

    @PutMapping(value = "/editProduct/{id}")
    public ResponseEntity<Status> editProduct(@PathVariable("id") Integer id,@RequestBody ProductDto productDto){
        Product product=productMapper.toProduct(productDto);
        return new ResponseEntity<>(productService.editProduct(id,product),HttpStatus.OK);
    }

    @DeleteMapping(value = "/deleteProduct/{id}")
    public ResponseEntity<Status> deleteProduct(@PathVariable("id") Integer id){
        return new ResponseEntity<>(productService.deleteProduct(id),HttpStatus.OK);
    }

    @GetMapping(value = "/product/{id}")
    public ResponseEntity<ProductDto> getProduct(@PathVariable("id") Integer id){
        Product product=productService.getProduct(id);
        return new ResponseEntity<>(productMapper.toProductDto(product),HttpStatus.OK);
    }

    @GetMapping(value = "/allProducts")
    public ResponseEntity<List<ProductDto>> findAllProducts(){
        Collection<Product> products=productService.getAllProducts();
        List<ProductDto> productDtoList=products.stream().map(e->productMapper.toProductDto(e)).collect(Collectors.toList());
        return new ResponseEntity<>(productDtoList,HttpStatus.OK);
    }
}
