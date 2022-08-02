package ro.msg.learning.shop.service.impl.strategy;

import lombok.RequiredArgsConstructor;
import net.bytebuddy.implementation.bytecode.Throw;
import org.springframework.stereotype.Service;
import ro.msg.learning.shop.exceptions.MissingStockException;
import ro.msg.learning.shop.model.Location;
import ro.msg.learning.shop.model.OrderDetail;
import ro.msg.learning.shop.model.Stock;
import ro.msg.learning.shop.service.IStrategy;
import ro.msg.learning.shop.service.LocationService;
import ro.msg.learning.shop.service.StockService;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@RequiredArgsConstructor
@Service
public class SingleLocationStrategy implements IStrategy {
    private final StockService stockService;
    private final LocationService locationService;

    @Override
    public List<Stock> createOrder(List<OrderDetail> products) {

        List<Location> locations = locationService.findAll();
        boolean found = false;
        for (Location location : locations) {
            int count = 0;
            List<Stock> stocks = stockService.findAllByLocation(location);
            for (Stock currentStock : stocks) {
                found = false;
                for (OrderDetail product : products) {
                    if (currentStock.getProduct().equals(product.getProduct()) && currentStock.getQuantity() >= product.getQuantity()) {
                        count++;
                        found = true;
                        break;
                    }
                }
                if (found) {
                    continue;
                }
            }
            if (count == products.size()) {
                for (Stock stock : stocks) {
                    for (OrderDetail product : products) {
                        if (stock.getProduct().equals(product.getProduct())) {
                            stockService.editQuantity(stock, product.getQuantity());
                            break;
                        }
                    }
                }
                return Arrays.asList(stocks.get(0));
            }
        }
        throw new MissingStockException("There is no sufficient stock to create the order you asked!");
    }
}
