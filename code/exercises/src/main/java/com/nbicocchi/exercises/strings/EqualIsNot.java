package com.nbicocchi.exercises.strings;

public class EqualIsNot {

    /**
     * Checks if a string is empty ("") or null.
     *
     * @param s the string to be verified
     * @return true if the string is null or empty
     */
    public static boolean isEmpty(String s) {
        return s == null || s.isEmpty();
    }


    /**
     * Returns true if the number of occurrences of "is" equals the number of occurrences of "not"
     * @param string the string to be processed
     * @return true if the number of occurrences of "is" equals the number of occurrences of "not"
     */
    public static boolean equalIsNot(String string) {
        if (isEmpty(string)) {
            return true;
        }

        int indexIs = 0, indexNot = 0;
        while (indexIs != -1 && indexNot != -1) {
            indexIs = string.indexOf("is", indexIs + 1);
            indexNot = string.indexOf("not", indexNot + 1);
        }
        return indexIs == indexNot;
    }
}
