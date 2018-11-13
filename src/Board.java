
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
	
	private void setCircle(Cell[][] board) {
		
	}
	
	private int checkStatus() {
		//check horizontal 
		int row, col = 0;
		
		for(row = 0; row < maxRows; row++){
			for (col = 0; col < maxCol; col++) {
				if(board[row][col].content == Status.CIRCLE) {
				
				}
			}
			
		}
		return 0;
	}
	
	//print board --- jframe/jpanels 
	
}
