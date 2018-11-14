import java.util.Scanner;

public class testApp { //Test TicTacToe logic by playing on a single console
	private Board myBoard;
	private gameStateTest gameState;
	private Status currPlayer;
	private static Scanner input = new Scanner(System.in);
	
	public testApp() {
		myBoard = new Board();
		
		startGame();
		
		do {
			playerMove(currPlayer);
			checkGameState(currPlayer);
			if (gameState == gameStateTest.CROSSWIN) {
				System.out.println("Cross won");
			} else if (gameState == gameStateTest.CIRCLEWIN){
				System.out.println("Circle won");
			} else if (gameState == gameStateTest.DRAW) {
				System.out.println("It's a draw");
			}
			
			currPlayer = (currPlayer == Status.CROSS) ? Status.CIRCLE : Status.CROSS; // if currplayer is cross, it is circle's turn, otherwise, cross' turn 
		} while (gameState == gameStateTest.PLAYING);
	}
	
	public void startGame() {
		myBoard.startNew();
		currPlayer = Status.CROSS;
		gameState = gameStateTest.PLAYING;
	}
	
	public void playerMove (Status currStatus) { //A player moves based on their assigned Piece (status)
		boolean turnEnd = false;
		do {
			if (currStatus == Status.CROSS) {
				System.out.println("Player 'X', enter your coordinates (row[0 - 2], col[0 - 2]): ");
			} else {
				System.out.println("Player 'O', enter your coordinates (row[0 - 2], col[0 - 2]): ");
			}
			int row = input.nextInt();
			int col = input.nextInt();
			if (row >= 0  && row < Board.maxRows && col >= 0 && col < Board.maxCol && myBoard.board[row][col].content == Status.EMPTY) {
				myBoard.board[row][col].content = currStatus;
				myBoard.currRow = row;
				myBoard.currCol = col;
				turnEnd = true;
			} else{
				System.out.println("Invalid move");
			}
		} while (!turnEnd); //
	}
	
	public void checkGameState(Status currStatus) {
		if (myBoard.checkWin(currStatus)) { //if there's a win on the board do this
			gameState = (currStatus == Status.CROSS) ? gameState.CROSSWIN : gameState.CIRCLEWIN; //if currState/turn is CROSS, CROSS WINS; else, CIRCLE WINS
		} else if (myBoard.checkDraw()) {
			gameState = gameStateTest.DRAW;
		}
	}
	
	public static void main(String[] args) {
		new testApp();
	}

}
