 
public class DanAbidStrategyTester {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		//BattleshipPlayer newDanAbidStrategy=new BattleshipPlayer();
		//BattleshipGame game=new BattleshipGame(newDanAbidStrategy);
		//game.play();
		DanAbidStrategy newDanAbidStrategy=
			new DanAbidStrategy();
		int runs=1000;
		PlayerEvaluator newPlayerEvaluator=new 
		       PlayerEvaluator(newDanAbidStrategy,runs);
		System.out.println("Max: "+newPlayerEvaluator.maxTurns());
		System.out.println("Min: "+newPlayerEvaluator.minTurns());
		System.out.println("Average: "+newPlayerEvaluator.averageTurns());
	}

}


