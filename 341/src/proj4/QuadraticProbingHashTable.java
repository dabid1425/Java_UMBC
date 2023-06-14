package proj4;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;



// QuadraticProbing Hash table class
//
// CONSTRUCTION: an approximate initial size or default of 101
//
// ******************PUBLIC OPERATIONS*********************
// bool insert( x )       --> Insert x
// bool remove( x )       --> Remove x
// bool contains( x )     --> Return true if x is present
// void makeEmpty( )      --> Remove all items


/**
 * Probing table implementation of hash tables.
 * Note that all "matching" is based on the equals method.
 * @author Mark Allen Weiss
 */

public class QuadraticProbingHashTable<AnyType>
{
	/**
	 * Construct the hash table.
	 */
	private static int numberofInts=0;
	private static int successFullInserts=0;
	private static int successFailedInserts=0;
	private static int maxProb=0;
	private static int originalPos=0;
	private static int numbProbes=0;
	private static int clusters=0;
	private static int numberOfProbs=0;
	private static int maxClusters=0;
	private static int tableSize=0;

	/**
	 * Construct the hash table.
	 * @param size the approximate initial size.
	 */
	public QuadraticProbingHashTable( int size )
	{
		allocateArray( size );
		makeEmpty( );
	}

	/**
	 * Insert into the hash table. If the item is
	 * already present, do nothing.
	 * @param x the item to insert.
	 */
	public void insert( AnyType x )
	{
		// Insert x as active
		try{
			int currentPos = findPos( x );
			if( isActive( currentPos ) ){
				successFailedInserts++;
				//System.out.println("Failed "+successFailedInserts+" could not Insert "+x);
				return;
			}

			successFullInserts++;
			//System.out.println("Success "+ successFullInserts+" Inserted "+x);
			array[ currentPos ] = new HashEntry<AnyType>( x, true );
		}catch(ArrayIndexOutOfBoundsException e){
			if(numbProbes>maxProb){
				maxProb=numbProbes;
			}
			successFailedInserts++;
		}
	}

	private int myhash( AnyType x )
	{
		int hashVal = x.hashCode( );

		hashVal %= array.length;
		if( hashVal < 0 )
			hashVal += array.length;

		return hashVal;
	}
	/**
	 * Method that performs quadratic probing resolution.
	 * Assumes table is at least half empty and table length is prime.
	 * @param x the item to search for.
	 * @return the position where the search terminates.
	 */
	private int findPos( AnyType x )
	{
		List<Integer> allPos=new ArrayList<Integer>();
		int offset = 1;
		int currentPos = myhash( x );
		numbProbes=0;
		while( array[ currentPos ] != null &&
				!array[ currentPos ].element.equals( x ) )
		{	
			currentPos += offset;  // Compute ith probe
			offset += 2;
			if( currentPos >= array.length )
				currentPos%= array.length;
			if(allPos.contains(currentPos)){
				throw new ArrayIndexOutOfBoundsException();
			}else {
				allPos.add(currentPos);
			}
				numbProbes++;
				numberOfProbs++;
		}
		numbProbes++;
		numberOfProbs++;
		if(numbProbes>maxProb){
			maxProb=numbProbes;
		}
		return currentPos;
	}

	/**
	 * Remove from the hash table.
	 * @param x the item to remove.
	 */
	public void remove( AnyType x )
	{
		int currentPos = findPos( x );
		if( isActive( currentPos ) )
			array[ currentPos ].isActive = false;
	}

	/**
	 * Find an item in the hash table.
	 * @param x the item to search for.
	 * @return the matching item.
	 */
	public boolean contains( AnyType x )
	{
		int currentPos = findPos( x );
		return isActive( currentPos );
	}

	/**
	 * Return true if currentPos exists and is active.
	 * @param currentPos the result of a call to findPos.
	 * @return true if currentPos is active.
	 */
	private boolean isActive( int currentPos )
	{
		return array[ currentPos ] != null && array[ currentPos ].isActive;
	}

	/**
	 * Make the hash table logically empty.
	 */
	public void makeEmpty( )
	{
		//currentSize = 0;
		for( int i = 0; i < array.length; i++ )
			array[ i ] = null;
	}



	private static class HashEntry<AnyType>
	{
		public AnyType  element;   // the element
		public boolean isActive;  // false if marked deleted

		@SuppressWarnings("unused")
		public HashEntry( AnyType e )
		{
			this( e, true );
		}

		public HashEntry( AnyType e, boolean i )
		{
			element  = e;
			isActive = i;
		}
	}

	
	private HashEntry<AnyType> [ ] array; // The array of elements
	//private int currentSize;              // The number of occupied cells

	/**
	 * Internal method to allocate array.
	 * @param arraySize the size of the array.
	 */
	@SuppressWarnings("unchecked")
	private void allocateArray( int arraySize )
	{
		array = new HashEntry[  arraySize ];
	}

