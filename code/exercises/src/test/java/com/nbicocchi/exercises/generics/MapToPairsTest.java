package com.nbicocchi.exercises.generics;

import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;

class MapToPairsTest {
    @Test
    void mapToPairs() {
        Map<Integer, String> map = Map.of(0, "nicola", 1, "darma", 2, "agata");
        List<Pair<Integer, String>> list = MapToPairs.mapToPairs(map);
        for (Pair<Integer, String> pair : list) {
            switch (pair.getFirst()) {
                case 0 -> assertEquals("nicola", pair.getSecond());
                case 1 -> assertEquals("darma", pair.getSecond());
                case 2 -> assertEquals("agata", pair.getSecond());
            }
        }
    }
}