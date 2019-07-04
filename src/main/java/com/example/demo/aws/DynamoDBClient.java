package com.example.demo.aws;

import com.amazonaws.regions.Regions;
import com.amazonaws.services.dynamodbv2.AmazonDynamoDB;
import com.amazonaws.services.dynamodbv2.AmazonDynamoDBClientBuilder;
import com.amazonaws.services.dynamodbv2.document.DynamoDB;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Service;

@Service
public class DynamoDBClient {

    @Bean
    public DynamoDB getClient() {
        AmazonDynamoDB amazonDynamoDB = AmazonDynamoDBClientBuilder.standard().withRegion(Regions.AP_SOUTHEAST_1).build();
        DynamoDB ddb = new DynamoDB(amazonDynamoDB);
        return ddb;
    }
}
