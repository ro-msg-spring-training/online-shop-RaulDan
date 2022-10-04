package ro.msg.learning.shop.service;

import ro.msg.learning.shop.model.OrderDetail;

import javax.activation.DataSource;
import java.util.List;

public interface PDFService {

    DataSource computePDF(List<OrderDetail> orderDetails);
}
