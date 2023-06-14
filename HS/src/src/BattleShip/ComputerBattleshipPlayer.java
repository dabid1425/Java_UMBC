

public class ComputerBattleshipPlayer extends BattleshipPlayer
{
	ComputerBattleshipPlayer() 
	{
		construct = false;
	}
	
	public void updatePlayer(Position mypos, boolean hit, char initial, 
							 String boatname, boolean sunk, boolean gameover, 
							 boolean toomanyturns, int turns)
	{
		updateGrid(mypos,hit,initial);
	}

	public String author()
	{
		return "Computer";
	}
	public String name()
	{
		return "Computer Strategy";
	}
	
	public Position shoot()
	{
		int stop=0;
		Position mypos=new Position(0,0);
		while(!mygrid.empty(mypos))
		{
			int xrand=(int)(10*Math.random());
			int yrand=(int)(10*Math.random());
			mypos = new Position(xrand,yrand);
		}
		return mypos;
	}
}
