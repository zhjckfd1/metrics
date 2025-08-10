package com.example.prometheus.db;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@MappedSuperclass
public class BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

//    @CreationTimestamp    //можем использовать @CreationTimestamp и @UpdateTimestamp вместо @PrePersist и @PreUpdate (доп. логика не нужна...)
//    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "created_on")
    private LocalDateTime createdOn;

//    @UpdateTimestamp
//    @Temporal(TemporalType.TIMESTAMP)
    private LocalDateTime updatedOn;

    @PrePersist
    public void prePersist() {
        LocalDateTime now = LocalDateTime.now();
        this.setCreatedOn(now);
        this.setUpdatedOn(now);
    }

    @PreUpdate
    public void PreUpdate() {
        this.setUpdatedOn(LocalDateTime.now());
    }
}
