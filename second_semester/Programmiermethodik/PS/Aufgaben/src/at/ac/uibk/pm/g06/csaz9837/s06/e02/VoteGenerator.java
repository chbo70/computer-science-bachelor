package at.ac.uibk.pm.g06.csaz9837.s06.e02;

import java.util.ArrayList;
import java.util.Map;
import java.util.Random;

public abstract class VoteGenerator {
    private Random rand = new Random();
    public void setSeed(){
        rand.setSeed(472396082);
    }
    public abstract int assignVotes();
}
