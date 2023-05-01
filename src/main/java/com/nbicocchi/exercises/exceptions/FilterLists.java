package com.nbicocchi.exercises.exceptions;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;

public class FilterLists {
    public static List<List<String>> filterLists(List<List<String>> src) {
        List<List<String>> dst = new ArrayList<>();
        for (List<String> l : src) {
            try {
                for (String s : l) {
                    CheckString.checkString(s);
                }
                dst.add(l);
            } catch (ParseException ignored) {
            }
        }
        return dst;
    }
}
