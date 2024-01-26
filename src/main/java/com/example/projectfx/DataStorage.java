import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class DataStorage {

    private static final String FILE_PATH = "library_data.txt";

    public static void saveData(List<Book> books) {
        try (ObjectOutputStream outputStream = new ObjectOutputStream(new FileOutputStream(FILE_PATH))) {
            outputStream.writeObject(books);
            System.out.println("Data saved successfully.");
        } catch (IOException e) {
            e.printStackTrace();
            System.err.println("Error saving data to file.");
        }
    }

    public static List<Book> loadData() {
        List<Book> books = new ArrayList<>();
        try (ObjectInputStream inputStream = new ObjectInputStream(new FileInputStream(FILE_PATH))) {
            Object object = inputStream.readObject();
            if (object instanceof List<?>) {
                books = (List<Book>) object;
                System.out.println("Data loaded successfully.");
            }
        } catch (IOException | ClassNotFoundException e) {
            // Ignore if the file does not exist or an error occurs during deserialization
        }
        return books;
    }
}
