package com.example.demo.aws;

import com.amazonaws.services.dynamodbv2.document.DynamoDB;
import com.amazonaws.services.dynamodbv2.document.Table;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Service;

@Service
public class DynamoDemoTable {
    @Autowired
    private DynamoDB dynamoDB;

    @Bean(name = "DDBDemoTable")
    public Table getDemoTable() {
        return dynamoDB.getTable("demo");
    }
}



