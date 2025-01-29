package com.library.models;

import java.time.LocalDate;

public class BorrowRecord {
    private String readerId;
    private String bookId;
    private LocalDate borrowDate;
    private LocalDate returnDate;

    public BorrowRecord(String readerId, String bookId, LocalDate borrowDate) {
        this.readerId = readerId;
        this.bookId = bookId;
        this.borrowDate = borrowDate;
        this.returnDate = null;
    }

    public String getReaderId() {
        return readerId;
    }

    public String getBookId() {
        return bookId;
    }

    public LocalDate getBorrowDate() {
        return borrowDate;
    }

    public LocalDate getReturnDate() {
        return returnDate;
    }

    public void setReturnDate(LocalDate returnDate) {
        this.returnDate = returnDate;
    }
}
