package ro.msg.learning.shop.configurations;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import ro.msg.learning.shop.service.IStrategy;
import ro.msg.learning.shop.service.LocationService;
import ro.msg.learning.shop.service.StockService;
import ro.msg.learning.shop.service.impl.strategy.MostAbundantStrategy;
import ro.msg.learning.shop.service.impl.strategy.SingleLocationStrategy;
import ro.msg.learning.shop.utils.Strategy;

@Configuration
@RequiredArgsConstructor
public class StrategyConfiguration {

    @Value("${app.strategy}")
    private String strategy;
    private final StockService stockService;
    private final LocationService locationService;

    @Bean
    public IStrategy findStrategy(){
        if(strategy.equals(Strategy.SINGLE_LOCATION.toString())){
            return new SingleLocationStrategy(stockService,locationService);
        }
        else return new MostAbundantStrategy(stockService);
    }
}
