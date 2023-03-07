package at.ac.uibk.pm.exceptions.resourceloader;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.nio.file.Files;
import java.nio.file.Paths;

/**
 * Class demonstrates loading of various resources (file, url, sql) and shows
 * exception chaining.
 */
public class ResourceLoader {

    /**
     * Method reads from the given url (can be file or url) and returns its
     * contents.
     *
     * @param url - path to resource to be loaded.
     * @return string holding the contents of the given resource.
     * @throws ResourceException if error occurs when reading resource.
     */
    public static String loadResource(String url) throws ResourceException {
        BufferedReader r = null;
        try {
            if (url.startsWith("file://")) {
                r = new BufferedReader(new FileReader(url.substring(7)));
            } else if (url.startsWith("http://")) {
                URL u = new URL(url);
                URLConnection c = u.openConnection();
                r = Files.newBufferedReader(Paths.get(url));
            } else if (url.startsWith("sql://")) {
                throw new ResourceException("currently no database connection implemented");
            } else {
                throw new ResourceException("unknown URL type, please start with http:// or file:// or sql://");
            }
        } catch (MalformedURLException e) {
            throw new ResourceException(e);
        } catch (IOException e) {
            throw new ResourceException(e);
        }
        String s;
        StringBuilder sb = new StringBuilder();
        try {
            String line = r.readLine();
            while (line != null) {
                sb.append(line);
                sb.append("\n");
                line = r.readLine();
            }
            s = sb.toString();
        } catch (IOException e) {
            throw new ResourceException(e);
        } finally {
            try {
                r.close();
            } catch (IOException e) {
                throw new ResourceException(e);
            }
        }
        return s;
    }
}