package ro.msg.learning.shop.model.ids;

import ro.msg.learning.shop.model.Location;
import ro.msg.learning.shop.model.Product;
import java.io.Serializable;

public class StockId implements Serializable {

    private Product product;
    private Location location;
}
