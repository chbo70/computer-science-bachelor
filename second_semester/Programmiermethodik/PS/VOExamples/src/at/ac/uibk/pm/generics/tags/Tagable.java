package at.ac.uibk.pm.generics.tags;

/**
 * Interface specifies behavior of elements which can be tagged.
 *
 * @param <T> the datatype of the tag
 */
public interface Tagable<T> {
    /**
     * Sets given tag.
     *
     * @param tag tag to be set
     */
    void setTag(T tag);

    /**
     * Returns tag.
     *
     * @return tag.
     */
    T getTag();
}
