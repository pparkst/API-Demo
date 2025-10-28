package com.pparkst.api.service;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;

import com.pparkst.api.util.ChunkingData;

@Service
public class BatchProcessingService {
    private final ExecutorService executorService = Executors.newFixedThreadPool(15);
    private final ChunkService chunkService;

    public BatchProcessingService(ChunkService chunkService) {
        this.chunkService = chunkService;
    }

    public <T> void processBulkData(List<T> fullList, JpaRepository<T, ?> repository) throws Exception {
        int chunkSize = 5000;
        List<List<T>> chunks = ChunkingData.chunkData(fullList, chunkSize);

        List<Future<Void>> futures = new ArrayList<>();

        for (List<T> chunk : chunks) {
            Future<Void> future = executorService.submit(() -> {
                chunkService.processChunkBatch(chunk, repository);
                return null;
            });

            futures.add(future);
        }

        for (Future<Void> future : futures) {
            future.get();
        }
    }
}
