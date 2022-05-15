package org.bookstore;

import java.util.Scanner;
import java.util.function.BinaryOperator;

public class Book {
    String book_title,book_publisher,book_ISBN,book_publish_year;
    int book_totPages;

    public Book(String book_title, String book_publisher, String book_ISBN, String book_publish_year, int book_totPages) {
        this.book_title = book_title;
        this.book_publisher = book_publisher;
        this.book_ISBN = book_ISBN;
        this.book_publish_year = book_publish_year;
        this.book_totPages = book_totPages;
    }

    public String getBook_title() {
        return book_title;
    }

    public void setBook_title(String book_title) {
        this.book_title = book_title;
    }

    public String getBook_publisher() {
        return book_publisher;
    }

    public void setBook_publisher(String book_publisher) {
        this.book_publisher = book_publisher;
    }

    public String getBook_ISBN() {
        return book_ISBN;
    }

    public void setBook_ISBN(String book_ISBN) {
        this.book_ISBN = book_ISBN;
    }

    public String getBook_publish_year() {
        return book_publish_year;
    }

    public void setBook_publish_year(String book_publish_year) {
        this.book_publish_year = book_publish_year;
    }

    public int getBook_totPages() {
        return book_totPages;
    }

    public void setBook_totPages(int book_totPages) {
        this.book_totPages = book_totPages;
    }

    public static Book createBookFromUser(Scanner scan){
        System.out.println("Book Information : ");
        System.out.println();
        System.out.print("Book Title : ");
        String bookTitle = scan.nextLine();
        System.out.print("Book Publisher : ");
        String bookPublisher = scan.nextLine();
        System.out.print("Book ISBN : ");
        String bookISBN = scan.nextLine();
        System.out.print("Book Publish Year : ");
        String bookPublishYear = scan.nextLine();
        System.out.print("Book Pages No. : ");
        int bookPages = scan.nextInt();
        scan.nextLine();
        return new Book(bookTitle,bookPublisher,bookISBN,bookPublishYear,bookPages);
    }

    @Override
    public String toString() {
        return "Book{" +
                "book_title='" + book_title + '\'' +
                ", book_publisher='" + book_publisher + '\'' +
                ", book_ISBN='" + book_ISBN + '\'' +
                ", book_publish_year='" + book_publish_year + '\'' +
                ", book_totPages=" + book_totPages +
                '}';
    }
}
