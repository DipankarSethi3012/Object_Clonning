import java.util.*;

// ------------------ Book Class ------------------
class Book {
    String title;
    String author;

    Book(String title, String author) {
        this.title = title;
        this.author = author;
    }
}

// ------------------ Library Class ------------------
class Library {
    String name;
    List<Book> books;

    Library(String name) {
        this.name = name;
        this.books = new ArrayList<>();
    }

    void addBook(Book book) {
        books.add(book);
    }

    // Shallow Clone
    Library shallowClone() {
        Library library = new Library(this.name);
        library.books = this.books; // same list reference
        return library;
    }

    // Deep Clone
    Library deepClone() {
        Library library = new Library(this.name);
        List<Book> clonedBooks = new ArrayList<>();
        for (Book book : this.books) {
            clonedBooks.add(new Book(book.title, book.author)); // new Book objects
        }
        library.books = clonedBooks;
        return library;
    }

    void display() {
        System.out.println("Library : " + name);
        for (Book book : books) {
            System.out.println("Book : " + book.title + ", Author : " + book.author);
        }
    }

    List<Book> getBooks() {
        return books;
    }
}

// ------------------ Main Class ------------------
public class Main {
    public static void main(String[] args) {

        // Create sample data
        String name = "City Library";
        List<String> titles = Arrays.asList("Book A", "Book B", "Book C");
        List<String> authors = Arrays.asList("Author X", "Author Y", "Author Z");

        // Create original library and add books
        Library library = new Library(name);
        for (int i = 0; i < titles.size(); i++) {
            library.addBook(new Book(titles.get(i), authors.get(i)));
        }

        System.out.println("Original Library : ");
        library.display();

        // Create shallow and deep clones
        Library shallowLibrary = library.shallowClone();
        Library deepLibrary = library.deepClone();

        // Modify original library book
        int changeIndex = 1; // modify second book
        String changeTitle = "Modified Book B";
        String changeAuthor = "Modified Author Y";
        library.getBooks().get(changeIndex).title = changeTitle;
        library.getBooks().get(changeIndex).author = changeAuthor;

        System.out.println("\nAfter Modification : ");
        System.out.println("\nOriginal Library : ");
        library.display();

        System.out.println("\nShallow Clone : ");
        shallowLibrary.display();

        System.out.println("\nDeep Clone : ");
        deepLibrary.display();
    }
}
