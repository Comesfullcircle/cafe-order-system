package com.example.cafeordersystem.domain;

import com.mysql.cj.x.protobuf.MysqlxDatatypes;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Column;
import org.springframework.data.relational.core.mapping.Table;

import java.time.ZonedDateTime;

@Table(name = "stores")
public class Store {

    @Id
    private int storeId;

    @Column
    private String name;

    @Column
    private String address;

    @Column
    private String phoneNumber;

    @Column
    private String openAt; // 시간만 저장할 예정 예시 0900 오전 9시

    @Column
    private String closeAt; // 시간만 저장할 예정 예시 1800 오후 6시
}

