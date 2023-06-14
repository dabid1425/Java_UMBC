

public class BattleshipGrid
{
	private int[][] grid = new int[10][10];
	private char[][] initialgrid = new char[10][10];
	
	BattleshipGrid()
	{
		for(int x = 0; x < 10; x++)
		{
			for(int y = 0; y < 10; y++)
			{
				grid[x][y]=0;
				initialgrid[x][y]=' ';
			}
		}
	}
	
	public void shotAt(Position pos, boolean hit, char initial)
	{
		if(hit==true)
		{
			grid[pos.rowIndex()][pos.columnIndex()]=2;
			initialgrid[pos.rowIndex()][pos.columnIndex()]=initial;
		}
		else
			grid[pos.rowIndex()][pos.columnIndex()]=1;
	}
	
	public boolean hit(Position pos)
	{
		if(grid[pos.rowIndex()][pos.columnIndex()]==2)
			return true;
		return false;	
	}
	public boolean miss(Position pos)
	{
		if(grid[pos.rowIndex()][pos.columnIndex()]==1)
			return true;
		return false;
	}
	public boolean empty(Position pos)
	{
		if(grid[pos.rowIndex()][pos.columnIndex()]==0)
			return true;
		return false;
	}
	public char boatInitial(Position pos)
	{
		return initialgrid[pos.rowIndex()][pos.columnIndex()];
	}
}
