package ro.msg.learning.shop.controller.csv;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ro.msg.learning.shop.mappers.csv.StockCsvMapper;
import ro.msg.learning.shop.model.Stock;
import ro.msg.learning.shop.service.impl.csv.CsvServiceImpl;

import java.util.List;

@RestController
@CrossOrigin
@RequiredArgsConstructor
@RequestMapping("/csv")
public class CsvController {

    private final StockCsvMapper stockCsvMapper;
    private final CsvServiceImpl csvService;

    @GetMapping(value = "/export/{id}",produces = {"text/csv"})
    public ResponseEntity<?> exportFromLocation(@PathVariable("id") Integer id){
        List<Stock> stockList=csvService.allStocksFromLocation(id);
        return new ResponseEntity<>(stockCsvMapper.toDtoFromStock(stockList), HttpStatus.OK);
    }
}
