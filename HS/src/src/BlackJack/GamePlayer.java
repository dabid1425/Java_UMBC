
public abstract class GamePlayer{
	public abstract double playGame(Strategy strat) throws StrategyCrashedException;
	
	public double[] playAllStrategies(Strategy[] stratList) throws StrategyCrashedException{
		double[] result = new double [stratList.length];
		for (int x = 0; x < stratList.length; x++){
			try {
				if (!stratList[x].crashed()){
					result[x] = playGame(stratList[x]);
				}
			}
			catch (Exception e){
				e.printStackTrace();
				//System.out.println("\n\n\n\n\n"+x+"\n\n\n\n\n");
				throw new StrategyCrashedException(x);
			}
		}
		return result;
	}
}
