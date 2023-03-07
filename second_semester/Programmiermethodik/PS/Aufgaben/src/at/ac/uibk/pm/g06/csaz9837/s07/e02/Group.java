package at.ac.uibk.pm.g06.csaz9837.s07.e02;

import java.util.List;

public class Group<T>{
    private List<Player<T>> playerInGroup;

    public Group(List<Player<T>> playerInGroup) {
        this.playerInGroup = playerInGroup;
    }

    @Override
    public String toString() {
        return "\nGroup: " +
                "playerInGroup=" + playerInGroup;
    }
}
