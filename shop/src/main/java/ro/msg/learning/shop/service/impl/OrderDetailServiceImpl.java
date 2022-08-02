package ro.msg.learning.shop.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ro.msg.learning.shop.model.OrderDetail;
import ro.msg.learning.shop.repository.OrderDetailsRepository;
import ro.msg.learning.shop.service.OrderDetailService;

@Service
@RequiredArgsConstructor
public class OrderDetailServiceImpl implements OrderDetailService {

    private final OrderDetailsRepository orderDetailsRepository;
    @Override
    public void save(OrderDetail orderDetail) {
        orderDetailsRepository.save(orderDetail);
    }
}
