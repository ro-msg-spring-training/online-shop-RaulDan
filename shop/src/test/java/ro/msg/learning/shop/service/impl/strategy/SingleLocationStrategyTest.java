package ro.msg.learning.shop.service.impl.strategy;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.mockito.junit.jupiter.MockitoExtension;
import ro.msg.learning.shop.exceptions.MissingStockException;
import ro.msg.learning.shop.model.*;
import ro.msg.learning.shop.service.LocationService;
import ro.msg.learning.shop.service.StockService;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
@ExtendWith(MockitoExtension.class)
class SingleLocationStrategyTest {

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

    private List<Location> locations;


    @Mock
    private LocationService locationService;
    @Mock
    private StockService stockService;

    @InjectMocks
    private SingleLocationStrategy singleLocationStrategy;

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

        this.locations = new ArrayList<>();
        this.locations.add(locationCluj);
        this.locations.add(locationMures);
    }

    private void populateStocks() {
        this.stockLaptopHPProductCluj = Stock.builder().location(locationCluj).product(productLaptopHP).quantity(10).build();
        this.stockLaptopDELLProductCluj = Stock.builder().location(locationCluj).product(productLaptopDELL).quantity(8).build();
        this.stockLaptopHPProductTarguMures = Stock.builder().location(locationMures).product(productLaptopHP).quantity(20).build();
        this.stockLaptopDELLProductTarguMures = Stock.builder().location(locationMures).product(productLaptopDELL).quantity(30).build();
    }

    @Test
    void testSingleLocationStrategy() {
        init();
        // Given ( for a specific method you have a specific input data
        this.orderDetails = new ArrayList<>();
        OrderDetail orderDetail = OrderDetail.builder().product(productLaptopHP).quantity(8).build();
        this.orderDetails.add(orderDetail);
        orderDetail = OrderDetail.builder().product(productLaptopDELL).quantity(1).build();
        this.orderDetails.add(orderDetail);
        // When
        when(locationService.findAll()).thenReturn(locations);
        when(stockService.findAllByLocation(locationCluj)).thenReturn(Arrays.asList(stockLaptopDELLProductCluj, stockLaptopHPProductCluj));

        // Method call
        List<Stock> stockList = singleLocationStrategy.createOrder(this.orderDetails);
        // Assert
        assertFalse(stockList.isEmpty());
        assertThat(stockList).hasSize(1);
        assertThat(stockList.get(0).getLocation().equals(locationCluj));
    }

    // Check if MissingStockException Is Thrown
    @Test
    void noStockAvailableTest(){
        init();

        this.orderDetails = new ArrayList<>();
        OrderDetail orderDetail = OrderDetail.builder().product(productLaptopHP).quantity(800).build();
        this.orderDetails.add(orderDetail);
        orderDetail = OrderDetail.builder().product(productLaptopDELL).quantity(300).build();
        this.orderDetails.add(orderDetail);

        when(locationService.findAll()).thenReturn(locations);
        when(stockService.findAllByLocation(locationCluj)).thenReturn(Arrays.asList(stockLaptopDELLProductCluj, stockLaptopHPProductCluj));
        when(stockService.findAllByLocation(locationMures)).thenReturn(Arrays.asList(stockLaptopDELLProductTarguMures,stockLaptopHPProductTarguMures));
        // Assert
        assertThrows(MissingStockException.class,()->singleLocationStrategy.createOrder(orderDetails));
    }

}