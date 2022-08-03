package ro.msg.learning.shop.strategyTest;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import ro.msg.learning.shop.model.Customer;
import ro.msg.learning.shop.model.Location;
import ro.msg.learning.shop.model.ProductOrder;
import ro.msg.learning.shop.repository.CustomerRepository;
import ro.msg.learning.shop.repository.ProductOrderRepository;
import ro.msg.learning.shop.repository.StockRepository;
import ro.msg.learning.shop.service.LocationService;
import ro.msg.learning.shop.service.StockService;

import java.time.LocalDateTime;

@ExtendWith(MockitoExtension.class)
public class UnitTestStrategy {

    private static final String addressCountry="Romania";
    private static final String addressCity="Cluj-Napoca";
    private static final String addressCounty="Cluj";
    private static final String addressStreet="strada Zorilor";
    @Mock
    private CustomerRepository customerRepository;
    @Mock
    private StockRepository stockRepository;
    @Mock
    private StockService stockService;
    @Mock
    private LocationService locationService;
    @Mock
    private ProductOrderRepository productOrderRepository;

    @BeforeEach
    void initialiseData(){

        Location shippedFrom=new Location(1,"Cluj-Napoca Location",addressCountry,addressCity,addressCounty,addressStreet);
        Customer customer=Customer.builder().id(2).email("raul.calugar@yahoo.ro").build();
        ProductOrder order=ProductOrder.builder().addressCountry(addressCountry).addressCounty(addressCounty).addressCity(addressCity).addressStreetAddress(addressStreet).createdAt(LocalDateTime.now()).build();



    }
}
