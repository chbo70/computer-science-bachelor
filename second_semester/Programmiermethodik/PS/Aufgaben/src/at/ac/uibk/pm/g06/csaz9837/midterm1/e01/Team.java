package at.ac.uibk.pm.g06.csaz9837.midterm1.e01;

import java.util.ArrayList;

public class Team {
    private ArrayList<OutfieldPlayer> team;
    private String teamName;

    public Team(String teamName, ArrayList<OutfieldPlayer> team) {
        this.team = team;
        this.teamName = teamName;
    }

    public void addPlayer(OutfieldPlayer player) {
        //unique check fehlt, d.h. hier muss gecheckt werden ob der Player schon im Team ist
        team.add(player);
    }

    public void printTeam() {
        for (OutfieldPlayer outfieldPlayer : team) {
            System.out.println(outfieldPlayer);
        }
    }

    @Override
    public String toString() {
        return "Team Name : " + teamName + "\n" +
                "Players: ";
    }
}
