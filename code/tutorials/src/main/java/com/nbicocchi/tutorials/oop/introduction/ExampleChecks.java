package com.nbicocchi.tutorials.oop.introduction;

import java.util.Arrays;

/** Small executable checks; no -ea flag or testing library is needed. */
public class ExampleChecks {
    public static void main(String[] args) {
        Point point = new Point(3, 4);
        check(point.distance(new Point(0, 0)) == 5.0, "Point distance");
        check(point.distance(0, 0) == 5.0, "Overloaded distance");
        check(new ColoredPoint(3, 4, "red").distance(0, 0) == 5.0,
                "Inherited distance");
        check(new Vector(0).search(10) == -1, "Empty vector");
        Vector vector = new Vector(100);
        check(vector.search(-1) == -1, "Absent value before sorting");
        vector.sort();
        check(vector.search(-1) == -1, "Absent value after sorting");
        try {
            new Vector(-1);
            throw new AssertionError("Negative capacity must be rejected");
        } catch (IllegalArgumentException expected) {
            // Expected constructor behavior.
        }

        // Fixed arrays make the proposed algorithm change reproducible.
        // Do not expose Vector's internal array merely to test this proposal.
        int[] unsorted = {8, 2, 5};
        int[] sorted = {2, 5, 8};
        int[] duplicates = {2, 2, 2};
        check(linearSearch(unsorted, 8) == 0, "Search without sorting");
        check(linearSearch(sorted, 9) == -1, "Missing-value convention");
        check(Arrays.binarySearch(sorted, 9) == -4, "Insertion-point encoding");
        check(linearSearch(duplicates, 2) == 0, "First matching index");

        System.out.println("binarySearch on unsorted input (no valid precondition): "
                + Arrays.binarySearch(unsorted, 8));
        System.out.println("binarySearch duplicate index (first is not guaranteed): "
                + Arrays.binarySearch(duplicates, 2));
        System.out.println("All checks passed.");
    }

    private static int linearSearch(int[] values, int value) {
        for (int i = 0; i < values.length; i++) {
            if (values[i] == value) {
                return i;
            }
        }
        return -1;
    }

    private static void check(boolean condition, String description) {
        if (!condition) {
            throw new AssertionError(description);
        }
    }
}
