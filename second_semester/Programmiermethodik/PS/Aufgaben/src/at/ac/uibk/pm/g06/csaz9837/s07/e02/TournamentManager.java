package at.ac.uibk.pm.g06.csaz9837.s07.e02;

import java.util.ArrayList;
import java.util.List;

public class TournamentManager <T>{
    //Players should be Generic, maybe implement it as abstract
    //Players should be assigned to a Group where they compete
    //result should be Generic too, but before points need to be assigned
    //after that a method is needed for returning a Ranking List
    private List<Player<T>> listOfPlayers;
    private List<Group<T>> groupList;
    private int numberOfGroups;

    public TournamentManager(List<Player<T>> listOfPlayers,  int numberOfGroups) {
        this.listOfPlayers = listOfPlayers;
        this.groupList = new ArrayList<>();
        this.numberOfGroups = numberOfGroups;
    }

    public void assignToGroup(GroupGenerator<T> generator){
        groupList = generator.groupGenerator(listOfPlayers, numberOfGroups);
        System.out.println(groupList);
    }

    @Override
    public String toString() {
        return "TournamentManager: \n" +
                "groupList=" + groupList;
    }
}
