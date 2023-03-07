package at.ac.uibk.pm.gui.tweetinspector;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.scene.text.Font;
import javafx.stage.Stage;

import java.text.ParseException;

/**
 * Class defines UI for TweetInspector application, launches controller for UI.
 */
public class TweetInspectorUI extends Application {

    private TableView<Tweet> tableTweets;
    private Stage primaryStage;
    private MenuBar menuBar;
    private Button buttonClear;
    private Button buttonLoad;
    private Button buttonStore;
    private MenuItem menuClose;
    private MenuItem menuAbout;

    /**
     * Method creates button pane (three buttons), returns HBox with buttons
     * contained.
     *
     * @return HBox with buttons contained.
     */
    private HBox createButtonPane() {
        this.buttonLoad = new Button("Load Data");
        this.buttonClear = new Button("Clear Table");
        this.buttonStore = new Button("Store Table as CSV");
        this.buttonLoad.setDefaultButton(true);

        HBox buttonPane = new HBox();
        buttonPane.setSpacing(10.0);
        buttonPane.setPadding(new Insets(10.0, 10.0, 10.0, 10.0));
        buttonPane.getChildren().addAll(this.buttonLoad, this.buttonClear, this.buttonStore);

        return buttonPane;
    }

    /**
     * Method creates menu for application.
     *
     * @return MenuBar-object containing the menu-items.
     */
    private MenuBar createMenu() {
        MenuBar menuBar = new MenuBar();
        // super-menu
        Menu menuFile = new Menu("File");
        Menu menuHelp = new Menu("Help");

        // sub-menus
        this.menuClose = new MenuItem("Close");
        menuFile.getItems().add(this.menuClose);

        this.menuAbout = new MenuItem("About");
        menuHelp.getItems().add(this.menuAbout);

        menuBar.getMenus().addAll(menuFile, menuHelp);
        return menuBar;
    }

    /**
     * Method creates TableView-Object holding list of tweets
     *
     * @return
     * @throws ParseException
     */
    private TableView<Tweet> createTableView() throws ParseException {
        return new TableView<Tweet>();
    }

    /**
     * Returns clear-button.
     *
     * @return button-object for clear-button.
     */
    public Button getButtonClear() {
        return this.buttonClear;
    }

    /**
     * Returns load-button.
     *
     * @return button-object for load-button.
     */
    public Button getButtonLoad() {
        return this.buttonLoad;
    }

    /**
     * Returns store-button.
     *
     * @return button-object for store-button.
     */
    public Button getButtonStore() {
        return this.buttonStore;
    }

    /**
     * Returns about-menu item.
     *
     * @return MenuItem-object for "About".
     */
    public MenuItem getMenuAbout() {
        return this.menuAbout;
    }

    /**
     * Returns the whole menuBar object
     *
     * @return MenuBar object.
     */
    public MenuBar getMenuBar() {
        return this.menuBar;
    }

    /**
     * Returns close-menu item.
     *
     * @return MenuItem-object for "Close".
     */
    public MenuItem getMenuClose() {
        return this.menuClose;
    }

    /**
     * Returns the current stage.
     *
     * @return stage currently used.
     */
    public Stage getPrimaryStage() {
        return this.primaryStage;
    }

    /**
     * Returns the TableView-object used for holding tweets.
     *
     * @return TableView-object
     */
    public TableView<Tweet> getTableTweets() {
        return this.tableTweets;
    }

    /**
     * Main method for starting and initializing the UI. Overrides start-method
     * of Application-class.
     */
    @Override
    public void start(Stage stage) throws Exception {
        this.primaryStage = stage;
        this.primaryStage.setTitle("TweetInspector");

        // define UI-Elements
        Label labelHeader = new Label("TweetInspector");
        labelHeader.setFont(new Font("Inconsolata for Powerline", 40.0));

        BorderPane root = new BorderPane();

        // add menu on top
        VBox top = new VBox();
        this.menuBar = this.createMenu();
        top.getChildren().add(this.menuBar);

        // main (center) region
        GridPane mainPane = new GridPane();// (Orrientation.VERTICAL);
        mainPane.setPadding(new Insets(10.0, 10.0, 10.0, 10.0));
        mainPane.setVgap(50.0);

        mainPane.add(labelHeader, 0, 0);
        this.tableTweets = this.createTableView();
        mainPane.add(this.tableTweets, 0, 1);
        GridPane.setHgrow(this.tableTweets, Priority.ALWAYS);
        GridPane.setVgrow(this.tableTweets, Priority.ALWAYS);

        // add button pane on bottom
        HBox buttonPane = this.createButtonPane();
        root.setTop(top);
        root.setCenter(mainPane);
        root.setBottom(buttonPane);
        Scene currentScene = new Scene(root, 800, 600);

        // set scene
        this.primaryStage.setScene(currentScene);
        this.primaryStage.show();
        this.primaryStage.setMaximized(true);

        // initialize controller to fill UI with data and add controls
        new TweetInspectorController(this);

    }
}
