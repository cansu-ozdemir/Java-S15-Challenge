package com.library.models;

public class Book {
    private String id;
    private String title;
    private String author;
    private String status;
    private BookCategory category;

    public Book(String id, String title, String author, BookCategory category) {
        this.id = id;
        this.title = title;
        this.author = author;
        this.status = "AVAILABLE";
        this.category = category;
    }

    public String getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public String getAuthor() {
        return author;
    }

    public String getStatus() {
        return status;
    }

    public void updateStatus(String status) {
        this.status = status;
    }

    public BookCategory getCategory() {
        return category;
    }

    public String getDetails() {
        return "ID: " + id + ", Title: " + title + ", Author: " + author + ", Status: " + status + ", Category: " + category;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public void setCategory(BookCategory category) {
        this.category = category;
    }
}
