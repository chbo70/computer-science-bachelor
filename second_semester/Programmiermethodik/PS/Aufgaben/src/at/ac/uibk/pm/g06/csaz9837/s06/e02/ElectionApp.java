package at.ac.uibk.pm.g06.csaz9837.s06.e02;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;

public class ElectionApp {
    public static void main(String[] args) {
        ElectionSystem electionSystem = new ElectionSystem();
        ElectoralRegion region1 = new ElectoralRegion("West", 10);
        ElectoralRegion region2 = new ElectoralRegion("Nord", 10);
        ElectoralRegion region3 = new ElectoralRegion("South", 10);
        PoliticalParty party1 = new PoliticalParty("ÖVP", 0);
        PoliticalParty party2 = new PoliticalParty("SPÖ",0);
        PoliticalParty party3 = new PoliticalParty("KPÖ",0);
        VoteGenerator voteGenerator = new RandomizedVoteGenerator();
        ArrayList<PoliticalParty> partiesAndVoteRegion1 = new ArrayList<>();
        partiesAndVoteRegion1.add(party1);
        partiesAndVoteRegion1.add(party2);
        partiesAndVoteRegion1.add(party3);
        ArrayList<PoliticalParty> partiesAndVoteRegion2 = new ArrayList<>(partiesAndVoteRegion1);
        ArrayList<PoliticalParty> partiesAndVoteRegion3 = new ArrayList<>(partiesAndVoteRegion1);
        electionSystem.addRegion(region1, partiesAndVoteRegion1);
        electionSystem.addRegion(region2, partiesAndVoteRegion2);
        electionSystem.addRegion(region3, partiesAndVoteRegion3);
        electionSystem.vote(voteGenerator);
        Collections.sort(partiesAndVoteRegion1);
        Collections.sort(partiesAndVoteRegion2);
        Collections.sort(partiesAndVoteRegion3);
        electionSystem.printMap();
    }
}
