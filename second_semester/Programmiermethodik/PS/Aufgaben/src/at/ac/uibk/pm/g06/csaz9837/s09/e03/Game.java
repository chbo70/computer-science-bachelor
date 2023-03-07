package at.ac.uibk.pm.g06.csaz9837.s09.e03;

public class Game {
    private Player player1;
    private Player player2;
    private int round;
    private int draws;

    public Game(Player player1, Player player2) {
        this.player1 = player1;
        this.player2 = player2;
    }

    public void play() {
        do {
            printGameStatus(player1.choice(), player2.choice());
            gameLogic(player1.choice(), player2.choice());
            round++;
        } while (playingCondition());
        getWinner();
    }

    private void printGameStatus(String player1Choice, String player2Choice) {
        System.out.println("***** ROUND: " + round + " *********************\n");
        System.out.println("Number of Draws: " + draws + "\n");
        System.out.println("Player 1: " + player1Choice + "\t" + player1.getTotalWins() + " total wins");
        System.out.println("Player 2: " + player2Choice + "\t" + player2.getTotalWins() + " total wins");
    }

    private void gameLogic(String player1Choice, String player2Choice) {
        if (player1Choice.equals(player2Choice)) {
            System.out.println("Draw!");
            draws++;
        } else if (player1Choice.equals("rock") && player2Choice.equals("scissors")) {
            System.out.println("Player 1 wins the round!\n");
            player1.setWins();
        } else if (player1Choice.equals("paper") && player2Choice.equals("rock")) {
            System.out.println("Player 1 wins the round!\n");
            player1.setWins();
        } else if (player1Choice.equals("scissors") && player2Choice.equals("paper")) {
            System.out.println("Player 1 wins the round!\n");
            player1.setWins();
        } else {
            System.out.println("Player 2 wins the round!\n");
            player2.setWins();
        }
    }
    private void getWinner() {
        System.out.println("***** RESULT *********************\n");
        if (player1.getTotalWins() > player2.getTotalWins()) {
            System.out.println("Player 1 won the game!");
        } else if (player1.getTotalWins() < player2.getTotalWins()) {
            System.out.println("Player 2 won the game!");
        } else {
            System.out.println("It's a tie!");
        }
    }

    private boolean playingCondition() {
        return player1.getTotalWins() < 3 && player2.getTotalWins() < 3;
    }
}

