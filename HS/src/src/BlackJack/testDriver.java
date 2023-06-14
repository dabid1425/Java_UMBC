


public class testDriver {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		int games=7;
		double playerScore;
		HumanBlackjackPlayer player=new HumanBlackjackPlayer();
		playerScore=BlackjackDealer.playBlackjack(player, games);
		System.out.println(playerScore);

	}

}
