package at.ac.uibk.pm.gui.basics;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

/**
 * Class showcases a first example using absolute positioning of UI elements.
 */
public class AbsolutePositioningApplication extends Application {
    /**
     * Main method for absolute positioning of UI elements.
     *
     * @param args not used
     */
    public static void main(String[] args) {
        launch(args);
    }

    /**
     * Method starts JavaFX application, sets stage and adds buttons and a
     * label. UI elements are positioned absolutely.
     *
     * @param primaryStage provided by JavaFX
     */
    @Override
    public void start(Stage primaryStage) {
        primaryStage.setTitle("Layout Showcase");

        // create buttons
        Button buttonStart = new Button();
        buttonStart.setText("Start");
        Button buttonStop = new Button();
        buttonStop.setText("Stop");
        Button buttonPause = new Button();
        buttonPause.setText("Pause");

        Label label = new Label();
        label.setText("Our buttons: ");

        buttonStop.setLayoutX(100.0);
        buttonStop.setLayoutY(0.0);

        buttonStart.setLayoutX(150.0);
        buttonStop.setLayoutY(0.0);

        buttonPause.setLayoutX(200.0);
        buttonPause.setLayoutY(0.0);

        label.setLayoutX(0);
        label.setLayoutY(0);

        // pane
        Pane root = new Pane();
        root.getChildren().add(label);
        root.getChildren().add(buttonStart);
        root.getChildren().add(buttonStop);
        root.getChildren().add(buttonPause);

        // set scene
        primaryStage.setScene(new Scene(root, 300, 150));
        primaryStage.show();
    }
}
