import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Main extends Application {
    @Override
    public void start(Stage stage) throws Exception {
        // Load the FXML file - make sure main.fxml exists in your project
        Parent root = FXMLLoader.load(getClass().getResource("main.fxml"));
        
        // Set up the stage
        stage.setTitle("Welcome");
        stage.setScene(new Scene(root));
        stage.show();
    }

    public static void main(String[] args) {
        // Launch the JavaFX application
        launch(args);
    }
}