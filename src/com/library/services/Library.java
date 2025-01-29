package com.library.services;

import com.library.models.Book;
import com.library.models.BookCategory;
import com.library.models.BorrowRecord;
import com.library.models.FinePolicy;
import com.library.models.Notification;
import com.library.models.Reader;

import java.time.LocalDate;
import java.util.*;

public class Library {
    private List<Book> books;
    private List<Reader> readers;
    private List<BorrowRecord> borrowRecords;
    private Set<String> readerIds;
    private Set<String> bookIds;
    private Map<String, List<BorrowRecord>> borrowRecordMap;
    private Map<String, Book> bookMap;

    public Library() {
        this.books = new ArrayList<>();
        this.readers = new ArrayList<>();
        this.borrowRecords = new ArrayList<>();
        this.readerIds = new HashSet<>();
        this.bookIds = new HashSet<>();
        this.borrowRecordMap = new HashMap<>();
        this.bookMap = new HashMap<>();
    }

    public void addReader(Reader reader) {
        if (readerIds.contains(reader.getId())) {
            System.out.println("Kimliği " + reader.getId() + " olan okuyucu zaten mevcut.");
        } else {
            readers.add(reader);
            readerIds.add(reader.getId());
            System.out.println("Okuyucu başarıyla eklendi.");
        }
    }

    public void addBook(Book book) {
        if (bookIds.contains(book.getId())) {
            System.out.println("ID'si " + book.getId() + " olan kitap zaten mevcut.");
        } else {
            books.add(book);
            bookIds.add(book.getId());
            bookMap.put(book.getId(), book);
            System.out.println("Kitap başarıyla eklendi.");
        }
    }

    public Book findBookById(String id) {
        return bookMap.get(id);
    }

    public List<Book> findBooksByTitle(String title) {
        List<Book> filteredBooks = new ArrayList<>();
        for (Book book : books) {
            if (book.getTitle().equalsIgnoreCase(title)) {
                filteredBooks.add(book);
            }
        }
        return filteredBooks;
    }

    public List<Book> findBooksByAuthor(String author) {
        List<Book> filteredBooks = new ArrayList<>();
        for (Book book : books) {
            if (book.getAuthor().equalsIgnoreCase(author)) {
                filteredBooks.add(book);
            }
        }
        return filteredBooks;
    }

    public void updateBookInfo(String bookId, String newTitle, String newAuthor, BookCategory newCategory) {
        Book book = findBookById(bookId);
        if (book != null) {
            book.setTitle(newTitle);
            book.setAuthor(newAuthor);
            book.setCategory(newCategory);
            System.out.println("Kitap bilgileri başarıyla güncellendi.");
        } else {
            System.out.println("Kitap bulunamadı.");
        }
    }

    public void removeBook(String bookId) {
        Book book = findBookById(bookId);
        if (book != null) {
            books.remove(book);
            bookIds.remove(bookId);
            bookMap.remove(bookId);
            System.out.println("Kitap başarıyla silindi.");
        } else {
            System.out.println("Kitap bulunamadı.");
        }
    }

    public List<Book> listBooksByCategory(BookCategory category) {
        List<Book> filteredBooks = new ArrayList<>();
        for (Book book : books) {
            if (book.getCategory() == category) {
                filteredBooks.add(book);
            }
        }
        return filteredBooks;
    }

    public void borrowBook(Reader reader, Book book, LocalDate borrowDate) {
        if (book.getStatus().equals("AVAILABLE")) {
            reader.borrowBook(book);
            BorrowRecord record = new BorrowRecord(reader.getId(), book.getId(), borrowDate);
            borrowRecords.add(record);
            borrowRecordMap.computeIfAbsent(reader.getId(), k -> new ArrayList<>()).add(record);
            book.updateStatus("BORROWED");
            bookMap.put(book.getId(), book);
            System.out.println("Kitap başarıyla ödünç alındı.");
        } else {
            System.out.println("Bu kitap mevcut değil.");
        }
    }
    public void returnBook(Reader reader, Book book, LocalDate returnDate, int damagedPages) {
        if(!reader.getBorrowedBooks().contains(book)) {
            System.out.println("Kitap bu okuyucu tarafından ödünç alınmadı.");
            return;
        }
        reader.returnBook(book);

        List<BorrowRecord> records = borrowRecordMap.get(reader.getId());
        if (records == null) {
            System.out.println("Kullanıcıya ait ödünç kitap kaydı bulunamadı.");
            return;
        }

        BorrowRecord record = records.stream().filter(r -> r.getBookId().equals(book.getId())).findFirst().orElse(null);

        if (record != null) {
            record.setReturnDate(returnDate);
        }

        int allowedDays = 30;
        long daysLate = returnDate.toEpochDay() - record.getBorrowDate().plusDays(allowedDays).toEpochDay();
        double lateFee = FinePolicy.calculateLateFee((int) daysLate);
        double damageFee = FinePolicy.calculateDamageFee(damagedPages);
        double totalCharge = 10.0 + lateFee + damageFee;

        if (totalCharge > 10) {
            System.out.println("Ek Ödeme Gerekli: " + (totalCharge - 10.0) + " TL");
        } else {
            System.out.println("Geri Ödeme Tutarı: " + (10.0 - totalCharge) + " TL");
        }

        Notification notification = new Notification("Kitap İade Edildi: " + book.getTitle() + ", Toplam Ücret: " + totalCharge + " TL", LocalDate.now());
        System.out.println(notification);

        book.updateStatus("AVAILABLE");
        bookMap.put(book.getId(), book);

        System.out.println("Kitap İade Süreci Tamamlandı.");
    }
}
