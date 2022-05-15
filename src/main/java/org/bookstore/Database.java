package org.bookstore;

import java.sql.*;
import java.util.ArrayList;

public class Database {
    private static String jdbcUrl = "jdbc:mysql://localhost:3306/";
    private final String dbName;
    private final String user;
    private final String password;

    private Connection connection;

    public Database(String dbName){
        this.dbName = dbName;
        jdbcUrl += this.dbName;
        this.user = "root";
        this.password = "root";
    }

    public Database(String dbName, String user, String password){
        this.dbName = dbName;
        jdbcUrl += this.dbName;
        this.user = user;
        this.password = password;
    }

    private void connectToDB(){
        try {
            this.connection = DriverManager.getConnection(jdbcUrl,this.user,this.password);

            if (this.connection != null){
                System.out.println("Connected to DB!!");
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
    private void endConnectionToDB() {
        try {

            this.connection.close();

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    private String insertBookQuery(Book book){
        String query = "INSERT INTO books " +
                "(book_title,book_totPages,book_publisher,book_ISBN,book_publish_year) " +
                "VALUES('%s',%d,'%s','%s','%s')";

        query = String.format(query,
                book.getBook_title(),
                book.getBook_totPages(),
                book.getBook_publisher(),
                book.getBook_ISBN(),
                book.getBook_publish_year());

        return query;
    }

    private void executeInsertBookQuery(Book book) throws SQLException{
        Statement st = this.connection.createStatement();

        int rows = st.executeUpdate(insertBookQuery(book));

        if (rows > 0)
            System.out.println("Book Inserted into DB!!!");
    }

    public void persistBook(Book book){
        try {
            connectToDB();

            executeInsertBookQuery(book);

            endConnectionToDB();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private String findBookQuery(String ISBN) {
        String query = "SELECT * FROM books WHERE book_ISBN = '%s'";
        return String.format(query, ISBN);
    }

    private String getAllBooksQuery() {
        String query = "SELECT * FROM books";
        return query;
    }

    private ResultSet executeFindBookQuery(String ISBN) throws SQLException{
        Statement st = this.connection.createStatement();

        ResultSet res = st.executeQuery(findBookQuery(ISBN));

        return res;
    }

    private ResultSet executeGetAllBooksQuery() throws SQLException{
        Statement st = this.connection.createStatement();

        ResultSet res = st.executeQuery(getAllBooksQuery());

        return res;
    }

    public ArrayList<Book> retrieveBook(String ISBN){
        ArrayList<Book> bookList = new ArrayList<>();
        try {
            connectToDB();

            ResultSet res = executeFindBookQuery(ISBN);

            while(res.next()){
                Book book = new Book(
                        res.getString("book_title"),
                        res.getString("book_publisher"),
                        res.getString("book_ISBN"),
                        res.getString("book_publish_year"),
                        res.getInt("book_totPages")
                );

                bookList.add(book);
            }

            endConnectionToDB();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return bookList;
    }

    public ArrayList<Book> retrieveAllBooks(){
        ArrayList<Book> bookList = new ArrayList<>();
        try {
            connectToDB();

            ResultSet res = executeGetAllBooksQuery();

            while(res.next()){
                Book book = new Book(
                        res.getString("book_title"),
                        res.getString("book_publisher"),
                        res.getString("book_ISBN"),
                        res.getString("book_publish_year"),
                        res.getInt("book_totPages")
                );

                bookList.add(book);
            }

            endConnectionToDB();

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return bookList;
    }

    private String removeBookQuery(String ISBN) {
        String query = "DELETE FROM books WHERE book_ISBN = '%s'";
        return String.format(query, ISBN);
    }

    private String removeAllBooksQuery() {
        String query = "DELETE FROM books";
        return query;
    }

    private void executeRemoveBookQuery(String ISBN) throws SQLException{
        Statement st = this.connection.createStatement();

        int rows = st.executeUpdate(removeBookQuery(ISBN));

        if (rows > 0)
            System.out.println("Book Removed from DB!!!");
    }

    private void executeRemoveAllBooksQuery() throws SQLException{
        Statement st = this.connection.createStatement();

        int rows = st.executeUpdate(removeAllBooksQuery());

        if (rows > 0)
            System.out.println("All Books Removed from DB!!!");
    }

    public void removeBook(String ISBN){
        try {
            connectToDB();

            executeRemoveBookQuery(ISBN);

            endConnectionToDB();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void removeAllBooks(){
        try {
            connectToDB();

            executeRemoveAllBooksQuery();

            endConnectionToDB();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
