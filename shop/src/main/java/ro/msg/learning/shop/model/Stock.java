package ro.msg.learning.shop.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import ro.msg.learning.shop.model.ids.StockId;

import javax.persistence.*;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
@IdClass(StockId.class)
public class Stock {

    @Id
    @ManyToOne
    private Product product;
    @Id
    @ManyToOne
    private Location location;
    private Integer quantity;
}