	public static void getClusters(QuadraticProbingHashTable<Integer> h){
		int cluster=0;
		for(int i=0;i<h.array.length;i++){
			if(h.array[i]!=null){
				cluster++;
			}if(h.array[i]==null&&cluster>=1){
				clusters++;
				if(cluster>maxClusters){
					maxClusters=cluster;
				}
				cluster=0;
			}

		}
		if(cluster!=0){
			clusters++;
			if(cluster>maxClusters){
				maxClusters=cluster;
			}
		}
	}
	public static double averageOfClusters(QuadraticProbingHashTable<Integer> h){
		getClusters(h);
		return (double)successFullInserts/(double)clusters;
	}
//	public static int totalInstered(QuadraticProbingHashTable<Integer> h){
//		int counter=0;
//		for(int i=0;i<h.array.length;i++){
//			if(h.array[i]!=null){
//				counter++;
//			}
//		}
//		return counter;
//	}
	public static double average(QuadraticProbingHashTable<Integer> h){
		return ((double)numberOfProbs/((double)successFullInserts+(double)successFailedInserts));
	}
	public static int total(){
		return successFullInserts+successFailedInserts;
	}
	public static double lamda(){
		return (double)successFullInserts/(double)tableSize; 
	}
	public void insertforLinear( AnyType x )
	{
		// Insert x as active
		try{
			int currentPos = findPosForLinear( x );
			if( isActive( currentPos ) ){
				successFailedInserts++;
				//System.out.println("Failed "+successFailedInserts+" could not Insert "+x);
				return;
			}

			successFullInserts++;
			//System.out.println("Success "+ successFullInserts+" Inserted "+x);
			array[ currentPos ] = new HashEntry<AnyType>( x, true );
		}catch(ArrayIndexOutOfBoundsException e){
			if(numbProbes>maxProb){
				maxProb=numbProbes;
			}
			successFailedInserts++;
		}
	}
	private int findPosForLinear( AnyType x )
	{
		int currentPos = myhash( x );
		originalPos=currentPos;
		numbProbes=0;
		numbProbes++;
		numberOfProbs++;
		while( array[ currentPos ] != null &&
				!array[ currentPos ].element.equals( x ) )
		{	
			currentPos += 1;  // Compute ith probe
			if( currentPos >= array.length )
				currentPos%= array.length;
			if(originalPos==currentPos){
				numbProbes++;
				numberOfProbs++;
				throw new ArrayIndexOutOfBoundsException();
			}
			numbProbes++;
			numberOfProbs++;
		}
		if(numbProbes>maxProb){
			maxProb=numbProbes;
		}
		return currentPos;
	
	}
	public void insertForDoubleHash( AnyType x, int largestPrime )
	{
		// Insert x as active
		try{
			int currentPos = findPosForDoubleHash( x ,largestPrime);
			if( isActive( currentPos ) ){
				successFailedInserts++;
				//System.out.println("Failed "+successFailedInserts+" could not Insert "+x);
				return;
			}

			successFullInserts++;
			//System.out.println("Success "+ successFullInserts+" Inserted "+x);
			array[ currentPos ] = new HashEntry<AnyType>( x, true );
		}catch(ArrayIndexOutOfBoundsException e){
			if(numbProbes>maxProb){
				maxProb=numbProbes;
			}
			successFailedInserts++;
		}
	}
	public int hashForDouble(AnyType x,int R){
		return R-(x.hashCode()%R);
	}
	private int findPosForDoubleHash( AnyType x,int R )
	{
		List<Integer> allPos=new ArrayList<Integer>();
		int offset = 1;
		int currentPos = myhash( x );
		originalPos=currentPos;
		numbProbes=0;
		numbProbes++;
		numberOfProbs++;
		while( array[ currentPos ] != null &&
				!array[ currentPos ].element.equals( x ) )
		{	
			currentPos += offset*hashForDouble(x,R);  // Compute ith probe
			if( currentPos >= array.length )
				currentPos%= array.length;
			
			if(allPos.contains(currentPos)){
				throw new ArrayIndexOutOfBoundsException();
			}else {
				allPos.add(currentPos);
			}
			numbProbes++;
			numberOfProbs++;
		}
		if(numbProbes>maxProb){
			maxProb=numbProbes;
		}
		return currentPos;
	
	}
	// Simple main
	public static void main( String [ ] args )
	{
		Scanner fileScanner = null;
		String[] splitString=null;
		try{
			FileInputStream file=new FileInputStream(args[0]);
			fileScanner=new Scanner(file);
			while(fileScanner.hasNextLine()){
				String command=fileScanner.nextLine();	
				splitString = (command.split("\\s+"));
			}
			numberofInts=Integer.parseInt(args[1]);
			int interval=Integer.parseInt(args[2]);
			tableSize=Integer.parseInt(args[3]);
			int largestPrime=Integer.parseInt(args[4]);
			int intervals=interval;
			int inter=intervals;
			QuadraticProbingHashTable<Integer> H = new QuadraticProbingHashTable<Integer>(tableSize );
			System.out.println("                  Linear Probing Analysis (Table size = "+tableSize+")");
			System.out.format("%30s %24s %19s","--- Inserts ---","------- Probes ------"," ----- Clusters -----");
			System.out.println("");
			System.out.println("   N   lambda  success  failed     total    avg    max     number   avg     max  ");
			int count=successFullInserts+successFailedInserts;
			while(count!=numberofInts){
				if(count!=inter){
					int x=Integer.parseInt(splitString[count]);
					H.insertforLinear(x);
				}else if(count==inter){
					double ave1=average(H); 
					double ave2=averageOfClusters(H);
					DecimalFormat fmt = new DecimalFormat(".00");
					System.out.format("%4s %8s %5s %10s %9s %7s %5s %9s %8s %6s ",inter,fmt.format(lamda()),successFullInserts,successFailedInserts,numberOfProbs
							,fmt.format(ave1),maxProb,clusters,fmt.format(ave2),maxClusters);
					System.out.println();
					inter=inter+intervals;
					clusters=0;
					maxClusters=0;
					H.insertforLinear(Integer.parseInt(splitString[count]));
				}
				count++;
			}
			double ave1=average(H);
			double ave2=averageOfClusters(H);
			DecimalFormat fmt = new DecimalFormat(".00");
			System.out.format("%4s %8s %5s %10s %9s %7s %5s %9s %8s %6s ",inter,fmt.format(lamda()),successFullInserts,successFailedInserts,numberOfProbs
					,fmt.format(ave1),maxProb,clusters,fmt.format(ave2),maxClusters);
			System.out.println();
			inter=intervals;
			count=0;
			successFullInserts=0;
			successFailedInserts=0;
			maxProb=0;
			clusters=0;
			numberOfProbs=0;
			maxClusters=0;
			H=new QuadraticProbingHashTable<Integer>(tableSize);
			System.out.println("                  Quadratic Probing Analysis (Table size = "+tableSize+")");
			System.out.format("%30s %24s %19s","--- Inserts ---","------- Probes ------"," ----- Clusters -----");
			System.out.println("");
			System.out.println("   N   lambda  success  failed     total    avg    max     number   avg     max  ");
			while(count!=numberofInts){
				if(count!=inter){
					int x=Integer.parseInt(splitString[count]);
					H.insert(x);
				}else if(count==inter){
					ave1=average(H);
					ave2=averageOfClusters(H);
					fmt = new DecimalFormat(".00");
					System.out.format("%4s %8s %5s %10s %9s %7s %5s %9s %8s %6s ",inter,fmt.format(lamda()),successFullInserts,successFailedInserts,numberOfProbs
							,fmt.format(ave1),maxProb,clusters,fmt.format(ave2),maxClusters);
					System.out.println();
					inter=inter+intervals;
					clusters=0;
					maxClusters=0;
					H.insert(Integer.parseInt(splitString[count]));
				}
				count++;
			}
			ave1=average(H);
			ave2=averageOfClusters(H);
			fmt = new DecimalFormat(".00");
			System.out.format("%4s %8s %5s %10s %9s %7s %5s %9s %8s %6s ",inter,fmt.format(lamda()),successFullInserts,successFailedInserts,numberOfProbs
					,fmt.format(ave1),maxProb,clusters,fmt.format(ave2),maxClusters);
			System.out.println();
			H=new QuadraticProbingHashTable<Integer>(tableSize);
			inter=intervals;
			count=0;
			successFullInserts=0;
			successFailedInserts=0;
			maxProb=0;
			clusters=0;
			numberOfProbs=0;
			maxClusters=0;
			H=new QuadraticProbingHashTable<Integer>(tableSize);
			System.out.println("                  Double Hashing Analysis (Table size = "+tableSize+")");
			System.out.format("%30s %24s %19s","--- Inserts ---","------- Probes ------"," ----- Clusters -----");
			System.out.println("");
			System.out.println("   N   lambda  success  failed     total    avg    max     number   avg     max  ");
			while(count!=numberofInts){
				if(count!=inter){
					int x=Integer.parseInt(splitString[count]);
					H.insertForDoubleHash(x,largestPrime);
				}else if(count==inter){
					ave1=average(H);
					ave2=averageOfClusters(H);
					fmt = new DecimalFormat(".00");
					System.out.format("%4s %8s %5s %10s %9s %7s %5s %9s %8s %6s ",inter,fmt.format(lamda()),successFullInserts,successFailedInserts,numberOfProbs
							,fmt.format(ave1),maxProb,clusters,fmt.format(ave2),maxClusters);
					System.out.println();
					inter=inter+intervals;
					clusters=0;
					maxClusters=0;
					H.insertForDoubleHash(Integer.parseInt(splitString[count]),largestPrime);
				}
				count++;
			}
			ave1=average(H);
			ave2=averageOfClusters(H);
			fmt = new DecimalFormat(".00");
			System.out.format("%4s %8s %5s %10s %9s %7s %5s %9s %8s %6s ",inter,fmt.format(lamda()),successFullInserts,successFailedInserts,numberOfProbs
					,fmt.format(ave1),maxProb,clusters,fmt.format(ave2),maxClusters);
			System.out.println();
		}catch(FileNotFoundException e){
			System.out.println("File not found " 
					+ e.getMessage());
			System.exit(-1);
		}
	}

}
