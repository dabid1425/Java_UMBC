


public abstract class BlackjackPlayer {

	/**
	 * @param args
	 */ 
	public abstract boolean hit(BlackjackHand dealerHand, BlackjackHand playerHand);
	public abstract void dealerHit(BlackjackHand dealerHand);
	public abstract void playerBusts(BlackjackHand playerHand);
	public abstract void playerTies(BlackjackHand playerHand, BlackjackHand dealerHand);
	public abstract void playerWins(BlackjackHand playerHand, BlackjackHand dealerHand);
	public abstract void dealerBusts(BlackjackHand dealerHand);
	public abstract void dealerWins(BlackjackHand dealerHand, BlackjackHand playerHand);

	public static void main(String[] args) {


	}

}
