package proj1;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.LinkedList;
import java.util.ListIterator;
import java.util.Scanner;

public class Project1 {
	/**
	 * Project1.java - This class is the user interface for the project it will handle all print out statements and all try catch statements
	 * @version
	 * @Dan Abid
	 * @Project 1  CMSC 341 - Spring 2013
	 * @Section 4
	 *
	 */
	private static LinkedList<Patron> people=new LinkedList<Patron>();
	private static LinkedList<Book> books=new LinkedList<Book>();
	/**
	 * sees if the patron has been added to the list 
	 * Preconditions: needs a person  
	 * Postcondition: none
	 * @return none
	 */
	public static void possiblyAddPerson(int person){
		int counter=0;
		if(people.size()>0){
			for(int i=0;i<people.size();i++){
				if(people.get(i).getId()!=person){
					counter++;
				}
			}
			if(counter==people.size()){
				people.add(new Patron(person));
			}
		}else{
			people.add(new Patron(person));
		}
	}
	/**
	 * sees if the book has been added to the list 
	 * Preconditions: needs a book  
	 * Postcondition: none
	 * @return none
	 */
	public static void possiblyAddBook(String book){
		int counter=0;
		if(books.size()>0){
			for(int i=0;i<books.size();i++){
				if(!books.get(i).getId().equals(book)){
					counter++;
				}
			}
			if(counter==books.size()){
				Book newBook=new Book(book);
				books.add(newBook);
			}
		}else{
			Book newBook=new Book(book);
			books.add(newBook);
		}
	}
	/**
	 * sees if a patron can borrow a book  
	 * Preconditions: needs a book and a patron 
	 * Postcondition: none
	 * @return none
	 */
	public static void borrow(String book,int person){
		int counter=0;
		for(int i=0;i<people.size();i++){
			if(!people.get(i).hasbook(book)){
				counter++;
			}else{
				if(people.get(i).getId()==(person)){
					System.out.println("Patron "+person+" already has that book");
					return;
				}
			}
		}if(counter==people.size()){
			for(int i=0;i<people.size();i++){
				if(people.get(i).getId()==person){
					people.get(i).PatronBorrow(book);
				}
			}
		}else{
			for(int i=0;i<books.size();i++){
				if(books.get(i).getId().equals(book)){
					Patron persons=new Patron(person);
					books.get(i).addToQ(persons);
				}
			}
		}
	}
	/**
	 *  patron returns a book and then the program checks if anyone else is waiting for that book 
	 * Preconditions: needs a book and a patron 
	 * Postcondition: none
	 * @return none
	 */
	public static void returnBook(String book, int person){
		boolean hadbook=false;
		boolean there=false;
		for(int i=0;i<books.size();i++){
			if(books.get(i).getId().equals(book)){
				there=true;
				break;
			}
		}if(there){
			for(int i=0;i<people.size();i++){
				if(people.get(i).hasbook(book)&&people.get(i).getId()==person){
					hadbook=true;
					people.get(i).PatronReturn(book);
				}
			}if(!hadbook){
				System.out.println("Patron "+ person+" never had "+book);
			}else{
				for(int i=0;i<books.size();i++){
					if(books.get(i).getId().equals(book)){
						if(books.get(i).getlistSize()>0){
							int persons=books.get(i).removeFromQ().getId();
							borrow(book,persons);
						}
					}
				}
				
			}
		}else{
			System.out.println(book +" is not in our library");
		}


	}
	/**
	 * checks to see who has the book  
	 * Preconditions: needs a book 
	 * Postcondition: none
	 * @return none
	 */
	public static void whohas(String book){
		boolean notThere=true;
		for(int i=0;i<people.size();i++){
			if(people.get(i).hasbook(book)){
				System.out.println("Patron "+people.get(i).getId()+" has "+book);
				notThere=false;
				return;
			}
			
		}if(notThere){
			possiblyAddBook(book);
			System.out.println("No one has borrowed "+book);
		}
		
	}
	/**
	 * lists all the books the patron has check out at that time
	 * Preconditions: needs a patron 
	 * Postcondition: none
	 * @return none
	 */
	public static void lists(int person){
		boolean personNotThere=true;
		for(int i=0;i<people.size();i++){
			if(people.get(i).getId()==person){
				people.get(i).PatronList();
				personNotThere=false;
			}
		}
		if(personNotThere){
			possiblyAddPerson(person);
			System.out.println("Patron "+person+ " has not checked out anything");
		}

	}
	/**
	 * lists the wait list for that book 
	 * Preconditions: needs a book 
	 * Postcondition: none
	 * @return none
	 */
	public static void waitlist(String book){
		for(int i=0;i<books.size();i++){
			if(books.get(i).getId().equals(book)){
				books.get(i).printQ();
			}
		}
	}
	/**
	 * lists all the patrons in the library 
	 * Preconditions: none 
	 * Postcondition: none
	 * @return none
	 */
	public static void listpatrons() {
		if(people.size()>0){
		ListIterator<Patron> itr= people.listIterator();
		System.out.println("Patrons include: ");
		while(itr.hasNext()){
			System.out.println(itr.next().getId());
		}
		}else{
			System.out.println("No one has checked out or returned any books");
		}

	}
	public static void main(String[] args) {
		Scanner fileScanner = null;
		try{
			FileInputStream file=new FileInputStream(args[0]);
			fileScanner=new Scanner(file);
			while(fileScanner.hasNextLine()){
				String command=fileScanner.nextLine();	
				String[] splitString = (command.split("\\s+"));
				String newcommand=null;
				String newbook=null;
				String book="";
				int person=0;
				if(splitString.length==1){
					newcommand=splitString[0];
				}else if(splitString.length>=2){
					try{
						person=Integer.parseInt(splitString[0]);
						newcommand=splitString[1];
						if(!newcommand.equals("list")){
							possiblyAddPerson(person);
							for(int i=2;i<splitString.length;i++){
								book=book+" "+splitString[i];
							}
							possiblyAddBook(book);
							newbook=book;
						}
					}catch(Exception e){
						newcommand=splitString[0];
						for(int i=1;i<splitString.length;i++){
							book=book+" "+splitString[i];
						}
					}
				}
				if(newcommand.contains("borrow")){
					borrow(newbook,person);
				}else if(newcommand.contains("return")){
					returnBook(newbook,person);
				}else if(newcommand.contains("whohas")){
					whohas(book);
				}else if(newcommand.equals("list")){
					lists(person);
				}else if(newcommand.contains("waitlist")){
					waitlist(book);
				}else if(newcommand.equals("listpatrons")){
					listpatrons();
				}

			}


		}catch(FileNotFoundException e){
			System.out.println("File not found " 
					+ e.getMessage());
			System.exit(-1);
		}
	}

}
