package com.pparkst.api.service;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import jakarta.persistence.EntityManager;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class ChunkService {
    private final EntityManager entityManager;

    @Transactional
    public <T> void processChunkBatch(List<T> chunk, JpaRepository<T, ?> repository) {
        for (int i = 0; i < chunk.size(); i++) {
            repository.save(chunk.get(i));

            entityManager.flush();
            entityManager.clear();
        }
    }
}
