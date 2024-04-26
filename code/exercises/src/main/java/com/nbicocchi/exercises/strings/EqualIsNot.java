package com.nbicocchi.exercises.strings;

public class EqualIsNot {

    /**
     * Returns true if the number of occurrences of "is" equals the number of occurrences of "not"
     * @param string the string to be processed
     * @return true if the number of occurrences of "is" equals the number of occurrences of "not"
     */
    public static boolean equalIsNot(String string) {
        if (string == null || string.isEmpty()) {
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
