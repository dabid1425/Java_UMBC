package proj2;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.Scanner;

// BinarySearchTree class
//
// CONSTRUCTION: with no initializer
//
// ******************PUBLIC OPERATIONS*********************
// void insert( x )       --> Insert x
// void remove( x )       --> Remove x
// boolean contains( x )  --> Return true if x is present
// Comparable findMin( )  --> Return smallest item
// Comparable findMax( )  --> Return largest item
// boolean isEmpty( )     --> Return true if empty; else false
// void makeEmpty( )      --> Remove all items
// void printTree( )      --> Print tree in sorted order
// ******************ERRORS********************************
// Throws UnderflowException as appropriate

/**
 * Implements an unbalanced binary search tree.
 * Note that all "matching" is based on the compareTo method.
 * @author Mark Allen Weiss
 */
public class BinarySearchTree<AnyType extends Comparable<? super AnyType>>
{
	/**
	 * Construct the tree.
	 */
	public BinarySearchTree()
	{
		root = null;
	}

	/**
	 * Insert into the tree; duplicates are ignored.
	 * @param x the item to insert.
	 */

	public void insert( AnyType x )
	{
		try {
			root = insert( x, root );
		} catch (Exception e) {
		}
	}

	/**
	 * Remove from the tree. Nothing is done if x is not found.
	 * @param x the item to remove.
	 * @throws Exception 
	 */
	public void remove( AnyType x ) throws Exception
	{
		root = remove( x, root );
	}

	/**
	 * Find the smallest item in the tree.
	 * @return smallest item or null if empty.
	 */
	public AnyType findOtherMax( BinaryNode<AnyType> t)
	{
		if( isEmpty( ) )
			throw new UnderflowException(null );
		return findMax( t ).element;
	}
	public AnyType findMin( )
	{
		if( isEmpty( ) )
			throw new UnderflowException(null );
		return findMin( root ).element;
	}

	/**
	 * Find the largest item in the tree.
	 * @return the largest item of null if empty.
	 */
	public AnyType findMax( )
	{
		if( isEmpty( ) )
			throw new UnderflowException(null );
		return findMax( root ).element;
	}

	/**
	 * Find an item in the tree.
	 * @param x the item to search for.
	 * @return true if not found.
	 */
	public boolean contains( AnyType x )
	{
		return contains( x, root );
	}

	/**
	 * Make the tree logically empty.
	 */
	public void makeEmpty( )
	{
		root = null;
	}

	/**
	 * Test if the tree is logically empty.
	 * @return true if empty, false otherwise.
	 */
	public boolean isEmpty( )
	{
		return root == null;
	}

	/**
	 * Print the tree contents in sorted order.
	 */
	public void printTree( )
	{
		if( isEmpty( ) )
			System.out.println( "Empty tree" );
		else
			printTree( root );
	}

	/**
	 * Internal method to insert into a subtree.
	 * @param x the item to insert.
	 * @param t the node that roots the subtree.
	 * @return the new root of the subtree.
	 * @throws Exception 
	 */
	private BinaryNode<AnyType> insert( AnyType x, BinaryNode<AnyType> t ) throws Exception
	{
		if( t == null )
			return new BinaryNode<AnyType>( x, null, null);

		int compareResult = x.compareTo( t.element );
		if( compareResult < 0 ){
			t.left = insert( x, t.left );
		}else if( compareResult > 0 ){
			t.right = insert( x, t.right );
		}else{
			throw new Exception();
		}
		t.treeSize++;	
		return t;
	}

	/**
	 * Internal method to remove from a subtree.
	 * @param x the item to remove.
	 * @param t the node that roots the subtree.
	 * @return the new root of the subtree.
	 * @throws Exception 
	 */
	private BinaryNode<AnyType> remove( AnyType x, BinaryNode<AnyType> t ) throws Exception
	{
		if( t == null ){
			throw new Exception();
		}
			
		int compareResult = x.compareTo( t.element );

		if( compareResult < 0 ){
			t.left = remove( x, t.left );
			t.treeSize--;
		} else if( compareResult > 0 ){
			t.right = remove( x, t.right );
			t.treeSize--;
		}else if( t.left != null && t.right != null ) // Two children
		{
			t.element = findMin( t.right ).element;
			t.right = remove( t.element, t.right );
		}
		else
			t = ( t.left != null ) ? t.left : t.right;
		return t;
	}

