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
    private Location location1;
    private Location location2;
    private Customer customer;
    private ProductOrder order;
    private ProductCategory category;
    private Supplier supplier1;
    private Supplier supplier2;
    private Product product1;
    private Product product2;
    private Stock stock1Cluj;
    private Stock stock2Cluj;
    private Stock stock1Mures;
    private Stock stock2Mures;

    private List<OrderDetail> orderDetails;
    
    @BeforeEach
    void initialiseData() {
        this.location1 = new Location(1, "Cluj-Napoca Location", addressCountry, addressCity, addressCounty, addressStreet);
        this.location2 = new Location(2, "Targu Mures Location", addressCountry, muresCity, muresCounty, addressStreet);
        this.customer = Customer.builder().id(2).email("raul.calugar@yahoo.ro").build();
        this.order = ProductOrder.builder().addressCountry(addressCountry).addressCounty(addressCounty).addressCity(addressCity).addressStreetAddress(addressStreet).createdAt(LocalDateTime.now()).build();
        this.category = ProductCategory.builder().id(1).name("IT").description("IT Products").build();
        this.supplier1 = Supplier.builder().id(1).name("HP").build();
        this.supplier2 = Supplier.builder().id(2).name("DELL").build();
        this.product1 = Product.builder().id(1).category(category).supplier(supplier1).name("Laptop HP").build();
        this.product2 = Product.builder().id(2).category(category).supplier(supplier2).name("Laptop DELL").build();
        this.stock1Cluj = Stock.builder().location(location1).product(product1).quantity(10).build();
        this.stock2Cluj = Stock.builder().location(location1).product(product2).quantity(8).build();
        this.stock1Mures = Stock.builder().location(location2).product(product1).quantity(20).build();
        this.stock2Mures = Stock.builder().location(location2).product(product2).quantity(30).build();
        this.orderDetails=new ArrayList<>();
        OrderDetail orderDetail=OrderDetail.builder().product(product1).quantity(7).build();
        this.orderDetails.add(orderDetail);
        orderDetail=OrderDetail.builder().product(product2).quantity(8).build();
        this.orderDetails.add(orderDetail);

    }

    @Test
    public void checkOrder() {
        ProductOrder productOrder;
        productOrder=orderService.createOrder(this.order,this.orderDetails);
    }
}
