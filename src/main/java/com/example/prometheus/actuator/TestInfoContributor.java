package com.example.prometheus.actuator;

import com.example.prometheus.repo.TestRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.actuate.info.Info.Builder;
import org.springframework.boot.actuate.info.InfoContributor;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

@Component
@RequiredArgsConstructor
public class TestInfoContributor implements InfoContributor {
  //Метод count() в интерфейсе CrudRepository в Spring Data JPA используется для получения общего количества сущностей в репозитории.
  // Он возвращает количество записей, соответствующих типу сущности, с которой работает репозиторий.

  // /actuator/info

  private final TestRepository testRepository;

  @Override
  public void contribute(Builder builder) {
    Map<String, Object> testMap = new HashMap<String, Object>();
    long count = testRepository.count();
    testMap.put("testRepositoryCount2", count);
    builder.withDetail("test-stats", testMap).withDetail("testRepositoryCount1", count);
  }
}