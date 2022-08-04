package ro.msg.learning.shop.service.impl.test;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ro.msg.learning.shop.model.*;
import ro.msg.learning.shop.service.*;
import ro.msg.learning.shop.utils.Status;

@Service
@RequiredArgsConstructor
public class ManipulateDatabaseServiceImpl implements ManipulateDatabaseService {

    private final CustomerService customerService;
    private final ProductService productService;
    private final LocationService locationService;
    private final StockService stockService;
    private final ProductCategoryService productCategoryService;
    private final SupplierService supplierService;
    private final ProductOrderService productOrderService;
    private final OrderDetailService orderDetailService;
    private static final String ADDRESS_COUNTRY = "Romania";
    private static final String ADDRESS_CITY = "Cluj-Napoca";
    private static final String ADDRESS_COUNTY = "Cluj";
    private static final String ADDRESS_STREET = "strada Zorilor";
    private static final String MURES_COUNTY = "Mures";
    private static final String MURES_CITY = "Targu Mures";

    @Override
    public Status populateDatabase() {
        ProductCategory productCategory=ProductCategory.builder().id(1).name("IT").description("IT Products").build();
        productCategoryService.save(productCategory);

        Supplier supplierHP = Supplier.builder().id(1).name("HP").build();
        supplierService.save(supplierHP);
        Supplier supplierDELL = Supplier.builder().id(2).name("DELL").build();
        supplierService.save(supplierDELL);

        Product productLaptopHP=Product.builder().id(1).category(productCategory).supplier(supplierHP).name("Laptop HP").build();
        productService.save(productLaptopHP);
        Product productLaptopDELL = Product.builder().id(2).category(productCategory).supplier(supplierDELL).name("Laptop DELL").build();
        productService.save(productLaptopDELL);

        Customer customer=Customer.builder().id(1).username("John Doe").build();
        customerService.save(customer);

        Location locationCluj = new Location(1, "Cluj-Napoca Location", ADDRESS_COUNTRY, ADDRESS_CITY, ADDRESS_COUNTY, ADDRESS_STREET);
        locationService.save(locationCluj);
        Location locationMures = new Location(2, "Targu Mures Location", ADDRESS_COUNTRY, MURES_CITY, MURES_COUNTY, ADDRESS_STREET);
        locationService.save(locationMures);

        Stock stockLaptopHPProductCluj = Stock.builder().location(locationCluj).product(productLaptopHP).quantity(10).build();
        stockService.save(stockLaptopHPProductCluj);
        Stock stockLaptopDELLProductCluj = Stock.builder().location(locationCluj).product(productLaptopDELL).quantity(8).build();
        stockService.save(stockLaptopDELLProductCluj);
        Stock stockLaptopHPProductTarguMures = Stock.builder().location(locationMures).product(productLaptopHP).quantity(20).build();
        stockService.save(stockLaptopHPProductTarguMures);
        Stock stockLaptopDELLProductTarguMures = Stock.builder().location(locationMures).product(productLaptopDELL).quantity(30).build();
        stockService.save(stockLaptopDELLProductTarguMures);
        return Status.SUCCESS;
    }

    @Override
    public Status clearDatabase() {

        orderDetailService.deleteAll();
        productOrderService.deleteAll();
        customerService.deleteAll();
        stockService.deleteAll();
        productService.deleteAll();
        productCategoryService.deleteAll();
        locationService.deleteAll();

        return Status.SUCCESS;
    }
}
