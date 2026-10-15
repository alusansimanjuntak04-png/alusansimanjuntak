import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

// Class Main menjadi aplikasi JavaFX
public class Main extends Application {

    // Method main untuk menjalankan program
    public static void main(String[] args) {
        launch(args);
    }

    // Method start untuk membuat tampilan aplikasi
    @Override
    public void start(Stage stage) {

        // Membuat tombol dengan tulisan "Click Me"
        Button button = new Button("Click Me");

        // Memberi aksi pada tombol menggunakan lambda
        // Pesan akan muncul saat tombol diklik
        button.setOnAction(event ->
            System.out.println("Button clicked!")
        );

        // Membuat layout dan memasukkan tombol ke dalamnya
        StackPane root = new StackPane(button);

        // Membuat scene dengan ukuran 300 x 200
        stage.setScene(new Scene(root, 300, 200));

        // Menampilkan window
        stage.show();
    }
}