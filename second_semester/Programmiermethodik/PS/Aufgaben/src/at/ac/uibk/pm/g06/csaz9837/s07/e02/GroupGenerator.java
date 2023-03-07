package at.ac.uibk.pm.g06.csaz9837.s07.e02;

import java.util.List;

public interface GroupGenerator<T> {
    public List<Group<T>> groupGenerator(List<Player<T>> players, int numberOfGroups);
}
