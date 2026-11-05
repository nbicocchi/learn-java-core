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

        int indexIs = string.indexOf("is");
        int indexNot = string.indexOf("not");
        while (indexIs != -1 && indexNot != -1) {
            indexIs = string.indexOf("is", indexIs + 2);
            indexNot = string.indexOf("not", indexNot + 3);
        }
        return indexIs == indexNot;
    }

}
