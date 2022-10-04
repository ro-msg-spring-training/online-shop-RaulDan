package ro.msg.learning.shop.mappers.csv;

import ro.msg.learning.shop.dtos.csv.ExportStockDto;
import ro.msg.learning.shop.model.Product;
import ro.msg.learning.shop.model.Stock;

import java.util.List;
import java.util.stream.Collectors;

public class StockCsvMapper {

    public List<ExportStockDto> toDtoFromStock(List<Stock> stockList){

        return stockList.stream().map(stock->createExportStockDto(stock)).collect(Collectors.toList());
    }

    private ExportStockDto createExportStockDto(Stock stock){
        Product product=stock.getProduct();
        return ExportStockDto.builder().productName(product.getName())
                .quantity(stock.getQuantity()).productCategoryName(product.getCategory().getName())
                .productSupplierName(product.getSupplier().getName())
                .productImage(product.getImageUrl()).price(product.getPrice())
                .weight(product.getWeight()).build();
    }
}
