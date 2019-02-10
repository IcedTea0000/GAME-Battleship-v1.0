package bin;

import java.util.ArrayList;
import java.util.List;

public class GameSetup {
	public final int B_L = 6;
	public final int B_H = 6;
	public int totalLength;
	public int[][] board;
	public String[][] playerMap = new String[B_L][B_H];
	public List<Ship> shipList = new ArrayList<Ship>();
	private int lIndex, hIndex;

	public GameSetup() {
		genBoard();
		genShips();
		genPlayerMap();
	}

	// generate new 2D board with all 0 numbers
	private void genBoard() {
		board = new int[B_L][B_H];
		for (int lIndex = 0; lIndex < B_L; lIndex++)
			for (int hIndex = 0; hIndex < B_H; hIndex++) {
				board[lIndex][hIndex] = 0;
			}
	}

	// generate player map for GUI
	private void genPlayerMap() {
		for (int lIndex = 0; lIndex < B_L; lIndex++)
			for (int hIndex = 0; hIndex < B_H; hIndex++) {
				playerMap[lIndex][hIndex] = "~ ";
			}
	}

	// Generate 4 ship objects on board & add to shipList
	private void genShips() {
		Ship ship1 = new Ship("MotherBoard", 1);
		genShipPosition(ship1);
		shipList.add(ship1);
		Ship ship2 = new Ship("Predator", 2);
		genShipPosition(ship2);
		shipList.add(ship2);
		Ship ship3 = new Ship("Apocalypse", 3);
		genShipPosition(ship3);
		shipList.add(ship3);
		Ship ship4 = new Ship("ThunderStorm", 4);
		genShipPosition(ship4);
		shipList.add(ship4);
		
		totalLength=ship1.genLength()+ship3.genLength()+ship2.genLength()+ship4.genLength();
	}

	// Generate random position for ship
	private void genShipPosition(Ship ship) {
		hIndex = (int) (Math.random() * B_H);

		// Find available position for ship
		for (;;) {
			lIndex = (int) (Math.random() * B_L);
			if (checkHor(ship) == true) {
				for (int tempX = lIndex; tempX < lIndex + ship.getLength(); tempX++)
					board[tempX][hIndex] = ship.getNumber();
				break;
			} else {
				hIndex = (int) (Math.random() * B_H);
				if (checkVer(ship) == true) {
					for (int tempY = hIndex; tempY < hIndex + ship.getLength(); tempY++)
						board[lIndex][tempY] = ship.getNumber();
					break;
				}
			}
		}
	}

	// Check for obstacle horizontally
	private boolean checkHor(Ship ship) {
		int x = lIndex;
		if (x + ship.getLength() <= B_L) {
			for (; x < lIndex + ship.getLength(); x++)
				if (board[x][hIndex] != 0)
					return false;
		} else
			return false;
		return true;
	}

	// Check for obstacle vertically
	private boolean checkVer(Ship ship) {
		int y = hIndex;
		if (y + ship.getLength() <= B_H) {
			for (; y < hIndex + ship.getLength(); y++)
				if (board[lIndex][y] != 0)
					return false;
		} else
			return false;
		return true;
	}

}
