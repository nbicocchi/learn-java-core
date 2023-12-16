package com.nbicocchi.exercises.exceptions;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;

public class FilterItems {
    public static List<String> filterItems(List<String> src) {
        List<String> dst = new ArrayList<>();
        for (String s : src) {
            try {
                CheckString.checkString(s);
                dst.add(s);
            } catch (ParseException ignored) {
            }
        }
        return dst;
    }
}
