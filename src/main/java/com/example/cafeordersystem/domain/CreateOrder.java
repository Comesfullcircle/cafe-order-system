package com.example.cafeordersystem.domain;

import lombok.Builder;
import lombok.Getter;

import java.util.Map;

@Getter
@Builder
public class CreateOrder {
    private int customerId;
    private int storeId;
    private Map<Integer, Integer> quantityByProduct; //["아이스 아메키라노", 3]
}
