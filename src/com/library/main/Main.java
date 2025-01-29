package com.library.main;

import com.library.models.Book;
import com.library.models.BookCategory;
import com.library.models.Reader;
import com.library.services.Library;

import java.time.LocalDate;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Library library = new Library();
        Scanner scanner = new Scanner(System.in);

        System.out.println("Kütüphaneye Hoşgeldiniz");

        while (true) {
            System.out.println("\nMenü:");
            System.out.println("1. Kitap Ekle");
            System.out.println("2. Okuyucu Ekle");
            System.out.println("3. ID'ye Göre Kitap Arama");
            System.out.println("4. Başlığa Göre Kitap Arama");
            System.out.println("5. Kitapları Kategoriye Göre Listele");
            System.out.println("6. Kitap Ödünç Al");
            System.out.println("7. Kitabı İade Et");
            System.out.println("8. Yazar Adına Göre Kitap Arama");
            System.out.println("9. Kitap Bilgilerini Güncelle");
            System.out.println("10. Kitap Sil");
            System.out.println("11. Çıkış");
            System.out.println("Bir Seçenek Seçin: ");

            int choice = scanner.nextInt();
            scanner.nextLine();

            switch (choice) {
                case 1:
                    System.out.println("Kitap ID'sini Giriniz: ");
                    String id = scanner.nextLine();
                    System.out.println("Kitap Başlığı Giriniz: ");
                    String title = scanner.nextLine();
                    System.out.println("Kitap Yazarı Giriniz: ");
                    String author = scanner.nextLine();
                    System.out.println("Bir Kategori Seçin: FICTION, PSYCHOLOGY, HISTORY, POETRY, PHILOSOPHY, ART");
                    String categoryInput = scanner.nextLine().toUpperCase();

                    try {
                        BookCategory category = BookCategory.valueOf(categoryInput);
                        library.addBook(new Book(id, title, author, category));
                        System.out.println("Kitap Başarıyla Eklendi!");
                    } catch (IllegalArgumentException e) {
                        System.out.println("Geçersiz Kategori. Lütfen Tekrar Deneyin!");
                    }
                    break;

                case 2:
                    System.out.println("Okuyucu ID'sini Giriniz: ");
                    String readerId2 = scanner.nextLine();
                    System.out.println("Okuyucu Adını Giriniz: ");
                    String readerName = scanner.nextLine();

                    Reader newReader = new Reader(readerName, readerId2);
                    library.addReader(newReader);
                    break;

                case 3:
                    System.out.println("Aramak İçin Kitap ID'sini Giriniz: ");
                    String searchId = scanner.nextLine();
                    Book foundBook = library.findBookById(searchId);
                    if (foundBook != null) {
                        System.out.println("Kitap Bulundu: " + foundBook.getDetails());
                    } else {
                        System.out.println("Kitap Bulunamadı!");
                    }
                    break;

                case 4:
                    System.out.println("Aramak İstediğiniz Kitap Başlığını Giriniz: ");
                    String searchTitle = scanner.nextLine();
                    library.findBooksByTitle(searchTitle).forEach(book -> System.out.println(book.getDetails()));
                    break;

                case 5:
                    System.out.println("Listelenecek Bir Kategori Seçin: FICTION, PSYCHOLOGY, HISTORY, POETRY, PHILOSOPHY, ART");
                    String listCategoryInput = scanner.nextLine().toUpperCase();

                    try {
                        BookCategory listCategory = BookCategory.valueOf(listCategoryInput);
                        library.listBooksByCategory(listCategory).forEach(book -> System.out.println(book.getDetails()));
                    } catch (IllegalArgumentException e) {
                        System.out.println("Geçersiz Kategori. Lütfen Tekrar Deneyin!");
                    }
                    break;

                case 6:
                    System.out.println("Okuyucu ID'nizi Giriniz: ");
                    String readerId6 = scanner.nextLine();
                    System.out.println("Ödünç Alınacak Kitap ID'sini Giriniz: ");
                    String borrowBookId = scanner.nextLine();
                    System.out.println("Ödünç Alma Tarihini Giriniz (YYYY-MM-DD): ");
                    String borrowDateInput = scanner.nextLine();

                    LocalDate borrowDate = LocalDate.parse(borrowDateInput);
                    Reader readerForBorrow = new Reader("Test Reader", readerId6);
                    Book bookToBorrow = library.findBookById(borrowBookId);

                    if (bookToBorrow != null) {
                        library.borrowBook(readerForBorrow, bookToBorrow, borrowDate);
                    } else {
                        System.out.println("Kitap Bulunamadı.");
                    }
                    break;

                case 7:
                    System.out.println("Okuyucu Id'nizi Giriniz: ");
                    String returnReaderId = scanner.nextLine();
                    System.out.println("İade Etmek İçin Kitap Id'sini Giriniz: ");
                    String returnBookId = scanner.nextLine();
                    System.out.println("İade Tarihini Giriniz (YYYY-MM-DD: ");
                    String returnDateInput = scanner.nextLine();
                    System.out.println("Hasarlı Sayfa Sayısını Giriniz: ");
                    while (!scanner.hasNextInt()) {
                        System.out.println("Geçersiz giriş. Lütfen pozitif bir değer giriniz.");
                        scanner.next();
                    }
                    int damagedPages = scanner.nextInt();
                    scanner.nextLine();

                    LocalDate returnDate = LocalDate.parse(returnDateInput);
                    Reader readerForReturn = new Reader("Test Reader", returnReaderId);
                    Book bookToReturn = library.findBookById(returnBookId);

                    if (bookToReturn != null) {
                        library.returnBook(readerForReturn, bookToReturn, returnDate, damagedPages);
                    } else {
                        System.out.println("Kitap Bulunamadı!");
                    }
                    break;

                case 8:
                    System.out.println("Yazar Adını Giriniz: ");
                    String searchAuthor = scanner.nextLine();
                    library.findBooksByAuthor(searchAuthor).forEach(book -> System.out.println(book.getDetails()));
                    break;

                case 9:
                    System.out.println("Güncellenecek Kitap ID'sini Giriniz: ");
                    String updateId = scanner.nextLine();
                    System.out.println("Yeni Başlık Giriniz: ");
                    String newTitle = scanner.nextLine();
                    System.out.println("Yeni Yazar Giriniz: ");
                    String newAuthor = scanner.nextLine();
                    System.out.println("Yeni Kategori Giriniz: FICTION, PSYCHOLOGY, HISTORY, POETRY, PHILOSOPHY, ART");
                    String newCategoryInput = scanner.nextLine().toUpperCase();

                    try {
                        BookCategory newCategory = BookCategory.valueOf(newCategoryInput);
                        library.updateBookInfo(updateId, newTitle, newAuthor, newCategory);
                    } catch (IllegalArgumentException e) {
                        System.out.println("Geçersiz Kategori. Lütfen Tekrar Deneyin!");
                    }
                    break;


                case 10:
                    System.out.println("Silmek İstediğiniz Kitap ID'sini Giriniz: ");
                    String removeId = scanner.nextLine();
                    library.removeBook(removeId);
                    break;

                case 11:
                    System.out.println("Sistemden Çıkılıyor!");
                    scanner.close();
                    return;

                default:
                    System.out.println("Geçersiz Seçenek. Lütfen Tekrar Deneyin!");
            }
        }
    }
}