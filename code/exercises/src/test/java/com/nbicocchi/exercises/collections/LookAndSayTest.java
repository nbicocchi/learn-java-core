package com.nbicocchi.exercises.collections;

import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class LookAndSayTest {
    @Test
    void lookAndSay() {
        assertIterableEquals(List.of(), LookAndSay.lookAndSay(0));
        assertIterableEquals(List.of(1L), LookAndSay.lookAndSay(1));
        assertIterableEquals(List.of(1L, 11L), LookAndSay.lookAndSay(2));
        assertIterableEquals(List.of(1L, 11L, 21L), LookAndSay.lookAndSay(3));
        assertIterableEquals(List.of(1L, 11L, 21L, 1211L), LookAndSay.lookAndSay(4));
        assertIterableEquals(List.of(1L, 11L, 21L, 1211L, 111221L), LookAndSay.lookAndSay(5));
        assertIterableEquals(List.of(1L, 11L, 21L, 1211L, 111221L, 312211L, 13112221L, 1113213211L, 31131211131221L), LookAndSay.lookAndSay(9));
        assertIterableEquals(List.of(), LookAndSay.lookAndSay(-1));
    }
}