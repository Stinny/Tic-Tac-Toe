
public class Board {
	private int maxRows, maxCol = 3;
	private Cell[][] board = new Cell[maxRows][maxCol];
	
	public Board() {
		for (int row = 0; row < maxRows; row++){
			for (int col = 0; col < maxCol; col++) {
				board[row][col] = new Cell(row, col);
			}
		}
	}
	
	private int checkDiagonalCase() {
		int victory = 0;
		if (board[1][1].content == Status.CIRCLE) {
			if ((board[0][0].content == Status.CIRCLE && board[2][2].content == Status.CIRCLE) //if diagonal from [0][0] to [2][2]
					|| (board[0][2].content == Status.CIRCLE && board[2][0].content == Status.CIRCLE)) {// or diagonal from [0][2] to [2][0]
				victory = 1;
			}
		}
		else if (board[1][1].content == Status.CROSS) {
			if ((board[0][0].content == Status.CROSS && board[2][2].content == Status.CROSS) //if diagonal from [0][0] to [2][2]
					|| (board[0][2].content == Status.CROSS && board[2][0].content == Status.CROSS)) {// or diagonal from [0][2] to [2][0]
				victory = -1;
			}
		}
		
		return victory;
	}
	
	private int checkHorizontalCase() {
		
		int victory = 0; //if victory is 0, no one has won yet; if 1, circle wins; if -1, cross wins
		for (int col = 0; col < maxCol; col++) {
			int row = 0;
			if (board[row][col].content == Status.CIRCLE) {
				row++;
				if(board[row][col].content == Status.CIRCLE){
					row++;
					if(board[row][col].content == Status.CIRCLE) {
						victory = 1;
					}
				}
			}
			else if (board[row][col].content == Status.CROSS) {
				row++;
				if(board[row][col].content == Status.CROSS){
					row++;
					if(board[row][col].content == Status.CROSS) {
						victory = -1;
					}
				}
			}
		}
		return victory;
	}
	
	private int checkVerticalCase() {
		int victory = 0; //if victory is 0, no one has won yet; if 1, circle wins; if -1, cross wins
		for (int row = 0; row < maxRows; row++) {
			int col = 0;
			if (board[row][col].content == Status.CIRCLE) {
				col++;
				if(board[row][col].content == Status.CIRCLE){
					col++;
					if(board[row][col].content == Status.CIRCLE) {
						victory = 1;
					}
				}
			}
			else if (board[row][col].content == Status.CROSS) {
				col++;
				if(board[row][col].content == Status.CROSS){
					col++;
					if(board[row][col].content == Status.CROSS) {
						victory = -1;
					}
				}
			}
		}
		return victory;
	}
	
	
	
	
	//print board --- jframe/jpanels 
	
}
