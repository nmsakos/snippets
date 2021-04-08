package com.akos.collections;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.stream.IntStream;

import org.apache.commons.lang3.time.StopWatch;

public class HashMapVsLinkedHashMapTest {

    public static void main(String[] args) {
        new HashMapVsLinkedHashMapTest().run();
    }

    private void run() {
        final int loadSize = 100000;

        Map<Integer, Integer> hashMap = new HashMap<>(loadSize);
        runLoad(hashMap, loadSize);
        Map<Integer, Integer> linkedHashMap = new LinkedHashMap<>(loadSize);
        runLoad(linkedHashMap, loadSize);
    }

    private void runLoad(Map<Integer, Integer> map, int loadSize) {
        StopWatch stopWatch = new StopWatch();
        stopWatch.start();
        IntStream.rangeClosed(1, loadSize)
//            .parallel()
            .boxed()
            .forEach(item -> map.put(item, item));

        System.out.println(map.getClass().getCanonicalName()+" -> " + stopWatch.formatTime());
        stopWatch.stop();
        stopWatch.reset();
    }
}
