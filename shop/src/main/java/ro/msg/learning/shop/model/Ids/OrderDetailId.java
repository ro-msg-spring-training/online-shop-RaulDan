package ro.msg.learning.shop.model.Ids;


import ro.msg.learning.shop.model.Product;
import ro.msg.learning.shop.model.ProductOrder;


import java.io.Serializable;


public class OrderDetailId implements Serializable {

    private ProductOrder productOrder;
    private Product product;
}
