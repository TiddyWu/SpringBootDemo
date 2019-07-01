package com.example.demo.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Slf4j
public class HelloWorldController {

    @RequestMapping("/hello")
    public String index() {
        log.error("in the controller!");
        return "Hello World! 我爱戴琳！";
    }
}
