package com.fico.fawb.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;

@RestController
public class HealthController {

    @GetMapping("/")
    public String health()
    {
        return "ACTIVE at: "+new Date();
    }
}
