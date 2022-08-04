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
import ro.msg.learning.shop.service.OrderDetailService;
import ro.msg.learning.shop.service.ProductOrderService;
import ro.msg.learning.shop.service.ProductService;
import ro.msg.learning.shop.service.StockService;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

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
    private ObjectMapper objectMapper;
    private OrderCreationDto orderCreationDto;

    @BeforeEach
    void initialiseTest() throws Exception {

        mockMvc.perform(post("/test/populateDatabase")).andExpect(status().isOk());
        Product productLaptopHP = productService.getProduct(1);
        Product productLaptopDELL = productService.getProduct(2);
        List<OrderProductDto> products=new ArrayList<>();
        OrderProductDto dto=OrderProductDto.builder().product(productLaptopHP).productId(productLaptopHP.getId()).quantity(5).build();
        products.add(dto);
        dto=OrderProductDto.builder().product(productLaptopDELL).productId(productLaptopDELL.getId()).quantity(6).build();
        products.add(dto);
        orderCreationDto=OrderCreationDto.builder().build();
        orderCreationDto.setCreatedAt(LocalDateTime.now());
        orderCreationDto.setDeliveryAddressCity("Cluj-Napoca");
        orderCreationDto.setDeliveryAddressCountry("Romania");
        orderCreationDto.setDeliveryAddressStreet("Strada Observatorului");
        orderCreationDto.setDeliveryAddressCounty("Cluj");
        orderCreationDto.setProducts(products);

    }

    @Test
    void createOrderSuccess() throws Exception {
        mockMvc.perform(post("/createOrder")
                .content(objectMapper.writeValueAsString(orderCreationDto))
                .contentType(MediaType.APPLICATION_JSON).accept(MediaType.APPLICATION_JSON)
        ).andExpect(status().is2xxSuccessful());

    }

    @AfterEach
    void tearDown() throws Exception {
        mockMvc.perform(post("/test/clearDatabase")).andExpect(status().isOk());
    }
}