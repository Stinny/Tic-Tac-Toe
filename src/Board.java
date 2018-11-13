
public class Board {
	private int maxRows, maxCol = 3;
	private Cell[][] board = new Cell[maxRows][maxCol];
	
	public Board() {
		for (int row = 0; row < maxRows; row++){
			for (int col = 0; col < maxCol; col++) {
				board[row][col] = null;
			}
		}
	}
	
	
	
	//print board --- jframe/jpanels 
	
}
