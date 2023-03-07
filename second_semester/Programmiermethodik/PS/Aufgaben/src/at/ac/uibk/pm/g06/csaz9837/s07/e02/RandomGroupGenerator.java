package at.ac.uibk.pm.g06.csaz9837.s07.e02;

import java.util.*;

public class RandomGroupGenerator<T> implements GroupGenerator<T>{
    @Override
    public List<Group<T>> groupGenerator(List<Player<T>> players, int numberOfGroups) {
        List<Group<T>> groupList = new ArrayList<>();
        //Collections.shuffle(players);
        int a = 0;
        for (int i = 0; i < numberOfGroups; i++) {
            List<Player<T>> newPlayerList = new ArrayList<>();
            for (int j = 0; j < (players.size()/numberOfGroups); j++) {
                newPlayerList.add(players.get(a));
                a++;
            }
            groupList.add(new Group<>(newPlayerList));
        }
       // System.out.println(groupList);
        return groupList;
    }
}
