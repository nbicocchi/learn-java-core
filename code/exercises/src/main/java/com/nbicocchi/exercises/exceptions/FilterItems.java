package com.nbicocchi.exercises.exceptions;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;

public class FilterItems {
    public static List<String> filterItems(List<String> strings) {
        List<String> dst = new ArrayList<>();
        for (String s : strings) {
            try {
                CheckString.checkString(s);
                dst.add(s);
            } catch (ParseException ignored) {
            }
        }
        return dst;
    }
}
