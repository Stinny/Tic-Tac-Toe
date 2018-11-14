
public class Board {
	public static int maxRows = 3, maxCol = 3;
	public Cell[][] board;
	public int currRow, currCol;

	public Board() {
		board = new Cell[maxRows][maxCol];
		for (int row = 0; row < maxRows; row++) { // Initialize a 3x3 array of
													// CELLS with starting enum
													// status of EMPTY
			for (int col = 0; col < maxCol; col++) {
				board[row][col] = new Cell(row, col);
			}
		}
	}

	public void startNew() {
		for (int row = 0; row < maxRows; row++) {
			for (int col = 0; col < maxCol; col++) {
				board[row][col].setEmpty();
			}
		}
	}

	public boolean checkDiagonalCase(Status currContent) {
		
		if (board[1][1].content == currContent) {
			if ((board[0][0].content == currContent && board[2][2].content == currContent) // if
																								// diagonal
																								// from
																								// [0][0]
																								// to
																								// [2][2]
					|| (board[0][2].content == currContent && board[2][0].content == currContent)) {// or
																										// diagonal
																										// from
																										// [0][2]
																										// to
																										// [2][0]
				return true;
			}
		} 
//		else if (board[1][1].content == Status.CROSS) {
//			if ((board[0][0].content == Status.CROSS && board[2][2].content == Status.CROSS) // if
//																								// diagonal
//																								// from
//																								// [0][0]
//																								// to
//																								// [2][2]
//					|| (board[0][2].content == Status.CROSS && board[2][0].content == Status.CROSS)) {// or
//																										// diagonal
//																										// from
//																										// [0][2]
//																										// to
//																										// [2][0]
//				return true;
//			}
//		}

		return false;
	}

	public boolean checkHorizontalCase(Status currStatus) {

		for (int col = 0; col < maxCol; col++) {
			int row = 0;
			if (board[row][col].content == currStatus) {
				row++;
				if (board[row][col].content == currStatus) {
					row++;
					if (board[row][col].content == currStatus) {
						return true;
					}
				}
			} 
//			else if (board[row][col].content == Status.CROSS) {
//				row++;
//				if (board[row][col].content == Status.CROSS) {
//					row++;
//					if (board[row][col].content == Status.CROSS) {
//						return true;
//					}
//				}
//			}
		}
		return false;
	}

	public boolean checkVerticalCase(Status currStatus) {

		for (int row = 0; row < maxRows; row++) {
			int col = 0;
			if (board[row][col].content == currStatus) {
				col++;
				if (board[row][col].content == currStatus) {
					col++;
					if (board[row][col].content == currStatus) {
						return true;
					}
				}
			}
//			else if (board[row][col].content == Status.CROSS) {
//				col++;
//				if (board[row][col].content == Status.CROSS) {
//					col++;
//					if (board[row][col].content == Status.CROSS) {
//						return true;
//					}
//				}
//			}
		}
		return false;
	}

	public void setCoordX(int row, int col) {
		board[row][col].setCross();
	}

	public void setCoordO(int row, int col) {
		board[row][col].setCircle();
	}

	public boolean checkDraw() {
		for (int row = 0; row < maxRows; ++row) {
			for (int col = 0; col < maxCol; ++col) {
				if (board[row][col].content == Status.EMPTY) {
					return false; // if there is an empty cell, there can't be a
									// draw
				}
			}
		}
		return true; // if there is not a single empty cell, all spaces have
						// been filled and no one has won yet
	}

	public boolean checkWin(Status currStatus) {
		if (this.checkDiagonalCase(currStatus) || this.checkVerticalCase(currStatus)  || this.checkHorizontalCase(currStatus)) {
			return true;
		} else
			return false;
	}

	// if checkWin() returns true the game should end and the current player's
	// turn is the winner
	// if false continue whatever loop keeping the game running

}
