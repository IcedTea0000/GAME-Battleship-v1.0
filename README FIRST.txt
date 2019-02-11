===Battleship v1.0===

TO PLAY, RUN BattleshipGUI.java.
----------------------
----------------------
GAME RULE:
The ocean is a 6x6 grid. There are 4 ships with random lengths and positions. Player input the number of row and column to shoot. 
----------------------
----------------------
CONSTRUCTION PROCESS:
1. Generate gameboard as 2D array.
2. Generate 4 ship objects with random lengths and random positions vertically or horizontally so that they dont intersect with each other or go over the board.
	2.1. Generate a random [x][y], check if there is no obstacle horizontally then generate the ship that way. If there is obstacle horizontally but no obstacle vertically then generate the ship that way. If there are obstacles both way then pick a new random [x][y].
3. Set up GameProcess class handling: game's introduction, player's input, check input for HIT MISS SINK, game result printout.
	3.1. For checking ship's statement, put all ships into an ArrayList. If player's input is a HIT, search the value of the ship (from 2D array) in the ArrayList. When find matching, deduce the length of the ship -1. If the ship length=0, remove the ship out of ArrayList and announce SINK. 
4. Design GUI.
5. Connect GUI with backend objects and methods.
6. Test game and fix bugs along the way. 
----------------------
----------------------