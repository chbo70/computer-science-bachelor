package at.ac.uibk.pm.gui.fxml;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;

/**
 * Simple Controller class for HelloWorld based on FXML.
 */
public class Controller {
    @FXML
    private Button buttonHelloWorld;

    /**
     * Method can be used to initialize further GUI elements and further
     * controllers.
     */
    @FXML
    private void initialize() {

    }

    /**
     * helloAction simply prints "Hello World" is defined as action to be
     * performed when clicking on button (cf. FXML-file)
     *
     * @param event - the event that triggers this action
     */
    @FXML
    private void helloAction(ActionEvent event) {
        System.out.println("Hello World");
    }

}
