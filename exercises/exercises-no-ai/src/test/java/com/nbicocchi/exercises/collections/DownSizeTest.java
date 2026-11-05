package com.nbicocchi.exercises.collections;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class DownSizeTest {
    List<String> list;

    @Test
    void downsize() {
        list = new ArrayList<>(List.of("a", "b", "c", "d", "e", "f"));
        DownSize.downsize(list, 2);
        assertEquals(List.of("a", "c", "e"), list);
        list = new ArrayList<>(List.of("a", "b", "c", "d", "e", "f"));
        DownSize.downsize(list, 3);
        assertEquals(List.of("a", "b", "d", "e"), list);
        list = new ArrayList<>(List.of("a", "b", "c", "d", "e", "f"));
        DownSize.downsize(list, 1);
        assertEquals(List.of(), list);
    }
}