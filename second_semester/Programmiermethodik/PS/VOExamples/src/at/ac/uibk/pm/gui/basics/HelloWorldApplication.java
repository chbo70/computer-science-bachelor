package at.ac.uibk.pm.gui.basics;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

/**
 * Class showcases a first Hello World Example for JavaFX.
 */
public class HelloWorldApplication extends Application {

    /**
     * Main method for starting/launching javaFX application.
     *
     * @param args not used
     */
    public static void main(String[] args) {
        launch(args);
    }

    /**
     * Method starts JavaFX application, sets stage and adds a button. Overrides
     * start-method.
     */
    @Override
    public void start(Stage primaryStage) {
        primaryStage.setTitle("Hello World Application");
        // retrieve map of parameters
        // System.out.println(this.getParameters().getRaw().toString());
        // create button
        Button btn = new Button();
        btn.setText("Hello World");

        Label text = new Label();
        text.setText("This is our first button: ");

        // pane
        Pane root = new FlowPane();
        root.getChildren().add(text);
        root.getChildren().add(btn);

        // set scene
        primaryStage.setScene(new Scene(root, 200, 250));
        primaryStage.show();

    }
}
