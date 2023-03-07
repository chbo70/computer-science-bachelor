package at.ac.uibk.pm.gui.tweetinspector;

import com.google.gson.stream.JsonReader;
import com.google.gson.stream.JsonToken;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.util.List;

/**
 * Class holds IO methods for tweets (reading from json, writing to csv)
 */
public class TweetIO {

    /**
     * Method (static) reads tweet objects from array of json-objects.
     *
     * @param inputFile the file to be read.
     * @return observable list of tweets
     * @throws IOException           if error in reading from file occurs.
     * @throws IllegalStateException if json cannot be parsed correctly.
     */
    public static ObservableList<Tweet> readTwitterJSONFile(File inputFile) throws IOException, IllegalStateException {

        ObservableList<Tweet> result = FXCollections.observableArrayList();
        int objectsRead = 0;
        BufferedReader br = null;
        try {
            br = Files.newBufferedReader(inputFile.toPath(), StandardCharsets.UTF_8);
        } catch (UnsupportedEncodingException e) {
            System.out.println("Problems with encodings!");
            e.printStackTrace();
            System.exit(0);
        } catch (IOException e1) {
            System.out.println("Could not open input file: " + inputFile + ", quitting.");
            System.exit(0);
        }

        JsonReader reader = new JsonReader(br);
        reader.setLenient(true);

        reader.beginArray();
        while (reader.hasNext()) {
            Tweet tweet = new Tweet();
            reader.beginObject();
            while (reader.hasNext()) {

                String curr = reader.nextName();
                if (curr.equals("id") && reader.peek() != JsonToken.NULL) {
                    tweet.setTweetID(reader.nextLong());
                } else if (curr.equals("user") && reader.peek() != JsonToken.NULL) {
                    tweet.setUser(reader.nextString());
                } else if (curr.equals("source") && reader.peek() != JsonToken.NULL) {
                    tweet.setSource(reader.nextString());
                } else if (curr.equals("text") && reader.peek() != JsonToken.NULL) {
                    tweet.setTweet(reader.nextString());
                } else {
                    reader.skipValue();
                }
            }
            reader.endObject();
            result.add(tweet);
            ++objectsRead;
        }

        reader.endArray();
        reader.close();

        try {
            br.close();
        } catch (IOException e) {

            e.printStackTrace();
        }

        System.out.println("read " + objectsRead + " from file " + inputFile);
        return result;
    }

    /**
     * Method takes list of tweets and writes these to given output-file in
     * csv-format.
     *
     * @param tweets     list of tweets to be persisted.
     * @param outputFile path to output-file.
     */
    public static void writeTweetsAsCSV(List<Tweet> tweets, File outputFile) {
        try (BufferedWriter bw = Files.newBufferedWriter(outputFile.toPath())) {
            for (Tweet tweet : tweets) {
                bw.write(tweet.getTweetID() + ", \"" + tweet.getTweetText() + "\", \"" + tweet.getUser() + "\", \""
                        + tweet.getSource() + "\"\n");
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
