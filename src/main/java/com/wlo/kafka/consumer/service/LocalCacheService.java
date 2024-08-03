package com.wlo.kafka.consumer.service;

import com.wlo.kafka.consumer.dao.EventParamsRepository;
import com.wlo.kafka.consumer.model.EventParams;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import jakarta.annotation.PostConstruct;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

@Service
public class LocalCacheService {

    private final Map<Long, EventParams> cache = new ConcurrentHashMap<>();

    private final EventParamsRepository repository;

    public LocalCacheService(EventParamsRepository repository) {
        this.repository = repository;
    }

    @PostConstruct
    public void init() {
        loadCache();
    }

    private void loadCache() {
        List<EventParams> entities = repository.findAll();
        entities.forEach(entity -> cache.put(entity.getId(), entity));
    }

    @Scheduled(cron = "0 0 * * * ?")
    public void refreshCache() {
        loadCache();
    }

    public List<EventParams> getAllCache() {
        return cache.values().stream().toList();
    }

}
