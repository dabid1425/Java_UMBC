package proj1;

import java.util.LinkedList;
import java.util.ListIterator;

public class Patron {
	private int id;
	private LinkedList<String> books;
	public Patron(int person){
		id=person;
		books=new LinkedList<String>();
	}
	/**
	 * returns the id of the patron 
	 * Preconditions: none 
	 * Postcondition: none
	 * @return id
	 */
	public int getId(){
		return id;
	}
	/**
	 * returns true if the patron has the book
	 * Preconditions: book 
	 * Postcondition: none
	 * @return  true if the patron has the book
	 */
	public boolean hasbook(String book){
		boolean hasbook=false;
		ListIterator<String> itr= books.listIterator();
		while(itr.hasNext()){
			if(itr.next().equals(book)){
				hasbook=true;
			}
		}
		return hasbook;
	}
	/**
	 * adds the book to the patrons list 
	 * Preconditions: book 
	 * Postcondition: none
	 * @return none
	 */
	public  void PatronBorrow(String book){
		books.add(book);
		for(int i=0;i<books.size();i++){
			if(i+1==books.size()){
				System.out.println("Patron "+id+ " has borrowed "+books.get(i));
			}
		}
		
	}
	/**
	 * removes book from patron list 
	 * Preconditions: book 
	 * Postcondition: none
	 * @return none
	 */
	public  void PatronReturn(String book){
		boolean removedbook=false;
		for(int i=0;i<books.size();i++){
			if(books.get(i).equals(book)){
				books.remove(book);
				removedbook=true;
			}
		}if(removedbook){
		System.out.println("Patron "+id+ " has returned "+book);
		}
	}
	/**
	 * Lists all the books the patron has borrowed 
	 * Preconditions: none 
	 * Postcondition: none
	 * @return none
	 */
	public  void PatronList(){
		if(books.size()==0){
			System.out.println("Patron "+id+" has not borrowed any books");
		}else{
			ListIterator<String> itr= books.listIterator();
			System.out.println("Patron "+id+" has borrowed " +books.size()+" item(s)");
			while(itr.hasNext()){
				System.out.println(itr.next());
			}
		}
	}

}
