package com.example.prometheus.controllers;

import com.example.prometheus.services.TestService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/test")
public class TestController {

    private final TestService testService;

    @GetMapping("check")
    public String healthCheck(){
        return "work";
    }

    @GetMapping("/count/metric")
    public double getCountMetric(){
        return testService.getCountMetric();
    }

    @GetMapping("/count/repo")
    public double getCountInRepo(){
        return testService.getCountInRepo();
    }

    @PostMapping
    public String createTest(@RequestParam String text){
        return testService.save(text);
    }


}
