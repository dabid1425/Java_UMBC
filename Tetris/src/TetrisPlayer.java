/*
 This class will let the user rotate the
  new pieces coming in and place the piece 
  where they want to put the blocks and keep score
 The scoring formula for the majority of 
 Tetris products is built on the idea that 
 more difficult line clears should be awarded more points.
  For example, a single line clear in Tetris Zone is worth 100 points, 
  clearing four lines at once (known as a Tetris) is worth 800,
   while a back-to-back Tetris is worth 1,200 each.
 
 Nearly all Tetris games allow the player to press a 
 button to increase the speed of the current piece's descent, 
 rather than waiting for it to fall. The player can also stop 
 the pieces increased speed before the piece reaches the floor by 
 letting go of the button, this is a "soft drop"; otherwise, it is a 
 "hard drop" (some games only allow soft drop or hard drop; others 
 have separate buttons). Many games award a number of points based on 
 the height that the piece fell before locking.

 */
public class TetrisPlayer {
	public void rotate() {
		 int nextBlockRotation = (blockRotation + 1) % 4;
        if (!collides(0, 0, nextBlockRotation)) {
            blockRotation = nextBlockRotation;
        }
       
    }
    
    public void down() {
    	 while (!collides(0, 1, blockRotation)) {
            blockRow++;
        }
        
    }

    public void move(int dx) {
        if (!collides(dx, 0, blockRotation)) {
            blockCol += dx;
        }
    }
    public void start() {
    	grid = new int[gridRows][gridCols];
        blockCol = 3;
        blockRow = 0;
        score = 0;
        gameOver = false;
        
    }
    
   public static void main(String[] args) { 
   }
}
