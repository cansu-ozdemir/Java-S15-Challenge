package com.library.models;


import java.time.LocalDate;
import java.time.temporal.ChronoUnit;

public class Invoice {
    private double borrowFee;
    private double lateFee;
    private double damageFee;
    private LocalDate borrowDate;
    private LocalDate returnDate;

    public Invoice(double borrowFee, LocalDate borrowDate) {
        this.borrowFee = borrowFee;
        this.borrowDate = borrowDate;
        this.lateFee = 0;
        this.damageFee = 0;
    }

    public double calculateLateFee(LocalDate actualReturnDate, int allowedDays) {
        this.returnDate = actualReturnDate;
        long daysLate = ChronoUnit.DAYS.between(borrowDate.plusDays(allowedDays), actualReturnDate);
        if (daysLate > 0) {
            lateFee = daysLate * 1.0;
        }
        return lateFee;
    }

    public double calculateDamageFee(int damagedPages) {
        damageFee = damagedPages * 2.0;
        return damageFee;
    }

    public double calculateTotalCharge(LocalDate actualReturnDate, int allowedDays, int damagedPages) {
        this.returnDate = actualReturnDate;
        double lateFee = calculateLateFee(actualReturnDate, allowedDays);
        double damageFee = calculateDamageFee(damagedPages);
        return borrowFee + lateFee + damageFee;
    }

    public String generateInvoice(LocalDate actualReturnDate, int allowedDays, int damagedPages) {
        this.returnDate = actualReturnDate;
        double totalCharge = calculateTotalCharge(actualReturnDate, allowedDays, damagedPages);
        return "Borrow Fee: " + borrowFee + " TL\n" +
                "Late Fee: " + lateFee + " TL\n" +
                "Damage Fee: " + damageFee + " TL\n" +
                "Return Date: " + returnDate + "\n" +
                "Total Charge: " + totalCharge + " TL\n";
    }
}
