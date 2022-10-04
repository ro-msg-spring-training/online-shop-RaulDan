package ro.msg.learning.shop.service.impl;

import lombok.RequiredArgsConstructor;
import ro.msg.learning.shop.model.IncomePerLocation;
import ro.msg.learning.shop.model.Location;
import ro.msg.learning.shop.repository.IncomePerLocationRepository;
import ro.msg.learning.shop.service.IncomePerLocationService;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
public class IncomePerLocationServiceImpl implements IncomePerLocationService {

    private final IncomePerLocationRepository incomeRepository;
    @Override
    public void updateIncomePerLocation(Location location, String createdAt, BigDecimal price, Integer quantity) {
        Optional<IncomePerLocation> income=incomeRepository.findFirstByLocationAndOrderCreationDate(location,createdAt);
        if(income.isPresent()){
            income.get().setIncome(updateIncome(income.get().getIncome(),computePrice(price,quantity)));
            incomeRepository.save(income.get());
            return;
        }
        IncomePerLocation newIncome=IncomePerLocation.builder().location(location)
                .orderCreationDate(createdAt).income(computePrice(price,quantity)).build();
        incomeRepository.save(newIncome);
    }

    public List<IncomePerLocation> getAllLocationsByDate(String date){
        return incomeRepository.getAllByOrderCreationDate(date);
    }

    private BigDecimal updateIncome(BigDecimal income,BigDecimal newPrice){
        return income.add(newPrice);
    }
    private BigDecimal computePrice(BigDecimal price, Integer quantity){
        return price.multiply(BigDecimal.valueOf(quantity));
    }
}
