package com.nbicocchi.exercises.collections;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.LinkedList;

import static org.junit.jupiter.api.Assertions.*;

class SpeedTestTest {
    final int times = 10000;

    @Test
    void testInsertBeginning() {
        long arrayList = SpeedTest.insertBeginning(new ArrayList<>(), "nicola", times);
        long linkedList = SpeedTest.insertBeginning(new LinkedList<>(), "nicola", times);
        assertTrue(linkedList < arrayList);
    }

    @Test
    void testInsertEnd() {
        long arrayList = SpeedTest.insertEnd(new ArrayList<>(), "nicola", times);
        long linkedList = SpeedTest.insertEnd(new LinkedList<>(), "nicola", times);
        assertTrue(linkedList > arrayList);
    }
}