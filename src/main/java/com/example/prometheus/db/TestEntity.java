package com.example.prometheus.db;

import com.example.prometheus.actuator.TestMetrics;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "test_table")
@EntityListeners(TestMetrics.class)
public class TestEntity extends BaseEntity {

    private String text;

}
