package at.ac.uibk.pm.exceptions.resourceloader;

/**
 * Class shows exception handling for a resource loader (currently, only a file
 * reader is implemented).
 */

public class ResourceLoaderApplication {
    /**
     * Main method for resource loader application.
     *
     * @param args not used.
     */
    public static void main(String[] args) {
        try {

            // should be called with cmd-line argument file://InputFile.txt
            // (given file exists; e.g. as resources/alice_in_wonderland.txt)
            System.out.println(ResourceLoader.loadResource(args[0]));
        } catch (ResourceException e) {
            System.out.println("Please restart the program and specify an input url or file");
            // e.printStackTrace();
        }
    }

}
