package ro.msg.learning.shop.service.impl.strategy;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ro.msg.learning.shop.exceptions.MissingStockException;
import ro.msg.learning.shop.model.OrderDetail;
import ro.msg.learning.shop.model.Stock;
import ro.msg.learning.shop.service.IStrategy;
import ro.msg.learning.shop.service.StockService;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
public class MostAbundantStrategy implements IStrategy {

    private final StockService stockService;

    @Override
    public List<Stock> createOrder(List<OrderDetail> products) {
        List<Stock> stockList = stockService.findAll();
        List<Stock> result=new ArrayList<>();
        for (OrderDetail product : products) {
            List<Stock> stocks = stockList.stream().filter(e -> e.getProduct().equals(product.getProduct()))
                    .sorted(Comparator.comparingInt(Stock::getQuantity)).collect(Collectors.toList());
            Collections.reverse(stocks);
            if(null!=stocks && stocks.get(0).getQuantity()>=product.getQuantity()){
                    stockService.editQuantity(stocks.get(0),product.getQuantity());
                    result.add(stocks.get(0));
            }

        }
        if(result.size()!= products.size()){
            throw new MissingStockException("There is no sufficient stock to create the order you asked!");
        }
        return result;
    }
}
