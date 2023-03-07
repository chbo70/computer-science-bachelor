package at.ac.uibk.pm.g06.csaz9837.midterm1.e01;

import java.util.ArrayList;

public class Exercise1Application {
    public static void main(String[] args) {
        OutfieldPlayer player1 = new Attacker("Cristiano Ronaldo", Position.CENTER, 4);
        OutfieldPlayer player2 = new Attacker("Lionel Messi", Position.LEFT, 3);
        OutfieldPlayer player3 = new Defender("Harry Maguire", Position.LEFT, 3);
        OutfieldPlayer player4 = new Defender("Mark De Ligt", Position.RIGHT, 2);
        Team team1 = new Team("Manchester United", new ArrayList<OutfieldPlayer>());
        team1.addPlayer(player1);
        team1.addPlayer(player2);
        team1.addPlayer(player3);
        team1.addPlayer(player4);
        System.out.println(team1.toString());
        team1.printTeam();
    }
}
