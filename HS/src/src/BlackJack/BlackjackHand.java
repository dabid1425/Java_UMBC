


public class BlackjackHand extends Hand {

	/**
	 * @param args
	 */
	private int handValue;
	private boolean soft;
	private boolean aces;
	BlackjackHand(){
		handValue=0;
		soft=false;
	}
	public void addCard(PlayingCard myPlayingHand){
		super.addCard(myPlayingHand);
		this.computeValue();
	}

	public int handValue() {
		return handValue;
	}
	public boolean soft(){
		return soft;

	}

	private void computeValue(){
		handValue=0;
		aces=false;
		soft=false;
		PlayingCard card;
		int numberOfCards=numberOfCards();
		for(int i=0;i<numberOfCards;i++){
			card=nthCard(i);
			if(card.getValue()>=11){
				handValue+=10;
			}else{
				handValue=handValue+card.getValue();
			} if(card.getValue()==1){
				aces=true;
			}
		}if(aces==true&&handValue<12){
			handValue+=10;
			soft=true;
		}

	}
	public static void main(String[] args) {

	}

}
