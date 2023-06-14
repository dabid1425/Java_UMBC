package proj1;

import java.util.LinkedList;
import java.util.ListIterator;

public class Book {

	/**
	 * @param args
	 */
	private String id;
	private LinkedList<Patron> people;
	public Book(String book) {
		id=book;
		people=new LinkedList<Patron>();
	}
	/**
	 * gets the size of the list 
	 * Preconditions: none 
	 * Postcondition: none
	 * @return size of the list 
	 */
	public int getlistSize(){
		return people.size();
	}
	/**
	 * returns the book name 
	 * Preconditions: none 
	 * Postcondition: none
	 * @return returns the book name 
	 */
	public String getId(){
		return id;
	}
	/**
	 * gets the next person in line
	 * Preconditions: none 
	 * Postcondition: none
	 * @return the next person in line
	 */
	public int getnextinLine(){
		return people.get(0).getId();
	}
	/**
	 * adds the patron to the Q
	 * Preconditions: patron  
	 * Postcondition: none
	 * @return none
	 */
	public void addToQ(Patron p){
		for(int i=0;i<people.size();i++){
			if(people.get(i).getId()==p.getId()){
				System.out.println("Patron "+p.getId()+" is already waiting for this book");
				return;
			}
		}
		people.add(p);
		System.out.println("Patron "+p.getId()+ " is waiting for "+id);
	}
	/**
	 * removes the patron to from Q
	 * Preconditions: none  
	 * Postcondition: none
	 * @return patron removed from the list 
	 */
	public Patron removeFromQ(){
		Patron p=people.get(0);
		LinkedList<Patron> dummyList=new LinkedList<Patron>();
		for(int i=1;i<people.size();i++){
			dummyList.add(people.get(i));
		}
		people=dummyList;
		return p;

	}
	/**
	 * checks the people size is zero  
	 * Preconditions: none  
	 * Postcondition: none
	 * @return true if the size is zero 
	 */
	public boolean emptyQ() {
		if(people.size()==0){
			return true;
		}return false;
	}
	/**
	 * prints out the waiting list   
	 * Preconditions: none  
	 * Postcondition: none
	 * @return none 
	 */
	public void printQ(){
		if(!emptyQ()){
			ListIterator<Patron> itr= people.listIterator();
			System.out.println("The waitlist for " +id +" are:");
			while(itr.hasNext()){
				System.out.println(itr.next().getId());
			}

		}else{
			System.out.println("There is no one waiting for "+id);
		}
	}
}
