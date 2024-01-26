import java.util.ArrayList;
import java.util.List;

public class Library {
    private List<Book> books;

    public Library() {
        // Load data from file when the Library is created
        this.books = DataStorage.loadData();
    }

    public List<Book> getBooks() {
        return books;
    }

    public void addBook(Book book) {
        books.add(book);
        // Save data to file after adding a new book
        DataStorage.saveData(books);
        System.out.println("Book added successfully.");
    }
}
