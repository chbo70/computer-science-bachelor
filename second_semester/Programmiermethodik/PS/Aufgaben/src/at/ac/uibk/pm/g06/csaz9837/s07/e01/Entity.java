package at.ac.uibk.pm.g06.csaz9837.s07.e01;

import java.util.Comparator;

public abstract class Entity<U> implements Comparable<String> {
    private final U identifier;

    public Entity(U entity) {
        this.identifier = entity;
    }

    public U getIdentifier() {
        return identifier;
    }

}