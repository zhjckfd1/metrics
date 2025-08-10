package com.example.prometheus.services;

import com.example.prometheus.repo.TestRepository;
import com.example.prometheus.db.TestEntity;
import io.micrometer.core.instrument.MeterRegistry;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.*;

import java.util.concurrent.TimeUnit;

@Slf4j
@RequiredArgsConstructor
@Service
public class TestService {

    private final MeterRegistry meterRegistry;
    private final MetricService metricService;
    private final TestRepository testRepository;

    public double getCountMetric(){
        return meterRegistry.counter("MessagePerSecond").count();
    }

    public long getCountInRepo(){
        return testRepository.count();
    }

    @PostMapping
    public String save(String text){

        long startTime = System.currentTimeMillis();
        TestEntity testEntity = new TestEntity();
        testEntity.setText(text);

        testEntity = testRepository.save(testEntity);
        testRepository.save(testEntity);
        metricService.testDurationTimer(testEntity).record(System.currentTimeMillis() - startTime, TimeUnit.MILLISECONDS);
        metricService.countMPSMetric(testEntity);
        //meterRegistry.counter("MessagePerSecond").increment();

        return "ok";
    }


}
