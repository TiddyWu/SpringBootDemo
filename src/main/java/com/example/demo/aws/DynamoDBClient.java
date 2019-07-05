package com.example.demo.aws;

import com.amazonaws.regions.Regions;
import com.amazonaws.services.dynamodbv2.AmazonDynamoDB;
import com.amazonaws.services.dynamodbv2.AmazonDynamoDBClientBuilder;
import com.amazonaws.services.dynamodbv2.document.DynamoDB;
import com.amazonaws.services.dynamodbv2.document.Table;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Service;

@Service
public class DynamoDBClient {
    private DynamoDB dynamoDB;

    @Autowired
    public void setDynamoDB(DynamoDB dynamoDB) {
        this.dynamoDB = dynamoDB;
    }

    @Bean
    public DynamoDB getClient() {
        AmazonDynamoDB amazonDynamoDB = AmazonDynamoDBClientBuilder.standard().withRegion(Regions.AP_SOUTHEAST_1).build();
        return new DynamoDB(amazonDynamoDB);
    }

    @Bean(name = "DDBDemoTable")
    public Table getDemoTable() {
        return dynamoDB.getTable("demo");
    }
}
