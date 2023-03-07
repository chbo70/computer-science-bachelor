package at.ac.uibk.pm.g06.csaz9837.s06.e02;

import java.util.Random;

public class RandomizedVoteGenerator extends VoteGenerator{
    private ElectionSystem electionSystem = new ElectionSystem();
    private Random rand = new Random();

    @Override
    public int assignVotes() {
        return rand.nextInt(100000);
    }
}
