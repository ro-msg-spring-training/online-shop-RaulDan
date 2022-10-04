package ro.msg.learning.shop.service;

import ro.msg.learning.shop.model.OrderDetail;
import ro.msg.learning.shop.utils.Status;

import java.util.List;

public interface EmailService {

    Status sendEmail(String to, String createdAt, List<OrderDetail> orderDetails);
}
