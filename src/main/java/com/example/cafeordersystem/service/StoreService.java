package com.example.cafeordersystem.service;

import com.example.cafeordersystem.domain.StoreProduct;
import com.example.cafeordersystem.repository.StoreProductRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class StoreService {
    private final StoreProductRepository storeProductRepository;

    public StoreService(StoreProductRepository storeProductRepository) {
        this.storeProductRepository = storeProductRepository;
    }

    public StoreProduct getStoreProduct(int storeId, int productId) {
        Optional<StoreProduct> storeProductOptional = storeProductRepository.findByStoreIdAndProductId(storeId, productId);
        if (storeProductOptional.isEmpty()){
            throw new RuntimeException("존재하지 않습니다.");
        }
        return storeProductOptional.get();
    }

    //리스트 형태의 StoreProducts를 저장하는 메소드
    public void saveAll(List<StoreProduct> storeProducts){
        storeProductRepository.saveAll(storeProducts);
    }
}
