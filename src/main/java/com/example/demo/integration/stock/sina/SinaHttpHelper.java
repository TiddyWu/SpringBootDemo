package com.example.demo.integration.stock.sina;

import com.example.demo.integration.stock.StockEntity;
import lombok.extern.slf4j.Slf4j;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpUriRequest;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Component
@Slf4j
public class SinaHttpHelper {
    private static final HttpClient HTTP_CLIENT = HttpClients.createDefault();
    private static final String SINA_ENDPOINT = "http://hq.sinajs.cn/list=";
    private static final String COMMA_DELIMITER = ",";
    private static final String SEMICOLON_DELIMITER = ";";

    /**
     * Build HTTP request.
     * @param stocks stock id array.
     * @return HttpUriRequest
     */
    private HttpUriRequest buildSinaHttpRequest(String[] stocks) {
        String stockList = String.join(",", stocks);
        return new HttpGet(SINA_ENDPOINT + stockList);
    }

    /**
     * Input stock id list and return the formatted SINA result.
     * @param stocks Stock id list.
     * @return Formatted SINA result
     * @throws IOException
     */
    public List<SinaDataModel> getResponse(String[] stocks) throws IOException {
        List<SinaDataModel> sinaDataModelList = new ArrayList<>();

        HttpUriRequest request = buildSinaHttpRequest(stocks);

        HTTP_CLIENT.execute(request, (response) -> {
            String responseString = EntityUtils.toString(response.getEntity());
            String[] stockList = StringUtils.split(responseString, SEMICOLON_DELIMITER);
            for (String stock : stockList) {
                try {
                    SinaDataModel dataModel = convert(stock);
                    sinaDataModelList.add(dataModel);
                } catch (ParseException e) {
                    log.error("parse sina input error", e);
                    return false;
                }
            }
            return true;
        });

        return sinaDataModelList;
    }

    private SinaDataModel convert(String input) throws ParseException {
        int first = input.indexOf("\"");
        int last = input.lastIndexOf("\"");
        String data = input.substring(first + 1, last);
        String[] dataList = data.split(COMMA_DELIMITER);

        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
        Date date = dateFormat.parse(dataList[SinaDataModel.INDEX_DATE] + " " + dataList[SinaDataModel.INDEX_TIMESTAP]);

        return SinaDataModel.builder()
            .name(dataList[SinaDataModel.INDEX_NAME])
            .openingPriceToday(Double.valueOf(dataList[SinaDataModel.INDEX_OPENING_PRICE_TODAY]))
            .closingPriceYesterday(Double.valueOf(dataList[SinaDataModel.INDEX_CLOSING_PRICE_YESTERDAY]))
            .currentPrice(Double.valueOf(dataList[SinaDataModel.INDEX_CURRENT_PRICE]))
            .highestPriceToday(Double.valueOf(dataList[SinaDataModel.INDEX_HIGHEST_PRICE_TODAY]))
            .lowestPriceToday(Double.valueOf(dataList[SinaDataModel.INDEX_LOWEST_PRICE_TODAY]))
            .numberOfTraded(Integer.valueOf(dataList[SinaDataModel.INDEX_NUMBER_OF_TRADED]))
            .transactionAmount(Double.valueOf(dataList[SinaDataModel.INDEX_TRANSACTION_AMOUNT]))
            .buyers(convertEntity(dataList, SinaDataModel.INDEX_BUYER_START, SinaDataModel.INDEX_BUYER_END))
            .sellers(convertEntity(dataList, SinaDataModel.INDEX_SELLER_START, SinaDataModel.INDEX_SELLER_END))
            .date(date)
            .build();

    }

    private List<StockEntity> convertEntity(String[] dataList, int start, int end) {
        List<StockEntity> stockEntities = new ArrayList<>();

        for (int i = start; i <= end; i+=2) {
            stockEntities.add(StockEntity.builder()
                    .share(Double.valueOf(dataList[i]))
                    .price(Double.valueOf(dataList[i+1]))
                    .build());
        }

        return stockEntities;
    }
}
