package com.nbicocchi.exercises.strings;

public class EqualIsNot {
    // Alternative methods here
    // https://www.techiedelight.com/find-occurrences-of-substring-string-java/

    public static boolean equalIsNot(String string) {
        int is = countMatches(string, "is");
        int not = countMatches(string, "not");
        return is == not;
    }

    /* Counts how many times the substring appears in the larger string. */
    public static int countMatches(String text, String str) {
        if (isEmpty(text) || isEmpty(str)) {
            return 0;
        }
        int index = 0;
        int count = 0;
        while (true) {
            index = text.indexOf(str, index);
            if (index != -1) {
                count++;
                index += str.length();
            } else {
                break;
            }
        }
        return count;
    }

    /* Checks if a string is empty ("") or null. */
    public static boolean isEmpty(String s) {
        return s == null || s.length() == 0;
    }
}
