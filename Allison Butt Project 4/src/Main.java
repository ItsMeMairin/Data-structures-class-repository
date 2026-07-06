/**
 * The first sentence acts as the short summary.
 *
 * Detailed description goes here. You can format text using HTML tags 
 * like <p>paragraphs</p> or <code>code snippets</code>.
 *
 * @param parameterName Description of the input parameter.
 * @return Description of the return value.
 * @throws ExceptionName Description of why this exception is thrown.
 * @see AnotherClass
 */
//public int processData(String parameterName) throws ExceptionName {
    // Method implementation }


import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

public class Main extends Application {

    @Override
    public void start(Stage stage) {
        Label label = new Label("JavaFX Works!");

        Scene scene = new Scene(new StackPane(label), 300, 200);
        stage.setScene(scene);
        stage.setTitle("Test");
        stage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}