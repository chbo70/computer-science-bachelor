package at.ac.uibk.pm.generics.tags;

/**
 * Class demonstrates use of TaggableObject (implementing Tagable-interface).
 */

public final class TagableApplication {
    /**
     * Main method for demonstrating tagable objects.
     *
     * @param args not used.
     */
    public static void main(final String[] args) {
        TagableObject tagableObject = new TagableObject();
        tagableObject.setTag("fancy tag for object");
        System.out.println(tagableObject.getTag());
    }

}
