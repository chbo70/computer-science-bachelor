package at.ac.uibk.pm.g06.csaz9837.s07.e02;

import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        List<Player<String>> listOfPlayers = new ArrayList<>();
        Player<String> player1 = new Player<>("Jason");
        Player<String> player2 = new Player<>("Walter");
        Player<String> player3 = new Player<>("Anna");
        Player<String> player4 = new Player<>("Mason");
        Player<String> player5 = new Player<>("Dino");
        Player<String> player6 = new Player<>("Flido");
        Player<String> player7 = new Player<>("Justin");
        Player<String> player8 = new Player<>("Fink");
        Player<String> player9 = new Player<>("David");
        listOfPlayers.add(player1);
        listOfPlayers.add(player2);
        listOfPlayers.add(player3);
        listOfPlayers.add(player4);
        listOfPlayers.add(player5);
        listOfPlayers.add(player6);
        listOfPlayers.add(player7);
        listOfPlayers.add(player8);
        listOfPlayers.add(player9);
        TournamentManager<String> manager = new TournamentManager<>(listOfPlayers,2);
        GroupGenerator<String> generator = new RandomGroupGenerator<>();
        manager.assignToGroup(generator);
        //System.out.println(manager);
    }
}
