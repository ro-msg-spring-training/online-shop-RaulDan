package ro.msg.learning.shop.service.impl.strategy;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.mockito.junit.jupiter.MockitoExtension;
import ro.msg.learning.shop.model.*;
import ro.msg.learning.shop.service.StockService;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
@ExtendWith(MockitoExtension.class)
class MostAbundantStrategyTest {

    @Mock
    private StockService stockService;

    private static final String ADDRESS_COUNTRY = "Romania";
    private static final String ADDRESS_CITY = "Cluj-Napoca";
    private static final String ADDRESS_COUNTY = "Cluj";
    private static final String ADDRESS_STREET = "strada Zorilor";
    private static final String MURES_COUNTY = "Mures";
    private static final String MURES_CITY = "Targu Mures";

    private Location locationCluj;
    private Location locationMures;
    private ProductCategory category;
    private Supplier supplierHP;
    private Supplier supplierDELL;
    private Product productLaptopHP;
    private Product productLaptopDELL;
    private Stock stockLaptopHPProductCluj;
    private Stock stockLaptopDELLProductCluj;
    private Stock stockLaptopHPProductTarguMures;
    private Stock stockLaptopDELLProductTarguMures;
    private List<OrderDetail> orderDetails;

    private List<Stock> stocks;

    @InjectMocks
    private MostAbundantStrategy mostAbundantStrategy;

    void init() {

        populateCategory();
        populateLocations();
        populateSuppliers();
        populateProducts();
        populateStocks();
    }

    private void populateCategory() {
        this.category = ProductCategory.builder().id(1).name("IT").description("IT Products").build();
    }

    private void populateProducts() {
        this.productLaptopHP = Product.builder().id(1).category(category).supplier(supplierHP).name("Laptop HP").build();
        this.productLaptopDELL = Product.builder().id(2).category(category).supplier(supplierDELL).name("Laptop DELL").build();
    }

    private void populateSuppliers() {
        this.supplierHP = Supplier.builder().id(1).name("HP").build();
        this.supplierDELL = Supplier.builder().id(2).name("DELL").build();
    }


    private void populateLocations() {
        this.locationCluj = new Location(1, "Cluj-Napoca Location", ADDRESS_COUNTRY, ADDRESS_CITY, ADDRESS_COUNTY, ADDRESS_STREET);
        this.locationMures = new Location(2, "Targu Mures Location", ADDRESS_COUNTRY, MURES_CITY, MURES_COUNTY, ADDRESS_STREET);
    }

    private void populateStocks() {
        this.stocks=new ArrayList<>();
        this.stockLaptopHPProductCluj = Stock.builder().location(locationCluj).product(productLaptopHP).quantity(40).build();
        this.stockLaptopDELLProductCluj = Stock.builder().location(locationCluj).product(productLaptopDELL).quantity(8).build();
        this.stockLaptopHPProductTarguMures = Stock.builder().location(locationMures).product(productLaptopHP).quantity(20).build();
        this.stockLaptopDELLProductTarguMures = Stock.builder().location(locationMures).product(productLaptopDELL).quantity(30).build();
        this.stocks.addAll(Arrays.asList(stockLaptopHPProductCluj,stockLaptopDELLProductCluj,stockLaptopHPProductTarguMures,stockLaptopDELLProductTarguMures));
    }

    @Test
    void testDifferentStocks(){
        init();

        this.orderDetails = new ArrayList<>();
        OrderDetail orderDetail = OrderDetail.builder().product(productLaptopHP).quantity(8).build();
        this.orderDetails.add(orderDetail);
        orderDetail = OrderDetail.builder().product(productLaptopDELL).quantity(3).build();
        this.orderDetails.add(orderDetail);

        when(stockService.findAll()).thenReturn(stocks);
        List<Stock> result=mostAbundantStrategy.createOrder(orderDetails);
        assertThat(result.get(0).getLocation().equals(locationCluj) && result.get(1).getLocation().equals(locationMures));


    }

}