package at.ac.uibk.pm.gui.tweetinspector;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

/**
 * Class holds information about tweets,
 */
public class Tweet {

    private StringProperty tweetText;
    private StringProperty user;
    private StringProperty source;
    private StringProperty tweetID;

    /**
     * Constructor with empty fields
     */
    public Tweet() {
        this.tweetText = new SimpleStringProperty();
        this.user = new SimpleStringProperty();
        this.source = new SimpleStringProperty();
        this.tweetID = new SimpleStringProperty();

    }

    /**
     * Constructor
     *
     * @param tweet   text of the tweet.
     * @param user    userID.
     * @param source  how the tweet was sent.
     * @param tweetID ID of the tweet.
     */
    public Tweet(String tweet, String user, String source, String tweetID) {
        this.tweetText = new SimpleStringProperty(tweet);
        this.user = new SimpleStringProperty(user);
        this.source = new SimpleStringProperty(source);
        this.tweetID = new SimpleStringProperty(tweetID);
    }

    public String getSource() {
        return this.source.get();
    }

    public String getTweetID() {
        return this.tweetID.get();
    }

    public String getTweetText() {
        return this.tweetText.get();
    }

    public String getUser() {
        return this.user.get();
    }

    public void setSource(String source) {
        this.source.set(source);
    }

    public void setTweet(String tweet) {
        this.tweetText.set(tweet);
    }

    public void setTweetID(Long tweetID) {
        this.tweetID.set(String.valueOf(tweetID));
    }

    public void setTweetID(String tweetID) {
        this.tweetID.set(tweetID);
    }

    public void setUser(String user) {
        this.user.set(user);
    }

    @Override
    public String toString() {
        return "userID: " + this.user + ", tweet: " + this.tweetText + " ("
                + this.tweetID + "), service: " + this.source + "\n";
    }

}
