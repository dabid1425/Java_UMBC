/*
 This class will show the block 
 and the next three blocks expected to be played

 */
import java.util.Random;
public class TetrisNextBlock {
	public int newBlock(){
		Random newRand = new Random();
		return newRand.nextInt(7)+1;
	}
	public static void main(String[] args) { 
		/*
		TetrisNextBlock newBlocks= new TetrisNextBlock();
		for(int i=0; i<10; i++){
			System.out.println(newBlocks.newBlock());
		}
		*/
	}
}
