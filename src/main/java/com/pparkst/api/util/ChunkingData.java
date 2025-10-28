package com.pparkst.api.util;

import java.util.ArrayList;
import java.util.List;

public class ChunkingData {

    public static <T> List<List<T>> chunkData(List<T> fullList, int chunkSize) {
        if(fullList == null || fullList.isEmpty() || chunkSize <= 0) {
            return new ArrayList<>();
        }

        List<List<T>> chunks = new ArrayList<>();

        for(int i = 0; i < fullList.size(); i += chunkSize) {
            int end = Math.min(fullList.size(), i + chunkSize);
            chunks.add(fullList.subList(i, end));
        }

        return chunks;
    }
    
}
