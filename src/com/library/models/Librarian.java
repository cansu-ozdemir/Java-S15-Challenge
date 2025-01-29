package com.library.models;

import com.library.services.Library;

public class Librarian extends Person{
    private Library library;

    public Librarian(String name, String id, Library library) {
        super(name, id);
        this.library = library;
    }

    public void addBook(Book book) {
        library.getBookMap().put(book.getId(), book);
        library.getBookIds().add(book.getId());
        System.out.println(getName() + "adlı kütüphaneci kitap ekledi: " + book.getTitle());
    }

    public void removeBook(String bookId) {
        Book book = library.findBookById(bookId);
        if (book != null) {
            library.getBookMap().remove(bookId);
            library.getBookIds().remove(bookId);
            System.out.println(getName() + "adlı kütüphaneci kitabı kaldırdı: " + book.getTitle());
        } else {
            System.out.println("Kitap bulunamadı.");
        }
    }

    public void updateBook(String bookId, String newTitle, String newAuthor, BookCategory newCategory) {
        Book book = library.findBookById(bookId);
        if (book != null) {
            book.setTitle(newTitle);
            book.setAuthor(newAuthor);
            book.setCategory(newCategory);
            System.out.println("Kütüphaneci kitabı güncelledi: " + newTitle);
        } else {
            System.out.println("Kitap bulunamadı.");
        }
    }
}
