package com.example.demo.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletResponse;

@Controller
@Slf4j
public class HelloWorldController {

    @RequestMapping("/")
    public String index() {
        return "index";
    }


    @GetMapping("/time/{time}")
    @ResponseBody
    public String loveTime(@PathVariable(name="time") String times,  HttpServletResponse response) {
        response.setHeader("Access-Control-Allow-Origin", "*");
        return String.valueOf(Integer.parseInt(times) + 1);
    }
}
