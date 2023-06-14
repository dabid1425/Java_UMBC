

public class BattleshipGame extends GamePlayer{
	private Ocean ocean;
	private BattleshipPlayer player;
	
	BattleshipGame(BattleshipPlayer myPlayer){
		player = myPlayer;
	}
	
	BattleshipGame(){
	}
	
	public int play(){
		int score = 101;
		try {
			score = (int)playGame((Strategy)player);
		} 
		catch (StrategyCrashedException e) {}
		return score;
	}
	
	public double playGame(Strategy myPlayer) throws StrategyCrashedException{
		ocean = new Ocean();
		ocean.placeAllBoats();
		player = (BattleshipPlayer)myPlayer;
		player.startGame();
		Position pos;
		int x;
		for(x = 0; (ocean.allSunk() == false) && (x < 100) ; x++){
			pos = player.shoot();
			ocean.shootAt(pos);
			player.updatePlayer(pos, ocean.hit(pos), ocean.boatInitial(pos),
								ocean.boatName(pos), ocean.sunk(pos), 
								ocean.allSunk(), false, x);
		}
		return x;
	}
}	
			
			
