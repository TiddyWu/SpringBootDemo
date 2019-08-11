package com.example.demo.controller;

import com.example.demo.integration.stock.sina.SinaHttpHelper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.io.IOException;

@Controller
@Slf4j
public class StockController {
    SinaHttpHelper sinaHttpHelper;

    @Autowired
    private void setSinaHttpHelper(SinaHttpHelper sinaHttpHelper) {
        this.sinaHttpHelper = sinaHttpHelper;
    }

    @GetMapping("/stock/data")
    @ResponseBody
    public Object redis(@RequestParam("id") String[] ids) {

        try {
            return sinaHttpHelper.getResponse(ids);
        } catch (IOException e) {
            log.error("get stock info from sina error", e);
        }
        return null;
    }
}
