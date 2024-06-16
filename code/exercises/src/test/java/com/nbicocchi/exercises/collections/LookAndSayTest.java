package com.nbicocchi.exercises.collections;

import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class LookAndSayTest {
    @Test
    void lookAndSay() {
        assertIterableEquals(List.of("1"), LookAndSay.lookAndSay(1));
        assertIterableEquals(List.of("1", "11"), LookAndSay.lookAndSay(2));
        assertIterableEquals(List.of("1", "11", "21"), LookAndSay.lookAndSay(3));
        assertIterableEquals(List.of("1", "11", "21", "1211"), LookAndSay.lookAndSay(4));
        assertIterableEquals(List.of("1", "11", "21", "1211", "111221"), LookAndSay.lookAndSay(5));
        assertIterableEquals(List.of("1", "11", "21", "1211", "111221", "312211", "13112221", "1113213211", "31131211131221"), LookAndSay.lookAndSay(9));

    }
}
