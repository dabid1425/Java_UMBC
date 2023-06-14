




public class BlackjackDealer {

	/**
	 * @param args
	 */
	public static double playBlackjack(BlackjackPlayer player, int numGames){
		double playersScore=0;
		for(int i=0;i<numGames;i++){
			PlayingCard dealersCards=new PlayingCard();
			BlackjackHand dealersHand=new BlackjackHand();
			dealersHand.addCard(dealersCards);
			PlayingCard playersCardOne=new PlayingCard();
			PlayingCard playersCardTwo=new PlayingCard();
			BlackjackHand playersHand=new BlackjackHand();
			playersHand.addCard(playersCardOne);
			playersHand.addCard(playersCardTwo);
			boolean value;
			do{
				int j=i+1;
				System.out.println("Game # " +j);
				value=player.hit(dealersHand, playersHand);
				if(value==true){
					playersHand.addCard(new PlayingCard());
				}
			}while(value==true&&playersHand.handValue()<=21);
			if(playersHand.handValue()>21){
				player.playerBusts(playersHand);
			}else{
				while(dealersHand.handValue()<17){
					dealersHand.addCard(new PlayingCard());
					player.dealerHit(dealersHand);
				}
				if(dealersHand.handValue()>21){
					player.dealerBusts(dealersHand);
					playersScore++;
				}else if(playersHand.handValue()>dealersHand.handValue()){
					player.playerWins(playersHand, dealersHand);
					playersScore++;
				}else if(playersHand.handValue()==dealersHand.handValue()){
					player.playerTies(playersHand, dealersHand);
					playersScore+=.5;
				}else if(dealersHand.handValue()>playersHand.handValue()){
					player.dealerWins(dealersHand, playersHand);
				}


			}
		}
		playersScore/=numGames;
		return playersScore;

	}
	public static void main(String[] args) {

	}

}
