package com.nbicocchi.exercises.strings;

public class EqualIsNot {
    public static boolean equalIsNot(String string) {
        return countMatches(string, "is") == countMatches(string, "not");
    }

    /**
     * Counts how many times a substring appears in a given string.
     * @param text the given string
     * @param str the substring to be searched
     * @return the number of matches
     * @see "https://www.techiedelight.com/find-occurrences-of-substring-string-java/"
     */
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

    /**
     * Checks if a string is empty ("") or null.
     * @param s the string to be verified
     * @return true if the string is null or empty
     */
    public static boolean isEmpty(String s) {
        return s == null || s.length() == 0;
    }
}
