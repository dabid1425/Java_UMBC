

public class PlayerEvaluator {

	/**
	 * @param args
	 */
	private int maxTurns=0;
	private int minTurns=100;
	private int totalTurns;
	private int totalRuns;
	PlayerEvaluator(BattleshipPlayer newDanAbidStrategy,int runs){
		int turns;
		totalRuns=runs;
		for(int i=0;i<runs;i++){
			newDanAbidStrategy.startGame();
			BattleshipGame newGame= new BattleshipGame(newDanAbidStrategy);
			turns=newGame.play();
			if(turns>maxTurns){
				maxTurns=turns;
			}if(turns<minTurns){
				minTurns=turns;
			}
			totalTurns+=turns;
		}
	}
	public int maxTurns(){
		return maxTurns;
	}
	public int minTurns(){
		return minTurns;
	}
	public float averageTurns(){
		float averageTurns=(float)totalTurns/(float)totalRuns;
		return averageTurns;
	}
	public static void main(String[] args) {
	}

}

