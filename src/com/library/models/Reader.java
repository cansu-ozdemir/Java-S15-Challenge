package com.library.models;

import java.util.ArrayList;
import java.util.List;

public class Reader extends Person {
    private List<Book> borrowedBooks;

    public Reader(String name, String id) {
        super(name, id);
        this.borrowedBooks = new ArrayList<>();
    }

    public List<Book> getBorrowedBooks() {
        return borrowedBooks;
    }

    public void borrowBook(Book book) {
        if (borrowedBooks.size() < 5) {
            borrowedBooks.add(book);
            book.updateStatus("BORROWED");
            System.out.println("Ödünç Alınan Kitap: " + book.getTitle());
        } else {
            System.out.println("Ödünç alma limitine ulaştınız (5 Kitap).");
        }
    }

    public void returnBook(Book book) {
        if (borrowedBooks.contains(book)) {
            borrowedBooks.remove(book);
            book.updateStatus("AVAILABLE");
            System.out.println("Kitap İade Edildi: " + book.getTitle());
        } else {
            System.out.println("Bu kitap tarafınızdan ödünç alınmadı.");
        }
    }
}
