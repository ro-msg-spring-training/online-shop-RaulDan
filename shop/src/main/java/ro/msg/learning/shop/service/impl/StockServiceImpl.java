package ro.msg.learning.shop.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ro.msg.learning.shop.model.Location;
import ro.msg.learning.shop.model.Stock;
import ro.msg.learning.shop.repository.StockRepository;
import ro.msg.learning.shop.service.StockService;

import java.util.List;

@Service
@RequiredArgsConstructor
public class StockServiceImpl implements StockService {

    private final StockRepository stockRepository;
    @Override
    public List<Stock> findAll() {
        return stockRepository.findAll();
    }

    @Override
    public List<Stock> findAllByLocation(Location location) {
        return stockRepository.findAllByLocation(location);
    }

    @Override
    @Transactional
    public void editQuantity(Stock stock, Integer quantity) {
        Integer newQuantity=stock.getQuantity()-quantity;
        stock.setQuantity(newQuantity);
        stockRepository.save(stock);
    }

    @Override
    public void save(Stock stock) {
        stockRepository.save(stock);
    }

    @Override
    public void deleteAll() {
        stockRepository.deleteAll();
    }
}
