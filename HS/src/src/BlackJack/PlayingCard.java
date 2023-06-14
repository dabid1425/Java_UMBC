
import java.util.Scanner;


public class PlayingCard {

	/**
	 * @param args
	 */
	private int suit;
	private int value;
	private static boolean random=true;
	public static  final int ACE=1;
	public static final int KING=13;
	public static final int QUEEN=12;
	public static final int JACK=11;
	public static final int HEARTS=1;
	public static final int SPADES=2;
	public static final int CLUBS=3;
	public static final int DIAMONDS=4;
	PlayingCard(int suitnum, int valuenum){
		suit=suitnum;
		value=valuenum;
	}
	PlayingCard(){
		int lowNum=1;
		int highNum=4;
		int newNum=1;
		int SecondNum=13;
		String var=null;
		if(random==true){
			suit=(int)((highNum - lowNum + 1)* Math.random() + lowNum);
			value=(int)((SecondNum - newNum + 1)* Math.random() + newNum);
		}else{
			Scanner input= new Scanner(System.in);
			while(suit==0){
				System.out.println("Enter a Suit");
				var=input.next();
				String first=var.toLowerCase();
				if(0==first.compareTo("hearts")){
					suit=1;
				}else if(0==first.compareTo("spades")){
					suit=2;
				}else if(0==first.compareTo("clubs")){
					suit=3;
				}else if(0==first.compareTo("diamonds")){
					suit=4;
				}
			}

			System.out.println("Enter a Face Value");
			value=input.nextInt();
		}
	}
	public int getSuit(){
		return suit;

	}
	public int getValue(){
		return value;
	}
	public String toString(){
		String card;
		String newValue = null;
		String newSuit = null;
		if(suit==1){
			newSuit="HEARTS";
		}else if (suit==2){
			newSuit="SPADES";
		}else if (suit==3){
			newSuit="CLUBS";
		}else if(suit==4){
			newSuit="DIAMONDS";
		}if(value==1){
			newValue="Ace";
		}else if(value==13){
			newValue="King";
		}else if(value==12){
			newValue="Queen";
		}else if(value==11){
			newValue="Jack";
		}else{
			newValue=""+value;
		}
		card= newValue+" of "+ newSuit;
		return card;

	}
	public static void setRandom(boolean input ){
		random=input;
	}
	public static void main(String[] args) {

	}

}
