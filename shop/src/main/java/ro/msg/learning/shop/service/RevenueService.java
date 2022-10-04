package ro.msg.learning.shop.service;

import org.springframework.scheduling.annotation.Scheduled;
import ro.msg.learning.shop.model.Revenue;

public interface RevenueService {

    Revenue findByDate(String date);
    @Scheduled(cron = "0 30 14 * * ?",fixedDelay = 50000)
    void countRevenues();
}
