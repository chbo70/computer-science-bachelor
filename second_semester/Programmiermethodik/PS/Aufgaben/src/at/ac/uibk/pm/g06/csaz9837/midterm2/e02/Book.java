package at.ac.uibk.pm.g06.csaz9837.midterm2.e02;

import java.time.LocalDate;

public class Book {
    private int ISBN;
    private String title;
    private Author author;
    private int publishingYear;

    public Book(int ISBN, String title, Author author, int publishingYear) {
        this.ISBN = ISBN;
        this.title = title;
        this.author = author;
        this.publishingYear = publishingYear;
    }

    public int getISBN() {
        return ISBN;
    }

    public String getTitle() {
        return title;
    }

    public Author getAuthor() {
        return author;
    }

    public int getPublishingYear() {
        return publishingYear;
    }

    @Override
    public String toString() {
        return "\nISBN = " + ISBN +
                ",\nTitle = '" + title + '\'' +
                ",\nAuthor = " + author +
                ",\nPublishingYear = " + publishingYear +
                "\n";
    }
}
