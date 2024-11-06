package com.example.cafeordersystem.service;

import com.example.cafeordersystem.domain.CreateOrder;
import com.example.cafeordersystem.domain.Order;
import com.example.cafeordersystem.domain.StoreProduct;
import com.example.cafeordersystem.repository.OrderRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Service
public class OrderService {
    private final OrderRepository orderRepository;
    private final StoreService storeService;

    public OrderService(OrderRepository orderRepository, StoreService storeService) {
        this.orderRepository = orderRepository;
        this.storeService = storeService;
    }

    public void newOrder(CreateOrder createOrder){
        //customerId를 받는 부분도 있으니 ,Id 값을 받았을때 존재하지 않는 customer 라면 마찬가지로
        //주문 받지 않게 로직 생성하기


        //재고 데이터를 고려하는 로직
        List<StoreProduct> storeProducts = new ArrayList<>();

        for(Map.Entry<Integer, Integer> entry : createOrder.getQuantityByProduct().entrySet()){
            Integer productId = entry.getKey();
            Integer buyQuantity = entry.getValue();

            StoreProduct storeProduct = storeService.getStoreProduct(
                    createOrder.getStoreId(),
                    productId);
            int stockQuantity = storeProduct.getStockQuantity();

            if (buyQuantity > stockQuantity) {
                throw new RuntimeException("재고가 없습니다.");
            }
            storeProduct.adjustStockQuantity(buyQuantity);
            storeProducts.add(storeProduct); // 재고 관리를 위해 add 꼭  추가
        }

        Order entity = Order.newOrder(createOrder);
        orderRepository.save(entity);
        storeService.saveAll(storeProducts);
    }
}