	/**
	 * Internal method to find the smallest item in a subtree.
	 * @param t the node that roots the subtree.
	 * @return node containing the smallest item.
	 */
	private BinaryNode<AnyType> findMin( BinaryNode<AnyType> t )
	{
		if( t == null )
			return null;
		else if( t.left == null )
			return t;
		return findMin( t.left );
	}

	/**
	 * Internal method to find the largest item in a subtree.
	 * @param t the node that roots the subtree.
	 * @return node containing the largest item.
	 */
	private BinaryNode<AnyType> findMax( BinaryNode<AnyType> t )
	{
		if( t != null )
			while( t.right != null )
				t = t.right;

		return t;
	}

	/**
	 * Internal method to find an item in a subtree.
	 * @param x is item to search for.
	 * @param t the node that roots the subtree.
	 * @return node containing the matched item.
	 */
	private boolean contains( AnyType x, BinaryNode<AnyType> t )
	{
		if( t == null )
			return false;

		int compareResult = x.compareTo( t.element );

		if( compareResult < 0 )
			return contains( x, t.left );
		else if( compareResult > 0 )
			return contains( x, t.right );
		else
			return true;    // Match
	}

	/**
	 * Internal method to print a subtree in sorted order.
	 * @param t the node that roots the subtree.
	 */
	private void printTree( BinaryNode<AnyType> t )
	{
		if( t != null )
		{
			printTree( t.left );
			System.out.println( t.element+ " "+t.treeSize );
			printTree( t.right );
		}
	}

	/**
	 * Internal method to compute height of a subtree.
	 * @param t the node that roots the subtree.
	 */
	private int height( BinaryNode<AnyType> t )
	{
		if( t == null )
			return -1;
		else
			return 1 + Math.max( height( t.left ), height( t.right ) );    
	}

	// Basic node stored in unbalanced binary search trees
	private static class BinaryNode<AnyType>
	{
		// Constructors
		BinaryNode( AnyType theElement )
		{
			this( theElement, null, null);
		}

		BinaryNode( AnyType theElement, BinaryNode<AnyType> lt, BinaryNode<AnyType> rt )
		{
			element  = theElement;
			left     = lt;
			right    = rt;
			treeSize=1;
		}

		AnyType element;            // The data in the node
		BinaryNode<AnyType> left;   // Left child
		BinaryNode<AnyType> right;  // Right child
		int treeSize=0;
	}

