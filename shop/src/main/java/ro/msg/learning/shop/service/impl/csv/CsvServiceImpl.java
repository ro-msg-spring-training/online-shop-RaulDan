package ro.msg.learning.shop.service.impl.csv;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ro.msg.learning.shop.model.Location;
import ro.msg.learning.shop.model.Stock;
import ro.msg.learning.shop.service.CsvService;
import ro.msg.learning.shop.service.LocationService;
import ro.msg.learning.shop.service.StockService;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CsvServiceImpl implements CsvService {

    private final LocationService locationService;
    private final StockService stockService;

    @Override
    public List<Stock> allStocksFromLocation(Integer idLocation) {
        Location location=locationService.getLocationById(idLocation);
        return stockService.findAllByLocation(location);
    }
}
