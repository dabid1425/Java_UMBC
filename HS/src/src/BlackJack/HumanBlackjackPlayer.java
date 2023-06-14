

import java.util.Scanner;


public class HumanBlackjackPlayer extends BlackjackPlayer {

	/**
	 * @param args
	 */
	HumanBlackjackPlayer(){

	}
	public static void main(String[] args) {
	}

	@Override
	public void dealerBusts(BlackjackHand dealerHand) {
		System.out.println("The Dealer busts");
		for(int i=0;i<dealerHand.numberOfCards();i++){
			System.out.println("The dealer had: " +dealerHand.nthCard(i));
		}

	}

	@Override
	public void dealerHit(BlackjackHand dealerHand) {
		System.out.println("The Dealer hit");


	}

	@Override
	public void dealerWins(BlackjackHand dealerHand, BlackjackHand playerHand) {
		System.out.println("The Dealer Wins");
		for(int i=0;i<dealerHand.numberOfCards();i++){
			System.out.println("The dealer had: " +dealerHand.nthCard(i));
		}
		for(int n=0;n<playerHand.numberOfCards();n++){
			System.out.println("You had: "+playerHand.nthCard(n));
		}

	}

	@Override
	public boolean hit(BlackjackHand dealerHand, BlackjackHand playerHand) {
		boolean hit = false;
		String answer;
		Scanner input= new Scanner(System.in);
		do{
			for(int i=0;i<dealerHand.numberOfCards();i++){
				System.out.println("The dealer has: " +dealerHand.nthCard(i));
			}
			for(int n=0;n<playerHand.numberOfCards();n++){
				System.out.println("You Have: "+playerHand.nthCard(n));
			}

			System.out.println("Do you want to hit?");
			answer=input.next();
			if(answer.equals("yes")){
				hit=true;
				return hit;
			}else if(answer.equals("no")){
				hit=false;
				return hit;
			}else{
				System.out.println("I'm sorry that is not one of the answers");
			}
		}while(!answer.equals("yes")||!answer.equals("no"));
		return hit;
	}

	@Override
	public void playerBusts(BlackjackHand playerHand) {
		System.out.println("You busted");
		for(int n=0;n<playerHand.numberOfCards();n++){
			System.out.println("You had: "+playerHand.nthCard(n));
		}
	}

	@Override
	public void playerTies(BlackjackHand playerHand, BlackjackHand dealerHand) {
		System.out.println("You and the Dealer tied");
		for(int n=0;n<playerHand.numberOfCards();n++){
			System.out.println("You had: "+playerHand.nthCard(n));
		}
		for(int i=0;i<dealerHand.numberOfCards();i++){
			System.out.println("The dealer had: " +dealerHand.nthCard(i));
		}

	}

	@Override
	public void playerWins(BlackjackHand playerHand, BlackjackHand dealerHand) {
		System.out.println("You win");
		for(int n=0;n<playerHand.numberOfCards();n++){
			System.out.println("You: "+playerHand.nthCard(n));
		}
		for(int i=0;i<dealerHand.numberOfCards();i++){
			System.out.println("The dealer had: " +dealerHand.nthCard(i));
		}
	}

}
