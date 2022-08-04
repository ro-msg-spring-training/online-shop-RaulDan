package ro.msg.learning.shop.service.impl;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.test.context.TestPropertySource;
import ro.msg.learning.shop.configurations.StrategyConfiguration;
import ro.msg.learning.shop.model.*;
import ro.msg.learning.shop.repository.CustomerRepository;
import ro.msg.learning.shop.repository.OrderDetailsRepository;
import ro.msg.learning.shop.repository.ProductOrderRepository;
import ro.msg.learning.shop.repository.StockRepository;
import ro.msg.learning.shop.service.StockService;
import ro.msg.learning.shop.service.impl.CustomerServiceImpl;
import ro.msg.learning.shop.service.impl.LocationServiceImpl;
import ro.msg.learning.shop.service.impl.OrderDetailServiceImpl;
import ro.msg.learning.shop.service.impl.OrderServiceImpl;
import ro.msg.learning.shop.service.impl.strategy.SingleLocationStrategy;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@ExtendWith(MockitoExtension.class)
@TestPropertySource(locations="classpath:application.properties")
public class OrderServiceImplTest {

    private static final String addressCountry = "Romania";
    private static final String addressCity = "Cluj-Napoca";
    private static final String addressCounty = "Cluj";
    private static final String addressStreet = "strada Zorilor";

    private static final String muresCounty = "Mures";

    private static final String muresCity = "Targu Mures";
    @Mock
    private CustomerRepository customerRepository;

    private StrategyConfiguration configuration;

    @Mock
    private SingleLocationStrategy singleLocationStrategy;
    @Mock
    private StockRepository stockRepository;
    @Mock
    private StockService stockService;
    @Mock
    private LocationServiceImpl locationService;
    @Mock
    private ProductOrderRepository productOrderRepository;

    @Mock
    private OrderDetailsRepository orderDetailsRepository;

    @InjectMocks
    private CustomerServiceImpl customerService;

    @InjectMocks
    private OrderDetailServiceImpl orderDetailService;

    @InjectMocks
    private OrderServiceImpl orderService;
    private Location locationCluj;
    private Location locationMures;
    private Customer customer;
    private ProductOrder order;
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
    
    @BeforeEach
    void initialiseData() {
        this.locationCluj = new Location(1, "Cluj-Napoca Location", addressCountry, addressCity, addressCounty, addressStreet);
        this.locationMures = new Location(2, "Targu Mures Location", addressCountry, muresCity, muresCounty, addressStreet);
        this.customer = Customer.builder().id(2).email("raul.calugar@yahoo.ro").build();
        this.order = ProductOrder.builder().addressCountry(addressCountry).addressCounty(addressCounty).addressCity(addressCity).addressStreetAddress(addressStreet).createdAt(LocalDateTime.now()).build();
        this.category = ProductCategory.builder().id(1).name("IT").description("IT Products").build();
        this.supplierHP = Supplier.builder().id(1).name("HP").build();
        this.supplierDELL = Supplier.builder().id(2).name("DELL").build();
        this.productLaptopHP = Product.builder().id(1).category(category).supplier(supplierHP).name("Laptop HP").build();
        this.productLaptopDELL = Product.builder().id(2).category(category).supplier(supplierDELL).name("Laptop DELL").build();
        this.stockLaptopHPProductCluj = Stock.builder().location(locationCluj).product(productLaptopHP).quantity(10).build();
        this.stockLaptopDELLProductCluj = Stock.builder().location(locationCluj).product(productLaptopDELL).quantity(8).build();
        this.stockLaptopHPProductTarguMures = Stock.builder().location(locationMures).product(productLaptopHP).quantity(20).build();
        this.stockLaptopDELLProductTarguMures = Stock.builder().location(locationMures).product(productLaptopDELL).quantity(30).build();
        this.orderDetails=new ArrayList<>();
        OrderDetail orderDetail=OrderDetail.builder().product(productLaptopHP).quantity(7).build();
        this.orderDetails.add(orderDetail);
        orderDetail=OrderDetail.builder().product(productLaptopDELL).quantity(8).build();
        this.orderDetails.add(orderDetail);

    }

    @Test
    public void checkOrder() {
        ProductOrder productOrder;
        productOrder=orderService.createOrder(this.order,this.orderDetails);
    }
}
