package ro.msg.learning.shop.service.impl;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.Before;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import ro.msg.learning.shop.ShopApplication;
import ro.msg.learning.shop.dtos.orders.OrderCreationDto;
import ro.msg.learning.shop.dtos.orders.OrderProductDto;
import ro.msg.learning.shop.model.Product;
import ro.msg.learning.shop.service.*;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.Assert.assertTrue;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.MOCK,
        classes = ShopApplication.class)
@AutoConfigureMockMvc
@TestPropertySource(locations = "classpath:application-test.properties")
@ActiveProfiles("test")
@RunWith(SpringRunner.class)
class OrderServiceImplTest {

    @Autowired
    private MockMvc mockMvc;
    @Autowired
    private ProductService productService;
    @Autowired
    private StockService stockService;
    @Autowired
    private LocationService locationService;

    @Autowired
    private ObjectMapper objectMapper;
    private OrderCreationDto orderCreationDto;
    private Product productLaptopHP;
    private Product productLaptopDELL;

    @BeforeEach
    void initialiseTest() throws Exception {

        mockMvc.perform(post("/test/populateDatabase")).andExpect(status().isOk());
        productLaptopHP = productService.getProduct(1);
        productLaptopDELL = productService.getProduct(2);

        orderCreationDto=OrderCreationDto.builder().build();
        orderCreationDto.setCreatedAt(LocalDateTime.now());
        orderCreationDto.setDeliveryAddressCity("Cluj-Napoca");
        orderCreationDto.setDeliveryAddressCountry("Romania");
        orderCreationDto.setDeliveryAddressStreet("Strada Observatorului");
        orderCreationDto.setDeliveryAddressCounty("Cluj");

    }

    private void initialiseSuccessOrder(){
        List<OrderProductDto> products=new ArrayList<>();
        OrderProductDto dto=OrderProductDto.builder().product(productLaptopHP).productId(productLaptopHP.getId()).quantity(5).build();
        products.add(dto);
        dto=OrderProductDto.builder().product(productLaptopDELL).productId(productLaptopDELL.getId()).quantity(6).build();
        products.add(dto);
        orderCreationDto.setProducts(products);

    }

    private void initialFailedOrder(){
        List<OrderProductDto> products=new ArrayList<>();
        OrderProductDto dto=OrderProductDto.builder().product(productLaptopHP).productId(productLaptopHP.getId()).quantity(500).build();
        products.add(dto);
        dto=OrderProductDto.builder().product(productLaptopDELL).productId(productLaptopDELL.getId()).quantity(600).build();
        products.add(dto);
        orderCreationDto.setProducts(products);
    }
    @Test
    void createOrderSuccessSingleLocation() throws Exception {
        initialiseSuccessOrder();
        mockMvc.perform(post("/createOrder")
                .content(objectMapper.writeValueAsString(orderCreationDto))
                .contentType(MediaType.APPLICATION_JSON).accept(MediaType.APPLICATION_JSON)
        ).andExpect(status().is2xxSuccessful());
        assertThat(stockService.findAll().get(0).getLocation().equals(locationService.getLocationById(1)));

    }

    @Test
    void createOrderSuccessMostAbundantLocation() throws Exception{
        initialiseSuccessOrder();
        mockMvc.perform(post("/createOrder")
                .content(objectMapper.writeValueAsString(orderCreationDto))
                .contentType(MediaType.APPLICATION_JSON).accept(MediaType.APPLICATION_JSON)
        ).andExpect(status().is2xxSuccessful());
        assertThat(stockService.findAll().get(0).getLocation().equals(locationService.getLocationById(2)));
    }

    @Test
    void missingStockException() throws Exception{
        initialFailedOrder();
        mockMvc.perform(post("/createOrder")
                .content(objectMapper.writeValueAsString(orderCreationDto))
                .contentType(MediaType.APPLICATION_JSON).accept(MediaType.APPLICATION_JSON)
        ).andExpect(status().is5xxServerError());
    }

    @AfterEach
    void tearDown() throws Exception {
        mockMvc.perform(post("/test/clearDatabase")).andExpect(status().isOk());
    }
}