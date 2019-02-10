package bin;

import java.util.Iterator;
import java.util.Scanner;

import javax.swing.JTextArea;
import javax.swing.JTextField;

public class GameProcessForGUI {
	private int attempt = 1;
	public GameSetup setup = new GameSetup();

	// Game intro
	public void introPhase(JTextArea textArea) {
		textArea.append("===BATTLESHIP v1.0===\n");
		textArea.append(
				"The ocean is a 6x6 grid. There are 4 battleships lurking around, your mission is to terminate all of them using least missles. Enter the locations you want to shoot.\n");
		textArea.append("===ENJOY THE GAME===\n");
		printPlayerMap(textArea);
	}

	// Check for HIT, MISS, SUNK
	public void shootingPhase(JTextArea textArea, int xInput, int yInput) {
		textArea.append("-----------------------\n");
		textArea.append("Attempt: " + attempt + "\n");
		textArea.append("Battleships remaining: " + setup.shipList.size() + "\n");

		// Input shooting location
		int xTarget = xInput - 1;
		int yTarget = yInput - 1;

		// check for MISS HIT SUNK
		int targetValue = setup.board[xTarget][yTarget];
		if (targetValue == 0) {
			textArea.append("MISS!\n");
			if (setup.playerMap[xTarget][yTarget] != "$ ")
				setup.playerMap[xTarget][yTarget] = "X ";
		} else {
			textArea.append("HIT!\n");
			setup.board[xTarget][yTarget] = 0;
			setup.playerMap[xTarget][yTarget] = "$ ";

			// Check for sunk ship and remove out of array
			Iterator<Ship> itr = setup.shipList.iterator();
			while (itr.hasNext()) {
				Ship s = itr.next();
				if (s.getNumber() == targetValue) {
					s.setLength(s.getLength() - 1);
					if (s.getLength() == 0) {
						textArea.append("COMMANDER! YOU SUNK " + s.getName() + "\n");
						itr.remove();
					}
				}
			}
		}
		printPlayerMap(textArea);
		attempt++;
	}

	// Print player result
	public void resultPhase(JTextArea textArea) {
		int bonus =setup.totalLength;
		float result = (float) ((36-attempt+bonus) * 100 / 36);
		String rank;

		textArea.append("CONGRATULATION! YOU SUNK ALL BATTLESHIPS!\n");
		textArea.append("===YOUR RESULT===\n");
		textArea.append("Total attempt: " + attempt + "\n");
		textArea.append("Score: " + result + "%" + "\n");

		if (result > 65)
			rank = "S";
		else if (result > 55)
			rank = "S-";
		else if (result > 45)
			rank = "A";
		else if (result > 35)
			rank = "B";
		else
			rank = "C";

		textArea.append("Rank: " + rank + "\n");
		textArea.append("===GAME OVER===");
	}

	// Print visual map
	public void printPlayerMap(JTextArea textArea) {
		for (int lIndex = 0; lIndex < setup.B_L; lIndex++) {
			for (int hIndex = 0; hIndex < setup.B_H; hIndex++)
				textArea.append(setup.playerMap[lIndex][hIndex]);
			textArea.append("\n");
		}
	}
}
