
public class Cell {
	private int row;
	private int col;
	Status content; 
	
	public Cell(int row, int col) {
		this.row = row;
		this.col = col;
	}
	
	public void setCircle() {
		this.content = Status.CIRCLE;
	}
	
	public void setCross() {
		this.content = Status.CROSS;
	}
	
	public void setEmpty() {
		this.content = Status.EMPTY;
	}
	
	
}
