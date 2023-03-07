package at.ac.uibk.pm.g06.csaz9837.s09.e03;

public class RockPaperScissor {
    public static void main(String[] args) {
        Game game = new Game(new Player(), new Player());
        game.play();
        Player player1 = new Player();
        player1.printD();

        /*
        Player p1 = new Player();
        Player p2 = new Player();
        boolean gameWon = false;
        int numberOfPlayedRounds = 0; // Number of rounds played
        int p1Wins = p1.totalWins;
        int p2Wins = p2.totalWins;
        int draw = 0;
        String p1Choice;
        String p2Choice;
        // Game Loop
        do {
            System.out.println("***** Round: " + numberOfPlayedRounds + " *********************\n");
            System.out.println("Number of Draws: " + draw + "\n");
            p1Choice = p1.choice();
            System.out.println("Player 1: " + p1Choice + "\t Player 1 Total Wins: " + p1Wins);
            p2Choice = p2.choice();
            System.out.println("Player 2: " + p2Choice + "\t Player 2 Total Wins: " + p2Wins);
            if ((p1Choice.equals("rock")) && (p2Choice.equals("paper"))) {
                System.out.println("Player 2 Wins");
                p2Wins++; // trying a couple different ways to get count to work
            } else if ((p1Choice.equals("paper")) && (p2Choice.equals("rock"))) {
                p1Wins++;
                System.out.println("Player 1 Wins");
            } else if ((p1Choice.equals("rock")) && (p2Choice.equals("scissors"))) {
                p1Wins = p1.setWins();
                System.out.println("Player 1 Wins");
            } else if ((p1Choice.equals("scissors")) && (p2Choice.equals("rock"))) {
                p2Wins = p2.setWins();
                System.out.println("Player 2 Wins");
            } else if ((p1Choice.equals("scissors")) && (p2Choice.equals("paper"))) {
                p1Wins = p1.setWins();
                System.out.println("Player 1 Wins");
            } else if ((p1Choice.equals("paper")) && (p2Choice.equals("scissors"))) {
                p2Wins = p2.setWins();
                System.out.println("Player 2 Wins");
            }
            if (p1Choice == p2Choice) {
                draw++;
                System.out.println("\n\t\t\t Draw \n");
            }
            numberOfPlayedRounds++;
            if ((p1.getTotalWins() >= 3) || (p2.getTotalWins() >= 3)) {
                gameWon = true;
                System.out.println("GAME ENDS");
            }
            System.out.println();
        } while (gameWon != true);

         */
    }
}
