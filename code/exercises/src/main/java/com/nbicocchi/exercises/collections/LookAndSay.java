package com.nbicocchi.exercises.collections;

import java.util.ArrayList;
import java.util.List;

public class LookAndSay {
    public static List<Long> lookAndSay(long n){
        if(n < 1) {
            return new ArrayList<>();
        }

        List<Long> sequence = new ArrayList<>();
        sequence.add(1L);
        for(int i = 1; i < n; i++) {
            String previous = sequence.get(i - 1).toString();
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
            sequence.add(Long.parseLong(next.toString()));
        }
        return sequence;
    }
}
