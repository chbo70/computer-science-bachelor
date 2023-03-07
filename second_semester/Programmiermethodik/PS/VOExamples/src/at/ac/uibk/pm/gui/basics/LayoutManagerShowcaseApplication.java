package at.ac.uibk.pm.gui.basics;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

/**
 * Class showcases different LayoutManagers for JavaFX.
 */
public class LayoutManagerShowcaseApplication extends Application {
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

        // BorderPane

        BorderPane root = new BorderPane();
        root.setTop(label);
        BorderPane.setAlignment(label, Pos.CENTER);
        BorderPane.setMargin(label, new Insets(10.0, 2.0, 2.0, 2.0));
        root.setLeft(buttonStart);
        root.setCenter(buttonStop);
        root.setRight(buttonPause);

        // FlowPane
        /*
         * //FlowPane root = new FlowPane(); // vertical FlowPane FlowPane root
         * = new FlowPane(Orientation.VERTICAL); root.setStyle(
         * "-fx-background-color: #ffffff"); root.setPadding(new Insets(5.0,
         * 5.0, 5.0, 5.0)); root.setHgap(15.0); root.setVgap(10.0);
         * root.getChildren().add(label); root.getChildren().add(buttonStart);
         * root.getChildren().add(buttonStop);
         * root.getChildren().add(buttonPause);
         */

        /*
         * HBox root = new HBox(); root.setSpacing(20.0); root.setPadding(new
         * Insets(5.0, 5.0, 5.0, 5.0)); root.getChildren().add(label);
         * root.getChildren().add(buttonStart);
         * root.getChildren().add(buttonStop);
         * root.getChildren().add(buttonPause);
         */

        /*
         * GridPane root = new GridPane(); root.setPadding(new Insets(5.0, 5.0,
         * 5.0, 5.0)); root.setHgap(5.0); root.add(label, 0, 0);
         * root.add(buttonStart, 1, 1); root.add(buttonStop, 2, 1);
         * root.add(buttonPause, 3, 1);
         */

        // set scene
        primaryStage.setScene(new Scene(root, 500, 250));
        primaryStage.show();
    }
}
