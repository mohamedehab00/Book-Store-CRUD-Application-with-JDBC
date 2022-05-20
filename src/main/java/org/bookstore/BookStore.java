package org.bookstore;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class BookStore {
    private final Database db;
    private final String DB_NAME = "books_store";
    private final String USER = "root";
    private final String PASSWORD = "ROOT";

    private enum BOOK_VALUES{
        TITLE,PAGES_NO,PUBLISHER,PUBLISH_YEAR,NULL;
    }

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
                    BOOK_VALUES Choice = getValuesChoice(scan);
                    String ISBN = getISBNFromUser(scan);
                    String newValue = getNewValueFromUser(scan);
                    switch (Choice){
                        case TITLE :
                            db.updateBookTitle(ISBN, newValue);
                            break;
                        case PAGES_NO :
                            db.updateBookPageNum(ISBN, newValue);
                            break;
                        case PUBLISHER :
                            db.updateBookPublisher(ISBN, newValue);
                            break;
                        case PUBLISH_YEAR :
                            db.updateBookPublishYear(ISBN, newValue);
                            break;
                        case NULL:
                        default :
                            System.out.println("Invalid Input");
                    }
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

    private BOOK_VALUES getValuesChoice(Scanner scan) {
        System.out.println("Choose the field you want to update its value : ");
        System.out.println("1) Book Title" +
                " 2) Book Page No." +
                " 3) Book Publisher" +
                " 4) Book Publish Year");
        System.out.print("Choice : ");
        int choice;
        try {
            choice = scan.nextInt();
            scan.nextLine();
        }
        catch (Exception e){
            choice = 0;
        }

        return switch (choice){
            case 1 -> BOOK_VALUES.TITLE;
            case 2 -> BOOK_VALUES.PAGES_NO;
            case 3 -> BOOK_VALUES.PUBLISHER;
            case 4 -> BOOK_VALUES.PUBLISH_YEAR;
            default -> BOOK_VALUES.NULL;
        };
    }

    private String getNewValueFromUser(Scanner scan) {
        System.out.print("Value : ");
        String value = scan.nextLine();
        return value;
    }
}
