package ro.msg.learning.shop.dtos;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class RevenueDto {

    private Integer id;
    private String customerUserName;
    private String orderCreationDate;
    private BigDecimal totalIncome;
    private List<IncomeDto> incomes;
}
