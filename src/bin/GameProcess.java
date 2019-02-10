package bin;

import java.util.Iterator;
import java.util.Scanner;

import javax.swing.JTextArea;

public class GameProcess {
	private int attempt;
	private GameSetup setup = new GameSetup();

	public void run() {
		introPhase();
		shootingPhase();
		resultPhase();
	}

	// Game intro
	private void introPhase() {
		System.out.println("===BATTLESHIP v1.0===");
		System.out.println(
				"There are 4 battleships lurking around, your mission is to terminate all of them using least missles. Enter the locations you want to shoot.");
		System.out.println("===ENJOY THE GAME===");
		printPlayerMap();
	}

	// Check for HIT, MISS, SUNK
	private void shootingPhase() {
		attempt=1;
		
		while (setup.shipList.size() != 0) {
			System.out.println("-----------------------");
			System.out.println("Attempt: " + attempt);
			System.out.println("Battleship remaining " + setup.shipList.size());
			
			//Input shooting location
			System.out.print("Input X: ");
			Scanner sc = new Scanner(System.in);
			int xInput = sc.nextInt()-1;
			System.out.print("Input Y: ");
			sc = new Scanner(System.in);
			int yInput = sc.nextInt()-1;
			
			//check for MISS HIT SUNK
			int targetValue = setup.board[xInput][yInput];
			if (targetValue == 0) {
				System.out.println("MISS!");
				if (setup.playerMap[xInput][yInput] != "$ ")
					setup.playerMap[xInput][yInput] = "X ";
			}
			else {
				System.out.println("HIT!");
				setup.board[xInput][yInput] = 0;
				setup.playerMap[xInput][yInput] = "$ ";
				
				//Check for sunk ship and remove out of array
				Iterator<Ship> itr = setup.shipList.iterator();
				while (itr.hasNext()) {
					Ship s = itr.next();
					if (s.getNumber() == targetValue) {
						s.setLength(s.getLength() - 1);
						if (s.getLength() == 0) {
							System.out.println("COMMANDER! YOU SUNK " + s.getName());
							itr.remove();
						}
					}
				}
			}
			printPlayerMap();
			attempt++;
		}
	}

	// Print player result
	private void resultPhase() {
		int bonus =setup.totalLength;
		float result = (float) ((36-attempt+bonus) * 100 / 36);
		String rank;

		System.out.println("CONGRATULATION! YOU SUNK ALL BATTLESHIPS!");
		System.out.println("===YOUR RESULT===");
		System.out.println("Total attempt: " + attempt);
		System.out.println("Score: " + result + "%");

		if (result > 65)
			rank = "S";
		else if (result>55)
			rank="S-";
		else if (result > 45)
			rank = "A";
		else if (result > 35)
			rank = "B";
		else
			rank = "C";

		System.out.println("Rank: " + rank);
		System.out.println("===GAME OVER===");
	}

	// Print visual map
	public void printPlayerMap() {
		for (int lIndex = 0; lIndex < setup.B_L; lIndex++) {
			for (int hIndex = 0; hIndex < setup.B_H; hIndex++)
				System.out.print(setup.playerMap[lIndex][hIndex]);
			System.out.println();
		}
	}

}
