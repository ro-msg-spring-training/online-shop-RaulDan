package ro.msg.learning.shop.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import ro.msg.learning.shop.model.ids.OrderDetailId;

import javax.persistence.*;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
@IdClass(OrderDetailId.class)
public class OrderDetail {
    @Id
    @ManyToOne
    private ProductOrder productOrder;
    @Id
    @ManyToOne
    private Product product;
    private Integer quantity;
}
