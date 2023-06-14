

import java.io.*;

public class BattleshipPlayer extends Strategy
{
	protected boolean construct=true;
	protected BattleshipGrid mygrid;
	private String myname;
	
	public void startGame()
	{
		if(construct){
			String name;
			System.out.print("Enter your name: ");
			try 
			{
   				BufferedReader buff = new BufferedReader(new InputStreamReader(System.in));
   				name = buff.readLine();
			} 
			catch(IOException e){name = "";}
			System.out.println("Welcome To Battleship, " + name + ".");
			myname = name;
		}
		mygrid=new BattleshipGrid();
	}
	
	public String author() {return myname;}
	public String playerName() {return myname + "\'s Strategy";}
	
	public Position shoot()
	{
		Position mypos=new Position(0,0);
		char mychar;
		int myint;
		String coords;
		int cont = 0;
		while(cont == 0)
		{
			System.out.println("Enter the coordinates to shoot at (ex. c-4 needs to be lowercase): ");
			try 
			{
   				BufferedReader buff = new BufferedReader(new InputStreamReader(System.in));
   				coords = buff.readLine();
			} 
			catch(IOException e){coords = "";}
			if(coords.length() == 3 || coords.length() == 4)
			{
				mychar = coords.charAt(0);
				myint = ((int)coords.charAt(2)) - 48;
				if((myint == 1) && (coords.length()==4))
				{
					myint = (myint*10) + ((int)coords.charAt(3))-48;
					System.out.println(myint);
				}
				if(((int)mychar>=97&&(int)mychar<=106)&&myint>0&&myint<11)
				{	
					mypos=new Position(mychar,myint);
					cont=1;
				}
				else
					System.out.println("Invalid Coordinates");
			}
			else
				System.out.println("Incorrect Format for Coordinates");
		}
		return mypos;
	}
	
	protected void updateGrid(Position mypos, boolean hit, char initial)
	{
		mygrid.shotAt(mypos,hit,initial);
	}
	
	public void updatePlayer(Position mypos, boolean hit,char initial,String boatname,boolean sunk,boolean gameover,boolean toomanyturns,int turns)
	{
		updateGrid(mypos,hit,initial);
		System.out.println("  1 2 3 4 5 6 7 8 9 10");
		for(int pos=1;pos<11;pos++)
		{
			System.out.print((char)(pos+64));
			for(int pos2=1;pos2<11;pos2++)
			{
				System.out.print(" ");
				Position mypos2= new Position(pos-1,pos2-1);
				if(mygrid.hit(mypos2)==true)
					System.out.print(mygrid.boatInitial(mypos2));
				else if(mygrid.miss(mypos2)==true)
					System.out.print("*");
				else
					System.out.print(".");
			}
			System.out.println("");
		}
		System.out.println("");
		System.out.print("#"+turns+" The shot at "+ mypos.row()+"-"+mypos.column()+" was a ");
		if(hit==false)
			System.out.println("miss");
		else
		{
			System.out.print("hit on the "+boatname);
			if(sunk==false)
				System.out.println("");
			else
			{
				System.out.print(" and sunk it");
				if(gameover==true)
					System.out.println(" and ended the game");
			}
		}
		System.out.println(".");
	}


}
