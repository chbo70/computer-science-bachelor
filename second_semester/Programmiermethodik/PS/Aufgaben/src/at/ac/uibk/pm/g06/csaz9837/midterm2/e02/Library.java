package at.ac.uibk.pm.g06.csaz9837.midterm2.e02;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.IntStream;

public class Library {
    private final List<Book> books;

    public Library() {
        this.books = new ArrayList<>();
    }

    public void addBooks(int numberOfBooks, Book bookToAdd) {
        IntStream.range(0, numberOfBooks).mapToObj(i -> bookToAdd).forEach(books::add);
    }

    public List<Book> sortedLibrary(Comparator<Book> comp) {
        return books.stream().sorted(comp).toList();
    }

    public Book searchBook(int searchThisISBN) {
        return books.stream().filter(book -> book.getISBN() == searchThisISBN).findFirst().get();
    }

    public List<Book> searchBook(Author authorToSearch) {
        return books.stream().filter(book -> book.getAuthor() == authorToSearch).sorted(Comparator.comparing(Book::getPublishingYear)).toList();
    }
}
