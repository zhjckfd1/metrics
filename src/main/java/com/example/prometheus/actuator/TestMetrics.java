package com.example.prometheus.actuator;

import com.example.prometheus.db.TestEntity;
import io.micrometer.core.instrument.MeterRegistry;
import jakarta.persistence.PostPersist;
import org.springframework.stereotype.Component;

@Component
public class TestMetrics {
    private final MeterRegistry meterRegistry;

    public TestMetrics(MeterRegistry meterRegistry) {
        this.meterRegistry = meterRegistry;
    }

    @PostPersist
    public void onAfterCreate(TestEntity testEntity) {
        meterRegistry.counter("MessagePerSecondInListener", "testMetricActuator1", testEntity.getText()).increment();
    }
}
