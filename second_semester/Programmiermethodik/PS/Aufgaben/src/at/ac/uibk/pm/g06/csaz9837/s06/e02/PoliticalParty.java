package at.ac.uibk.pm.g06.csaz9837.s06.e02;

import java.util.ArrayList;

public class PoliticalParty implements Comparable<PoliticalParty>{
    private String name;
    private Integer totalVotes;
    private Integer seatsReceived;

    public PoliticalParty(String name, Integer totalVotes) {
        this.name = name;
        this.totalVotes = totalVotes;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
    public Integer getTotalVotes() {
        return totalVotes;
    }

    public void setTotalVotes(Integer totalVotes) {
        this.totalVotes = totalVotes;
    }

    @Override
    public String toString() {
        return "\t" + name + " (votes: " + getTotalVotes() + ")";
    }

    @Override
    public int compareTo(PoliticalParty o) {
        return this.name.compareTo(o.name);
    }
}
