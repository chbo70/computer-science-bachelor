package at.ac.uibk.pm.gui.tweetinspector;

import javafx.application.Application;

/**
 * Class is main class for tweet inspector, an application for reading tweets
 * from json files gathered over the Twitter API. Displays tweets in a
 * JavaFX-GUI.
 */
public class TweetInspectorApplication {
    /**
     * Main method for tweet inspector application.
     *
     * @param args not used
     */
    public static void main(String[] args) {
        Application.launch(TweetInspectorUI.class, args);
    }

}
