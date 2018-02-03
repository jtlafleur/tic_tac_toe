import java.util.Scanner;

public class Tic_Tac_Toe {
	public static final String[] players = { " X ", " O ", " A ", " B ", " C ", " D ", " E ", " F ", " G ", " H ",
			" I ", " J ", " K ", " L ", " M ", " N ", " P ", " Q ", " R ", " S ", " T ", " U ", " V ", " W ", " Y ",
			" Z " };
	public static final int numPlayers = 2;
	public static int gameState;

	public static final int ROWS = 3, COLS = 3;
	public static String[][] board; 

	public static int TURN; //Player whose turn it is

	// Scanner takes in user input
	public static Scanner sc = new Scanner(System.in);

	public static void initGame() {
		/*
		 * do { System.out.print("How many players? (Choose between 2 and 26) ");
		 * numPlayers = sc.nextInt(); } while(numPlayers < 2 || numPlayers > 26);
		 * 
		 * do {
		 * System.out.print("How big should the game be? (Choose between 3 and 999) ");
		 * ROWS = COLS = sc.nextInt(); } while(ROWS < 3 || ROWS > 999);
		 */

		board = new String[ROWS][COLS];
		for (int row = 0; row < ROWS; row++) {
			for (int col = 0; col < COLS; col++) {
				board[row][col] = "   ";
			}
		}

		displayBoard();
		gameState = numPlayers + 2;
		TURN = 0;

	}

	public static void playerMove(int turn) {
		do {
			System.out.print("Player \"" + players[turn] + "\" enter your move.\n(row[1-" + Integer.toString(ROWS)
					+ "] column[1-" + Integer.toString(COLS) + "])");

			int row = sc.nextInt() - 1;
			int col = sc.nextInt() - 1;

			if ((row >= 0 && row < ROWS) && (col >= 0 && col < COLS) && (board[row][col] == "   ")) {
				board[row][col] = players[turn];
				isWin(turn, row, col);
				if(isTie() && gameState == numPlayers + 2)
					gameState = numPlayers + 1;
				displayBoard();
				break;
			} else {
				System.out.println("Move not valid at" + Integer.toString(row+1)+", " + Integer.toString(col+1) +  ". Try again");
			}
		} while (true);
	}

	public static boolean isTie() {
		for (int row = 0; row < ROWS; row++)
			for (int col = 0; col < COLS; col++)
				if (board[row][col] == "   ")
					return false; // There is an empty space
		return true; // There aren't any empty spaces
	}

	public static void isWin(int turn, int row, int col) {
		// TODO: Check if the player that just moved won the game diagnolly, vertically
		// or horizontally, total of 4 ways to win
		if (board[row][0] == players[turn]         // 3-in-the-row
                && board[row][1] == players[turn]
                && board[row][2] == players[turn]
           || board[0][col] == players[turn]      // 3-in-the-column
                && board[1][col] == players[turn]
                && board[2][col] == players[turn]
           || row == col            // 3-in-the-diagonal
                && board[0][0] == players[turn]
                && board[1][1] == players[turn]
                && board[2][2] == players[turn]
           || row + col == 2  // 3-in-the-opposite-diagonal
                && board[0][2] == players[turn]
                && board[1][1] == players[turn]
                && board[2][0] == players[turn]) {
			gameState = turn;
		}
	}
	
	public static void endGame() {
		if(gameState == numPlayers+1)
			System.out.println("It's a tie game");
		else {
			System.out.println("Player \"" + players[gameState] + "\" won!");
		}
	}
	
	public static void displayBoard() {
		// TODO: Print the board
		String str1 = "  ---";
		for (int a = 1; a < COLS; a++)
			str1 += "+---";

		for (int i = 0; i < ROWS; i++) {
			if (i == 0) {
				String str2 = "  ";
				for (int a = 1; a <= COLS; a++)
					str2 += " " + Integer.toString(a) + "  ";
				System.out.println(str2);
			}

			System.out.println(Integer.toString(i + 1) + " " + String.join("|", board[i]));

			if (i != ROWS - 1) {
				System.out.println(str1);
			}
		}
	}

	public static void main(String[] args) {
		initGame();

		do {
			playerMove(TURN);
			TURN = (TURN + 1) % numPlayers;
		} while (gameState == numPlayers + 2);
		endGame();
	}
}
