/*
This class is designed to make the blockes used by the game.
 This class will make all 7 block configurations
 Refer to http://tetris.wikia.com/wiki/Tetrimino or 
 /Users/danabid/OneDrive/Documents/Java/Tetris/TetrisBlocks.png
*/
public class Tetromino {
	public char [][] cube(){
		char [][] cube = {{'0','1','1','0'},{'0','1','1','0'}};
		return cube;
	}
	public char [][] LShape(){
		char [][] LShape = {{'1','0','0','0'},{'1','1','1','0'}};
		return LShape;
	}
	public char [][] FourLong(){
		char [][] FourLong = {{'0','0','0','0'},{'1','1','1','1'}};
		return FourLong;
	}
	public char [][] ReverseLShape(){
		char [][] ReverseLShape = {{'0','0','0','1'},{'0','1','1','1'}};
		return ReverseLShape;
	}
	public char [][] triangle(){
		char [][] triangle = {{'0','0','1','0'},{'0','1','1','1'}};
		return triangle;
	}
	public char [][] Zig(){
		char [][] ReverseLShape = {{'1','1','0','0'},{'0','1','1','0'}};
		return ReverseLShape;
	}
	public char [][] Zag(){
		char [][] triangle = {{'0','0','1','1'},{'0','1','1','0'}};
		return triangle;
	}
	/*
	public static void unitTest(){
		Tetromino newTetromino = new Tetromino();
		char [][] cube=newTetromino.cube();
   		for(int i=0;i<2;i++){
   			for(int j=0;j<4;j++){		
   				System.out.print(cube[i][j]);
   			}
   			System.out.println();
   		}
   		cube=newTetromino.Zig();
   		System.out.println("Zig");
   		for(int i=0;i<2;i++){
   			for(int j=0;j<4;j++){		
   				System.out.print(cube[i][j]);
   			}
   			System.out.println();
   		}
   		cube=newTetromino.Zag();
   		System.out.println("Zag");
   		for(int i=0;i<2;i++){
   			for(int j=0;j<4;j++){		
   				System.out.print(cube[i][j]);
   			}
   			System.out.println();
   		}
   		cube=newTetromino.LShape();
   		System.out.println("L-Shape");
   		for(int i=0;i<2;i++){
   			for(int j=0;j<4;j++){		
   				System.out.print(cube[i][j]);
   			}
   			System.out.println();
   		}
   		System.out.println("Reverse L-Shape");
   		cube=newTetromino.ReverseLShape();
   		for(int i=0;i<2;i++){
   			for(int j=0;j<4;j++){		
   				System.out.print(cube[i][j]);
   			}
   			System.out.println();
   		}
   		cube=newTetromino.FourLong();
   		System.out.println("Four Long");
   		for(int i=0;i<2;i++){
   			for(int j=0;j<4;j++){		
   				System.out.print(cube[i][j]);
   			}
   			System.out.println();
   		}
   		System.out.println("Triangle");
   		cube=newTetromino.triangle();
   		for(int i=0;i<2;i++){
   			for(int j=0;j<4;j++){		
   				System.out.print(cube[i][j]);
   			}
   			System.out.println();
   		}
	}
	*/
   public static void main(String[] args) { 
   		//unitTest();
   }
}

