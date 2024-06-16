package com.nbicocchi.exercises.collections;

import java.util.ArrayList;
import java.util.List;

public class LookAndSay {
    public static List<String> lookAndSay(long n){
        if(n < 1) {
            return new ArrayList<>();
        }

        List<String> sequence = new ArrayList<>();
        sequence.add("1");
        for(int i = 1; i < n; i++) {
            String previous = sequence.get(i - 1);
            StringBuilder next = new StringBuilder();
            int count = 1;
            for(int j = 1; j < previous.length(); j++) {
                if(previous.charAt(j) == previous.charAt(j - 1)) {
                    count++;
                } else {
                    next.append(count).append(previous.charAt(j - 1));
                    count = 1;
                }
            }
            next.append(count).append(previous.charAt(previous.length() - 1));
            sequence.add(next.toString());
        }
        return sequence;
    }
}
