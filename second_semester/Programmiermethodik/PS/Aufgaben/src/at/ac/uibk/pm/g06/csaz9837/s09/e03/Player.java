package at.ac.uibk.pm.g06.csaz9837.s09.e03;

public class Player {
	int totalWins;
	Double d;
	/**
	 * Rando choose rock, paper, or scissors
	 *          */
	public String choice() {
		String choice = "";
		int c = (int) (Math.random() * 3);
		switch (c) {
			case 0 -> choice = ("rock");
			case 1 -> choice = ("paper");
			case 2 -> choice = ("scissors");
		}
		return choice;
	}
	//print d
	public void printD() {
		System.out.println(d);
	}

	public void setWins() {
		totalWins++;
	}

	public int getTotalWins() {
		return totalWins;
	}
}
