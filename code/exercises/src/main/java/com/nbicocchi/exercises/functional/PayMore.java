package com.nbicocchi.exercises.functional;

import java.util.Map;

public class PayMore {
    public static void payMore(Map<String, Double> fees, String numberFamily, double costDelta) {
        fees.replaceAll((number, fee) -> number.startsWith(numberFamily) ? fee + costDelta : fee);
    }
}
