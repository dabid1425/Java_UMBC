

public class Ocean
{
	private Boat[] myboats=new Boat[5];
	private int[][] grid=new int[10][10];
	private int boatsplaced=0;
	
	Ocean()
	{
		for(int pos=0;pos<10;pos++)
		{
			for(int pos2=0; pos2<10; pos2++)
				grid[pos][pos2]=-1;
		}
	}
	
	public void placeAllBoats()
	{
		int xrand;
		int yrand;
		int rand;
		int pos=0;
		String name;
		String direction;
		Position mypos;
		
		while(pos<5)
		{
			if(pos==0)
				name="Aircraft Carrier";
			else if(pos==1)
				name="Battleship";
			else if(pos==2)
				name="Cruiser";
			else if(pos==3)
				name="Submarine";
			else
				name="Destroyer";
			rand=(int)(2*Math.random());
			if(rand==1)
				direction="vertical";
			else
				direction="horizontal";
			xrand=(int)(10*Math.random());
			yrand=(int)(10*Math.random());
			mypos=new Position(xrand,yrand);
			try {
				this.placeBoat(name,direction,mypos);
			} catch(Exception e) {
				pos--;
			}
			pos++;
		}
	}	
	public void placeBoat(String boatName, String direction, Position pos) throws Exception
	{
		Boat myboat = new Boat(boatName, pos, direction);
		if(pos.rowIndex()<0||pos.columnIndex()<0)
			throw new Exception ("out of grid");
		else
		{
			if(direction.equals("vertical"))
			{
				if(myboat.size()+pos.rowIndex()>10)
					throw new Exception ("out of grid");
				for(int pos1=pos.rowIndex();pos1<myboat.size()+pos.rowIndex();pos1++)
				{
					if(grid[pos1][pos.columnIndex()]!=-1)
						throw new Exception ("overlapping");
				}
				for(int pos1=pos.rowIndex();pos1<myboat.size()+pos.rowIndex();pos1++)
				{
					grid[pos1][pos.columnIndex()]=boatsplaced;
				}
			}
			else
			{
				if(myboat.size()+pos.columnIndex()>10)
					throw new Exception ("out of grid");
				for(int pos1=pos.columnIndex();pos1<myboat.size()+pos.columnIndex();pos1++)
				{
					if(grid[pos.rowIndex()][pos1]!=-1)
						throw new Exception("overlapping");
				}
				for(int pos1=pos.columnIndex();pos1<myboat.size()+pos.columnIndex();pos1++)
				{
					grid[pos.rowIndex()][pos1]=boatsplaced;
				}
			}
			myboats[boatsplaced]=myboat;
			boatsplaced++;
		}
	}
	
	public void shootAt(Position pos)
	{
		if(grid[pos.rowIndex()][pos.columnIndex()]!=-1)
		{
			myboats[grid[pos.rowIndex()][pos.columnIndex()]].hit(pos);
		}
	}
	
	public boolean hit(Position pos)
	{
		if(grid[pos.rowIndex()][pos.columnIndex()]!=-1)
			return myboats[grid[pos.rowIndex()][pos.columnIndex()]].isHit(pos);
		else
			return false;
	}
	
	public char boatInitial(Position pos)
	{
		if(grid[pos.rowIndex()][pos.columnIndex()]!=-1)
			return myboats[grid[pos.rowIndex()][pos.columnIndex()]].abbreviation();
		else
			return ' ';
	}
	
	public String boatName(Position pos)
	{
		if(grid[pos.rowIndex()][pos.columnIndex()]!=-1)
			return myboats[grid[pos.rowIndex()][pos.columnIndex()]].name();
		else
			return "";
	}
	
	public boolean sunk(Position pos)
	{
		if(grid[pos.rowIndex()][pos.columnIndex()]!=-1)
			return myboats[grid[pos.rowIndex()][pos.columnIndex()]].sunk();
		else
			return false;
	}
	
	public boolean allSunk()
	{
		for(int pos=0; pos<boatsplaced; pos++)
		{
			if(myboats[pos].sunk()==false)
				return false;
		}
		return true;
	}
}	
