package at.ac.uibk.pm.gui.tweetinspector;

import javafx.beans.binding.DoubleBinding;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.TableColumn;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.FileChooser;

import java.io.File;
import java.util.Arrays;

/**
 * Class contains controller for TweetInspector UI.
 */
public class TweetInspectorController {

    /**
     * Constructor.
     *
     * @param ui UI to be controlled and filled with this controller.
     */
    public TweetInspectorController(TweetInspectorUI ui) {
        this.ui = ui;
        this.data = FXCollections.observableArrayList();

        this.ui.getButtonLoad().setOnAction((ActionEvent event) -> this.loadTweets());
        this.ui.getButtonStore().setOnAction((ActionEvent event) -> this.storeTweets());

        // lambda for handler for About menu item
        this.ui.getMenuAbout().setOnAction((ActionEvent event) -> {
            Alert alert = new Alert(AlertType.INFORMATION);
            alert.setTitle("About");
            alert.setHeaderText("Lecture \"Programmiermethodik\" 2021\n ");
            alert.setContentText("Summer Semester 2021\nUniversity of Innsbruck");
            alert.show();
        });

        // lambda for handler for Close menu item
        this.ui.getMenuClose().setOnAction((ActionEvent event) -> System.exit(0));

        // lambda for handler for clear button for table
        this.ui.getButtonClear().setOnAction((ActionEvent event) -> TweetInspectorController.this.data.clear());

        this.initializeTweetTable();
    }

    /**
     * Actions performed on clicking the load button - loading tweets from
     * json-file.
     */

    public void loadTweets() {
        // open file chooser, get chosen file
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Select Input File");
        File inputFile = fileChooser.showOpenDialog(TweetInspectorController.this.ui.getPrimaryStage());

        TweetInspectorController.this.data.clear();

        // read data from specified file
        try {
            TweetInspectorController.this.data.addAll(TweetIO.readTwitterJSONFile(inputFile));
        } catch (Exception e) {
            // if exception occurred, notify user and give feedback
            Alert alert = new Alert(AlertType.ERROR);
            alert.setTitle("Error occurred!");
            alert.setHeaderText("Did not parse file correctly!");
            alert.setContentText("Please specify a valid JSON-file!");
            alert.show();
        }
    }

    /**
     * actions performed to store tweets of table in csv file
     */
    public void storeTweets() {
        // open file chooser, retrieve chosen file
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Select Destination");
        File outputFile = fileChooser.showSaveDialog(TweetInspectorController.this.ui.getPrimaryStage());

        if (outputFile != null) {
            TweetIO.writeTweetsAsCSV(TweetInspectorController.this.data, outputFile);
            // inform user on successful export
            Alert alert = new Alert(AlertType.INFORMATION);
            alert.setTitle("Stored Successfuly");
            alert.setHeaderText(null);
            alert.setContentText("CSV-File was stored successfully to " + outputFile + "!");

            alert.showAndWait();
        } else {
            Alert alert = new Alert(AlertType.WARNING);
            alert.setTitle("Warning");
            alert.setContentText("No file was chosen");
        }
    }

    private ObservableList<Tweet> data;
    private TweetInspectorUI ui;

    /**
     * Initializes the TableView component of the UI.
     */
    private void initializeTweetTable() {
        // create columns

        TableColumn<Tweet, String> colTweetID = new TableColumn<Tweet, String>("Tweet ID");
        TableColumn<Tweet, String> colUser = new TableColumn<Tweet, String>("User ID");
        TableColumn<Tweet, String> colText = new TableColumn<Tweet, String>("Text");
        TableColumn<Tweet, String> colSource = new TableColumn<Tweet, String>("Source");

        // bind model (tweet bean) to table via cellValueFactory
        colTweetID.setCellValueFactory(new PropertyValueFactory<Tweet, String>("tweetID"));
        colUser.setCellValueFactory(new PropertyValueFactory<Tweet, String>("user"));
        colText.setCellValueFactory(new PropertyValueFactory<Tweet, String>("tweetText"));
        colSource.setCellValueFactory(new PropertyValueFactory<Tweet, String>("source"));

        // specify width of columns (spread equally)
        DoubleBinding width = this.ui.getTableTweets().widthProperty().divide(4);
        colSource.prefWidthProperty().bind(width);
        colUser.prefWidthProperty().bind(width);
        colText.prefWidthProperty().bind(width);
        colTweetID.prefWidthProperty().bind(width);

        // make columns sortable
        colTweetID.setSortable(true);
        colUser.setSortable(true);
        colText.setSortable(true);
        colSource.setSortable(true);

        // add example data
        this.data.add(new Tweet("this is an example tweet", "John Doe", "TweetDeck", "2343957"));
        this.ui.getTableTweets().setItems(this.data);
        this.ui.getTableTweets().getColumns().addAll(Arrays.asList(colTweetID, colUser, colText, colSource));
    }
}
