import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class LibraryApp extends Application {

    private Library library = new Library();
    private ListView<Book> bookListView = new ListView<>();

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) {
        primaryStage.setTitle("Library Management System");

        BorderPane borderPane = new BorderPane();

        // Create UI components
        TextField titleField = new TextField();
        TextField authorField = new TextField();
        Button addButton = new Button("Add Book");
        VBox addBookVBox = new VBox(10, new Label("Title:"), titleField, new Label("Author:"), authorField, addButton);
        addBookVBox.setPadding(new Insets(10));

        addButton.setOnAction(e -> {
            addBook(titleField.getText(), authorField.getText());
            updateBookListView();
            titleField.clear();
            authorField.clear();
        });

        bookListView.setCellFactory(param -> new ListCell<Book>() {
            @Override
            protected void updateItem(Book item, boolean empty) {
                super.updateItem(item, empty);

                if (empty || item == null) {
                    setText(null);
                } else {
                    setText(item.getTitle() + " by " + item.getAuthor());
                }
            }
        });

        // Set up the layout
        borderPane.setLeft(addBookVBox);
        borderPane.setCenter(bookListView);

        Scene scene = new Scene(borderPane, 400, 300);
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    private void addBook(String title, String author) {
        int id = library.getBooks().size() + 1;
        Book newBook = new Book(id, title, author, true);
        library.addBook(newBook);
    }

    private void updateBookListView() {
        bookListView.getItems().setAll(library.getBooks());
    }
}
