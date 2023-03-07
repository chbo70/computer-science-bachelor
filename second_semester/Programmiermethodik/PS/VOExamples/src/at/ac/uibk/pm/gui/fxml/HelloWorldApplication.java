package at.ac.uibk.pm.gui.fxml;


/**
 * Class defines UI for simple FXML-based HelloWorld application, launches UI.
 */
public class HelloWorldApplication extends Application {
    private static final String FXML_FILE = "HelloWorld.fxml";

    /**
     * Main method for hello world application.
     *
     * @param args not used
     */
    public static void main(String[] args) {
        launch(args);
    }

    /**
     * Start method (overridden) loads FXML file and starts GUI.
     *
     * @param primaryStage - the stage for the GUI
     */
    @Override
    public void start(Stage primaryStage) throws Exception {
        Parent root = FXMLLoader.load(getClass().getResource(FXML_FILE));

        Scene scene = new Scene(root, 600, 500);
        primaryStage.setTitle("Hello World");
        primaryStage.setScene(scene);
        primaryStage.show();

    }

}
