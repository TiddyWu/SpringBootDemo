package com.example.demo.controller;

import com.amazonaws.regions.Regions;
import com.amazonaws.services.dynamodbv2.AmazonDynamoDB;
import com.amazonaws.services.dynamodbv2.AmazonDynamoDBClientBuilder;
import com.amazonaws.services.dynamodbv2.document.DynamoDB;
import com.amazonaws.services.dynamodbv2.document.Item;
import com.amazonaws.services.dynamodbv2.document.ItemCollection;
import com.amazonaws.services.dynamodbv2.document.QueryOutcome;
import com.amazonaws.services.dynamodbv2.document.Table;
import com.amazonaws.services.dynamodbv2.document.spec.QuerySpec;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.HashMap;

@Controller
@Slf4j
public class DDBController {

    @Autowired
    @Qualifier("DDBDemoTable")
    private Table DDBDemoTable;


    @GetMapping("/createTable")
    @ResponseBody
    public String createDDBTable() {

        HashMap<String, String> nameMap = new HashMap<String, String>();
        nameMap.put("#yr", "id");

        HashMap<String, Object> valueMap = new HashMap<String, Object>();
        valueMap.put(":yyyy", "123");

        QuerySpec querySpec = new QuerySpec().withKeyConditionExpression("#yr = :yyyy").withNameMap(nameMap)
                                             .withValueMap(valueMap);

        ItemCollection<QueryOutcome> items = DDBDemoTable.query(querySpec);
        String res = "";
        for(Item item : items) {
            res += item.toJSONPretty();
        }
        return res;
    }
}
