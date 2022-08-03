package ro.msg.learning.shop.service.impl.strategy;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import ro.msg.learning.shop.exceptions.MissingStockException;
import ro.msg.learning.shop.model.Location;
import ro.msg.learning.shop.model.OrderDetail;
import ro.msg.learning.shop.model.Stock;
import ro.msg.learning.shop.service.IStrategy;
import ro.msg.learning.shop.service.LocationService;
import ro.msg.learning.shop.service.StockService;

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
        for (Location location : locations) {
            List<Stock> stocks = stockService.findAllByLocation(location);
            int count=checkStocksAvailability(products,stocks);
            if (count == products.size()) {
                editStocksQuantity(stocks,products);
                return Arrays.asList(stocks.get(0));
            }
        }
        throw new MissingStockException(errorMessage);
    }

    private int checkStocksAvailability(List<OrderDetail> products, List<Stock> stocks) {
        int count = 0;
        for (Stock currentStock : stocks) {
            for (OrderDetail product : products) {
                if (currentStock.getProduct().equals(product.getProduct()) && currentStock.getQuantity() >= product.getQuantity()) {
                    count++;
                    break;
                }
            }
        }
        return count;
    }

    public void editStocksQuantity(List<Stock> stocks, List<OrderDetail> products) {
        for (Stock stock : stocks) {
            for (OrderDetail product : products) {
                if (stock.getProduct().equals(product.getProduct())) {
                    stockService.editQuantity(stock,product.getQuantity());
                    break;
                }
            }
        }
    }
}
