package com.example.demo.integration.stock;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Setter
@Getter
@Builder
@ToString
public class StockEntity {
    double price;
    double share;
}
