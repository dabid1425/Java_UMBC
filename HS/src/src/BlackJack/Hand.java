
import java.util.ArrayList;


public class Hand {

	/**
	 * @param args
	 */
	Hand(){
		myPlayingCardHand=new ArrayList<PlayingCard>();
		
	}
		
		public int numberOfCards(){
		int numberofCards=myPlayingCardHand.size();
		return numberofCards;
		}
		
	public PlayingCard nthCard(int n){
		PlayingCard nthCard;
		if(n>=myPlayingCardHand.size()){
			nthCard=null;
		}else{
			nthCard=myPlayingCardHand.get(n);
		}
		return nthCard ;
	}
	public void print(){
		int length=myPlayingCardHand.size();
		if(length==0){
			System.out.println("There are no cards in your Hand");
		}else{
			for(PlayingCard i: myPlayingCardHand){
			System.out.println(i);
		}
		
		}
	}
	public void addCard(PlayingCard myPlayingHand){
		myPlayingCardHand.add(myPlayingHand);
	}
	public static void main(String[] args) {
		

	}
	private ArrayList<PlayingCard>myPlayingCardHand;
}
