package ro.msg.learning.shop.mappers;

import org.springframework.stereotype.Component;
import ro.msg.learning.shop.dtos.IncomeDto;
import ro.msg.learning.shop.model.IncomePerLocation;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class IncomeMapper {

    public static List<IncomeDto> toIncomeDtoFromIncome(List<IncomePerLocation> incomes){
        return incomes.stream().map(e->IncomeDto.builder()
                .location(e.getLocation().getName()).income(e.getIncome()).build()).collect(Collectors.toList());
    }
}
