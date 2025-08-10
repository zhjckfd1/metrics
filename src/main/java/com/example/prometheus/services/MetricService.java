package com.example.prometheus.services;

import com.example.prometheus.repo.TestRepository;
import com.example.prometheus.db.TestEntity;
import io.micrometer.core.instrument.Counter;
import io.micrometer.core.instrument.Gauge;
import io.micrometer.core.instrument.MeterRegistry;
import io.micrometer.core.instrument.Timer;
import org.springframework.stereotype.Component;

@Component
public class MetricService {

    private final MeterRegistry meterRegistry;

    public MetricService(MeterRegistry meterRegistry, TestRepository testRepository) {
        this.meterRegistry = meterRegistry;
        Runtime runtime = Runtime.getRuntime();
        Gauge memoryUsage = Gauge.builder("memory_usage_bytes", runtime::freeMemory)
                .description("Current free memory in bytes")
                .register(meterRegistry);
        Gauge.builder("test_gauge", testRepository::count).description("Количество тестов").register(meterRegistry);

    }

    public void countMPSMetric(TestEntity testEntity) {
//        Counter mpsCounter = Counter.builder("MessagePerSecond").description("кол-во сообщений в секунду").tags("test_text", testEntity.getText()).register(meterRegistry);
//        mpsCounter.increment();
        meterRegistry.counter("MessagePerSecond", "test_text", testEntity.getText()).increment();
    }


    public Timer testDurationTimer(TestEntity testEntity){
        return Timer.builder("test_duration").description("Время выполнения теста").tags("test_text", testEntity.getText()).register(meterRegistry);
    }
}
