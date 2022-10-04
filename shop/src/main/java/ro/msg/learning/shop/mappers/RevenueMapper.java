package ro.msg.learning.shop.mappers;

import org.springframework.stereotype.Component;
import ro.msg.learning.shop.dtos.RevenueDto;
import ro.msg.learning.shop.model.Revenue;

@Component
public class RevenueMapper {

    public RevenueDto toRevenueDtoFromRevenue(Revenue revenue){
        return RevenueDto.builder().id(revenue.getId())
                .customerUserName(revenue.getCustomer().getUsername())
                .incomes(IncomeMapper.toIncomeDtoFromIncome(revenue.getIncome()))
                .orderCreationDate(revenue.getOrderCreatedAt()).totalIncome(revenue.getTotalIncome()).build();
    }
}
