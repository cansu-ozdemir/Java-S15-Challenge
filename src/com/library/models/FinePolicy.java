package com.library.models;

public class FinePolicy {
    private static final double LATE_FEE_PER_DAY = 1.0;
    private static final double DAMAGE_FEE_PER_PAGE = 2.0;

    public static double calculateLateFee(int daysLate) {
        return daysLate > 0 ? daysLate * LATE_FEE_PER_DAY : 0;
    }

    public static double calculateDamageFee(int damagedPages) {
        return damagedPages > 0 ? damagedPages * DAMAGE_FEE_PER_PAGE : 0;
    }
}
