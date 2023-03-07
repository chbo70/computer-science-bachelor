package at.ac.uibk.pm.g06.csaz9837.midterm2.e02;

import java.util.Comparator;

public class LibraryApplication {
    public static void main(String[] args) {
        Author author1 = new Author("John", "Mayer");
        Book bookA1 = new Book(1111, "Life is good", author1, 2001);
        Book bookA2 = new Book(2222, "Life could be better", author1, 2010);
        Author author2 = new Author("Marissa", "Baelding");
        Book bookB1 = new Book(2211, "Death is not Fun", author2, 2011);
        Book bookB2 = new Book(2233, "Star Wars is super", author2, 1999);
        Library library = new Library();

        //laut Angabe hab ich verstanden, dass man die beliebige Anzahl an Exemplare selber wählen kann
        library.addBooks(2, bookA1);
        library.addBooks(1, bookA2);
        library.addBooks(1, bookB1);
        library.addBooks(3, bookB2);
        System.out.println(library.searchBook(1111));
        System.out.println();
        System.out.println(library.searchBook(author2));
        System.out.println();
        Comparator<Book> comp = (o1, o2) -> {
            //sort by title name alphabetically
            if (o1.getTitle().compareTo(o2.getTitle()) < 0) {
                return 1;
            } else if (o1.getTitle().compareTo(o2.getTitle()) > 0) {
                return -1;
            } else {
                return 0;
            }
        };
        System.out.println(library.sortedLibrary(comp));
    }
}
