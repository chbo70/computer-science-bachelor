package at.ac.uibk.pm.g06.csaz9837.s06.e02;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class ElectionSystem {
    private Map<ElectoralRegion, ArrayList<PoliticalParty>> electionSystem;

    public ElectionSystem() {
        this.electionSystem = new HashMap<>();
    }

    public void addRegion(ElectoralRegion regionToAdd, ArrayList<PoliticalParty> partiesAndVotes){
        electionSystem.put(regionToAdd,partiesAndVotes);
    }

    public void printMap(){
        for (ElectoralRegion e: electionSystem.keySet()){
            System.out.println("Current Region: " + e);
            for (PoliticalParty p : electionSystem.get(e)){
                System.out.println(p.toString());
            }
        }
    }
    public void vote(VoteGenerator voteGenerator){
        for (ElectoralRegion e: electionSystem.keySet()){
            //System.out.println(electionSystem.get(e).size());
            for (int i = 0; i < electionSystem.get(e).size(); i++) {
                PoliticalParty p = electionSystem.get(e).get(i);
                PoliticalParty party1 = new PoliticalParty(p.getName(), p.getTotalVotes());
                party1.setTotalVotes(voteGenerator.assignVotes());
                electionSystem.get(e).set(i,party1);
            }
        }
    }
    public void assignSeats(){

    }
}