	/** The tree root. */
	private BinaryNode<AnyType> root;
	private int rank(BinaryNode<AnyType> t,AnyType x) throws Exception{
		if(t==null){
			throw new Exception();
		}else{
			if(t.element.compareTo(x)==0){
				if(t.right!=null){
					return t.treeSize-t.right.treeSize;

				}else if(t.left!=null){
					return t.treeSize-t.left.treeSize+1;
				}else{
					return 1;
				}
			}else if(x.compareTo( t.element )>0){
				if(t.left==null){
					return rank(t.right,x)+1;
				}else{
					return rank(t.right,x)+t.left.treeSize+1;
				}
			}else{
				return rank(t.left,x);
			}
		}

	}
	public int rank( AnyType x ) throws Exception{
		return rank(root,x);
	}
	private AnyType nthElement(BinaryNode<AnyType> t,int x){
		if(t.left==null){
			if(t.right!=null){
				if(x!=1){
					return nthElement(t.right,x-1);
				}else{
					return t.element;
				}
			}else{
				return t.element;
			}
		}else if(t.left.treeSize+1==x){
			return t.element;
		}else if(t.left.treeSize+1<x){
			return nthElement(t.right,x-t.left.treeSize-1);
		}else {
			return nthElement(t.left,x);
		}
	}
	public int getTreeSize(){
		return root.treeSize;
	}
	public AnyType nthElement(int n){
		return nthElement(root,n);
	}
	private boolean isPerfect(BinaryNode<AnyType> t,boolean x){
		if((Math.pow(2, (height(root)+1))-1)==t.treeSize){
			return true;
		}else{
			return false;
		}
	}
	public boolean isPerfect(){
		boolean perfect=isPerfect(root,true);
		return perfect;
	}
	public AnyType median(){
		int x=0;
		if(root.treeSize%2==0){
			x=root.treeSize/2;
		}else{
			x=root.treeSize/2;
			x=x+1;
		}
		AnyType counter=nthElement(root,x);
		return counter;
	}
	private String toString(BinaryNode<AnyType> t,int nrLevels ){
		if (t == null) {
			return null;
		}
		if (nrLevels == 1) {
			return "("+t.element + ","+((t.treeSize));
		} else if (nrLevels > 1) {
			String leftStr = toString(t.left, nrLevels - 1);
			if(leftStr==null){
				leftStr="";
			}else if(!leftStr.contains(")")&&!leftStr.equals("")){
				leftStr=leftStr+","+t.element+") ";
			}
			String rightStr =toString(t.right, nrLevels - 1);
			if(rightStr==null){
				rightStr="";
			}else if(!rightStr.contains(")")&&!rightStr.equals("")){
				rightStr=rightStr+","+t.element+") ";
			}
			return leftStr + rightStr;
		}
		else
			return "";

	}
	public String toString(int nrLevels){
		return toString(root,nrLevels);
	}
	// Test program
	public static void main( String [ ] args )
	{
		BinarySearchTree<Integer> t = new BinarySearchTree<Integer>( );
		Scanner fileScanner = null;
		try{
			FileInputStream file=new FileInputStream(args[0]);
			fileScanner=new Scanner(file);
			boolean needtoSetArray=true;
			boolean setRoot=false;

			String command=fileScanner.nextLine();	
			String newCommand;
			int n = 0;
			String[] splitString = (command.split("\\s+"));
			if(needtoSetArray){
				needtoSetArray=false;
				for(int i=0;i<splitString.length;i++){
					if(!setRoot){
						setRoot=true;
						try{
							int x=Integer.parseInt(splitString[0]);
							t.insert(x);
						}catch(Exception e){

						}

					}else{
						try{
							int x=Integer.parseInt(splitString[i]);
							t.insert(x);
						}catch(Exception e){

						}
					}
				}
			}
			FileInputStream files=new FileInputStream(args[1]);
			fileScanner=new Scanner(files);
			while(fileScanner.hasNextLine()){
				newCommand=fileScanner.nextLine();
				splitString = (newCommand.split("\\s+"));
				if(!splitString[0].contains("#")){
					command=splitString[0];
					if(splitString.length>1){
						n=Integer.parseInt(splitString[1]);
					}
					if(command.toLowerCase().equals("print")){
						System.out.println(command+" "+ n);
						for (int i = 1; i <= n; i++) {
							splitString=t.toString(i).split("\\s+");
							if(!splitString[0].equals("")){
								System.out.println("Level " + (i-1) + ": ");
								if(i==1){
									System.out.print(t.toString(i) + ",NULL)"+"\n");
								}else{
									int counter =-1;
									for(int j=0;j<splitString.length;j++){
										counter++;
										if(counter==6){
											counter=-1;
											System.out.println(" ");
											System.out.print(splitString[j]);
										}else{
											System.out.print(splitString[j]);
										}
									}System.out.println("");
								}
							}
						}
					}else if(command.toLowerCase().equals("rank")){
						System.out.println(command+" "+ n);
						int x=0;
						try{
						x= t.rank(n);
							System.out.println(x);
						}catch(Exception e){
							System.out.println(n+" was not found in the tree");
						}
					}else if(command.toLowerCase().equals("nth")){
						System.out.println(command+" "+ n);
						if(t.getTreeSize()>=n){
							int k= t.nthElement(n);
							System.out.println(k);
						}else{
							System.out.println("Nth "+n +" is greater then the tree size");
						}

					}else if(command.toLowerCase().equals("median")){
						System.out.println(command);
						System.out.println(t.median());
					}else if(command.toLowerCase().equals("remove")){
						System.out.println(command+" "+ n);
						try{
						t.remove(n);
						System.out.println("Removed "+n);
						}catch (Exception e){
							System.out.println(n+" was not found in the tree");
						}
					}else if(command.toLowerCase().equals("perfect")){
						System.out.println(command);
						if(t.isPerfect()){
							System.out.println("The tress is perfect");
						}else{
							System.out.println("The tree is not perfect");
						}
					}
				}
			}
		}catch(FileNotFoundException e){
			System.out.println("File not found " 
					+ e.getMessage());
			System.exit(-1);
		}
	}

}
