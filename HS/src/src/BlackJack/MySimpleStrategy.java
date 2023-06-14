

public class MySimpleStrategy extends BlackjackStrategy  {

	/**
	 * @param args
	 */

	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

	@Override
	boolean hit(int handValue, int dealerHandValue, boolean soft) {
		if(dealerHandValue==11&&handValue<21){
			return true;
		}else if(handValue<17){
			return true;
		}else if(soft==true){
			return true;
		}else{
			return false;
		}
		
	}

}
