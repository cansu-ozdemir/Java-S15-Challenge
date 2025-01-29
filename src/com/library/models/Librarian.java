package com.library.models;

public class Librarian extends Person{
    public Librarian(String name, String id) {
        super(name, id);
    }

    public void addBook() {
        System.out.println("Kitap Ekleme");
    }

    public void removeBook() {
        System.out.println("Kitabı Kaldırma");
    }

    public void updateBook() {
        System.out.println("Kitap Güncelleme");
    }
}
