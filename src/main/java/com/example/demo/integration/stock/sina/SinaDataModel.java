package com.example.demo.integration.stock.sina;

import com.example.demo.integration.stock.StockEntity;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;
import java.util.List;

@Setter
@Getter
@Builder
public class SinaDataModel {
    public static final int INDEX_NAME = 0;
    public static final int INDEX_OPENING_PRICE_TODAY = 1;
    public static final int INDEX_CLOSING_PRICE_YESTERDAY = 2;
    public static final int INDEX_CURRENT_PRICE = 3;
    public static final int INDEX_HIGHEST_PRICE_TODAY = 4;
    public static final int INDEX_LOWEST_PRICE_TODAY = 5;
    public static final int INDEX_NUMBER_OF_TRADED = 8;
    public static final int INDEX_TRANSACTION_AMOUNT = 9;

    public static final int INDEX_BUYER_START = 10;
    public static final int INDEX_BUYER_END = 19;
    public static final int INDEX_SELLER_START = 20;
    public static final int INDEX_SELLER_END = 29;
    public static final int INDEX_DATE = 30;
    public static final int INDEX_TIMESTAP = 31;

    String name;
    double openingPriceToday;
    double closingPriceYesterday;
    double currentPrice;
    double highestPriceToday;
    double lowestPriceToday;
    int numberOfTraded;
    double transactionAmount;
    List<StockEntity> buyers;
    List<StockEntity> sellers;
    Date date;
}
