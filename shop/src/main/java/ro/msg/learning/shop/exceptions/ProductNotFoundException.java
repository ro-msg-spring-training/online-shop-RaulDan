package ro.msg.learning.shop.exceptions;

import java.util.function.Supplier;

public class ProductNotFoundException extends RuntimeException{

    public ProductNotFoundException(Integer id){
        super("Product with id: "+id+" does not exist!");
    }

}
