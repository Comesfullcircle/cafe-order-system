package com.example.cafeordersystem.service;

import com.example.cafeordersystem.domain.CreateOrder;
import com.example.cafeordersystem.domain.StoreProduct;
import com.example.cafeordersystem.repository.OrderRepository;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.sql.In;

import java.util.HashMap;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class OrderServiceTest {
    @Mock
    OrderRepository orderRepository;

    @Mock
    StoreService storeService;

    @InjectMocks
    OrderService orderService;

    @Test
    @DisplayName("구매 수량이 재고 수량보다 작을 때, 정상 주문이 가능하다.")
    public void stockQuantityTest_success(){
        //given
        int butQuantity = 5;
        int stockQuantity = 50;
        HashMap<Integer, Integer> map = new HashMap<>();
        map.put(1, butQuantity);
        CreateOrder createOrder = CreateOrder.builder()
                .storeId(1)
                .customerId(1)
                .quantityByProduct(map)
                .build();
        StoreProduct stock = StoreProduct.builder()
                .stockQuantity(stockQuantity)
                .build();
        when(storeService.getStoreProduct(1,1)).thenReturn(stock);

        //when
        orderService.newOrder(createOrder);

        //then
        assertThat(stock.getStockQuantity()).isEqualTo(stockQuantity-butQuantity);
    }


    @Test
    @DisplayName("구매 수량이 재고 수량보다 많을때, 정상 주문은 불가능하다.")
    public void stockQuantityTest_failure(){
        //given
        int butQuantity = 100;
        int stockQuantity = 50;
        HashMap<Integer, Integer> map = new HashMap<>();
        map.put(1, butQuantity);
        CreateOrder createOrder = CreateOrder.builder()
                .storeId(1)
                .customerId(1)
                .quantityByProduct(map)
                .build();
        StoreProduct stock = StoreProduct.builder()
                .stockQuantity(stockQuantity)
                .build();
        when(storeService.getStoreProduct(1,1)).thenReturn(stock);

        //when & then
        RuntimeException runtimeException = assertThrows(RuntimeException.class, ()->{
            orderService.newOrder(createOrder);
        });
        assertThat(runtimeException.getMessage()).isEqualTo("재고가 없습니다.");
    }

}