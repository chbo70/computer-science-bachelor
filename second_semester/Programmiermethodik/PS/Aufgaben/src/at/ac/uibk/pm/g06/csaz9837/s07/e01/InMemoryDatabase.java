package at.ac.uibk.pm.g06.csaz9837.s07.e01;

import java.util.*;

public class InMemoryDatabase<U extends Comparable, T extends Entity<U>>{
    private Map<U, T> database;
    //U = uniqueIdentifier ; T = storedEntity

    public InMemoryDatabase() {
        this.database = new HashMap<>();
    }

    public T save(T entity) {
        if (database.containsKey(entity.getIdentifier())) {
            throw new IllegalArgumentException();
        } else {
            database.put(entity.getIdentifier(), entity);
            return entity;
        }
    }

    public T delete(T entity) {
        if (!database.containsKey(entity.getIdentifier())) {
            throw new NoSuchElementException();
        } else {
            return database.remove(entity.getIdentifier());
        }
    }

    public Optional<T> findOne(U id) {
        //empty or value, using Optionals doesn't cause Exception
        //Optional<T> opt = Optional.empty();
        //database.get(id);
        /*
        for (T entity : database.values()) {
            if (entity.getIdentifier() == id) {
                opt = Optional.of(entity);
            }
        }
        */
        return Optional.of(database.get(id));
    }
    public List<T> findAll(Comparator<T> comparator){
        //List<T> sortedEntities = new ArrayList<>(database.values());
        //sortedEntities.sort(comparator);
        return database.values().stream().sorted(comparator).toList();
        //return sortedEntities;
    }

    @Override
    public String toString() {
        return "Database: \n" + "Entity: " + database + " ";
    }
}
