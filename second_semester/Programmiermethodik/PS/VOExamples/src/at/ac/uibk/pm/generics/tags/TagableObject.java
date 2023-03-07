package at.ac.uibk.pm.generics.tags;

/**
 * Class demonstrates usage of tagable interface.
 */
public class TagableObject implements Tagable<String> {
    private String tag;

    /**
     * Method sets tag for current object.
     *
     * @param t content of tag to be set
     */
    @Override
    public void setTag(final String t) {
        this.tag = t;
    }

    /**
     * Method retrieves set tag.
     *
     * @return tag assigned to object.
     */
    @Override
    public String getTag() {
        return this.tag;
    }
}