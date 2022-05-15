package org.bookstore;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class BookStore {
    private Database db;
    private final String DB_NAME = "books_store";
    private final String USER = "root";
    private final String PASSWORD = "ROOT";

    public BookStore() {
        db = new Database(this.DB_NAME, this.USER, this.PASSWORD);
    }

    private String getISBNFromUser(Scanner scan){
        System.out.print("Book ISBN : ");
        String ISBN = scan.nextLine();
        return ISBN;
    }

    private void printBooksList(List<Book> list){
        System.out.println();
        System.out.println("Books : ");
        for (Book book:
             list) {
            System.out.println(book);
        }
        System.out.println();
    }

    public void run(){
        System.out.println("********** Book Store **********");

        boolean StartUP = true;

        while (StartUP){
            System.out.println("Please select one of these choices (write choice number only) : \n");
            System.out.println("1) Add Book\n");
            System.out.println("2) Remove Book\n");
            System.out.println("3) Remove All Books\n");
            System.out.println("4) Update Book\n");
            System.out.println("5) Find a Book\n");
            System.out.println("6) Retrieve All Books\n");
            System.out.println("7) Exit");

            Scanner scan = new Scanner(System.in);
            System.out.println();
            System.out.print("Your Choice : ");
            char choice = scan.nextLine().charAt(0);
            System.out.println();

            switch (choice){
                case '1':
                    Book book = Book.createBookFromUser(scan);
                    db.persistBook(book);
                    break;
                case '2':
                    db.removeBook(getISBNFromUser(scan));
                    break;
                case '3':
                    db.removeAllBooks();
                    break;
                case '4':
                    break;
                case '5':
                    ArrayList<Book> retrievedBook = db.retrieveBook(getISBNFromUser(scan));
                    printBooksList(retrievedBook);
                    break;
                case '6':
                    ArrayList<Book> retrievedBooks = db.retrieveAllBooks();
                    printBooksList(retrievedBooks);
                    break;
                case '7':
                default:
                    StartUP = false;
            }
        }
    }
}
