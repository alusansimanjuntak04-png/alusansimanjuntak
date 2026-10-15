import javafx. application.Application;
import javafx. scene. Scene;
import javafx. scene. control. Button;
import javafx. scene. layout . StackPane;
import javafx.stage.Stage;

public class ObjekAnonymous extends Application {
    public static void main(String[] args) { launch (args) ; }
    @Override public void start (Stage stage)
    }
        Button button = new Button ("Click Me") ;
        button. setOnAction (
            event
            -> System. out.println (
                "Button clicked!")); // Anonymous object melalui lambda

        StackPane root = new StackPane (button) ;
        stage.setScene (new Scene (root, 300, 200));
        stage.show ();
    }
}