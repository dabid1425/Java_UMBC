

/*Eric Bell
 *Period 2 
 *AOOD
 *Battleship Project 2*/
public class Position {
	private char row;
	private int col;
	Position(char row, int col){
		this.row = row;
		this.col = col;
	}
	Position (int row, int col){
		this.row = (char)(row+97);/*corrects to value*/
		this.col = col+1;
	}
	public char row(){
		return row;
	}
	public int column(){
		return col;
	}
	public int rowIndex(){
		return row - 'a';
	}
	public int columnIndex(){
		return col-1;
	}
	public String toString(){
		return row + "-" + col;
	}
}
