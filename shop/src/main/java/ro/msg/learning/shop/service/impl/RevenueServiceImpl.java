package ro.msg.learning.shop.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ro.msg.learning.shop.model.IncomePerLocation;
import ro.msg.learning.shop.model.Revenue;
import ro.msg.learning.shop.repository.RevenueRepository;
import ro.msg.learning.shop.service.RevenueService;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

@Service
@RequiredArgsConstructor
public class RevenueServiceImpl implements RevenueService {

    private final RevenueRepository revenueRepository;
    private final IncomePerLocationServiceImpl incomeService;

    @Override
    public Revenue findByDate(String date) {
        return revenueRepository.findFirstByOrderCreatedAt(date)
                .orElseThrow(()->new RuntimeException("Not Found Revenues For "+date));
    }

    @Override
    public void countRevenues() {
        LocalDateTime now=LocalDateTime.now();
        DateTimeFormatter formatter=DateTimeFormatter.ofPattern("dd/MM/yyyy");
        String nowDate=now.format(formatter);
        List<IncomePerLocation> incomes=incomeService.getAllLocationsByDate(nowDate);
        Revenue revenue=Revenue.builder().income(incomes)
                .totalIncome(computeTotalIncomes(incomes))
                .orderCreatedAt(nowDate).build();
        revenueRepository.save(revenue);
    }
    private BigDecimal computeTotalIncomes(List<IncomePerLocation> income){

        return income.stream().map(e->e.getIncome()).reduce(BigDecimal.ZERO,(a,b)->a.add(b));
    }
}
