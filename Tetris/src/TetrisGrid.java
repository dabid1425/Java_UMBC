/*
 This class creates the grid (10 x24), will add and 
 remove blocks in each space of the grid, 
 and will print the grid for the user

 */
public class TetrisGrid {
	int playScreenRows;
	int playScreenColumns;
	char [][] screen;
	TetrisGrid(int rows,int colums){
		playScreenRows=rows;
		playScreenColumns=colums;
		screen=new char [rows][colums];
	}
	public String convertScreenToText() {
        StringBuilder sb = new StringBuilder();
        for (int y=0; y<playScreenRows; y++) {
            for (int x=0; x<playScreenColumns; x++) {
                sb.append(screen[x][y]);
            }
            sb.append("\n");
        }
        return sb.toString();
    }
    public char[][] getScreen(){
    	return screen;
    }
	private void clear() {
		for (int y=0; y<playScreenRows; y++) {
            for (int x=0; x<playScreenColumns; x++) {
                screen[x][y] = '.';
            }
        }
        
    }
    public void drawGameOver() {
    	print(8,  9, "+=======================+");
        print(8, 10, "|       GAME OVER       |");
        print(8, 11, "|                       |");
        print(8, 12, "|  PRESS SPACE TO PLAY  |");
        print(8, 13, "+=======================+");
        
    }
    private void clearFilledLines() {
       
    int clearedLineCount = 0;
        nextRow:
        for (int row=0; row<gridRows; row++) {
            for (int col=0; col<gridCols; col++) {
                if (grid[row][col] == 0) {
                    continue nextRow;
                }
            }
            for (int row2 = row; row2 > 0; row2--) {
                System.arraycopy(grid[row2 - 1], 0, grid[row2], 0, gridCols);
            }
            clearedLineCount++;
        }
        return clearedLineCount;
    }
    
    private void solidify() {
    	int[] p = blocks[blockCurrent][blockRotation];
        for (int i=0; i<p.length; i+=2) {
            grid[p[i + 1] + blockRow][p[i] + blockCol] = blockCurrent + 1;
        }
        
    }
    public void draw() {
    	clear();
        print(32, 23, " by O.L.");
        drawScore();
        drawGrid();
        drawNextPiece();
        if (model.isGameOver()) {
            drawGameOver();
        }
        
    }
     private void drawScore() {
        print(17, 1, "+---------------+");
        print(17, 2, "|               |");
        print(17, 3, "+---------------+");
        print(19, 2, "SCORE: " + model.getScore());
    }

	private void drawGrid() {
		print(3, 1, "+----------+");
        print(3, 22, "+----------+");
        for (int row = 4; row < playScreenRows; row++) {
            print(3, row - 2, "|          |");
            for (int col = 0; col < playScreenColumns; col++) {
                int c = model.getGridValue(col, row);
                if (c > 0) {
                    print (col + 4, row - 2, "#");
                }
                else {
                    print (col + 4, row - 2, " ");
                }
            }
        }
        
    }
    public void update() {
        int clearedLineCount = clearFilledLines();
        // update score
        score += scoreTable[clearedLineCount];
        // fall block
        if (!collides(0, 1, blockRotation)) {
            blockRow++;
            return;
        }
        // check game over
        if (blockRow < 4 || gameOver) {
            gameOver = true;
            return;
        }
        solidify();
        nextPiece();
    }
    private void nextPiece() {
    	blockCol = 3;
        blockRow = 0;
        blockRotation = 0;
        blockCurrent = blockNext;
        blockNext = (int) (7 * Math.random());
       
    }
    private boolean collides(int dx, int dy, int rotation) {
        int[] block = blocks[blockCurrent][rotation];
        for (int i = 0; i < block.length; i+=2) {
            int col = block[i] + blockCol + dx;
            int row = block[i + 1] + blockRow + dy;
            if (col < 0 || col > gridCols - 1 
                    || row < 0 || row > gridRows - 1
                    || grid[row][col] > 0) {
                return true;
            }
        }
        return false;
    }

    private void drawNextPiece() {
    	print(17,  5, " NEXT: ");
        print(17,  6, "+----+");
        print(17, 11, "+----+");
        for (int row = 0; row < 4; row++) {
            print(17,  row + 7, "|    |");
            for (int col = 0; col < 4; col++) {
                int c = model.getNextPieceValue(col, row);
                if (c > 0) {
                    print (col + 18, row + 7, "#");
                }
                else {
                    print (col + 18, row + 7, " ");
                }
            }
        }
        
    }
    private void initBlocks() {
    	for (int p = 0; p < blockData.length; p++) {
            int colRow = 0;
            for (int b = 0; b < 64; b++) {
                colRow = b % 16 == 0 ? 0 : colRow;
                if (((blockData[p] >> b) & 1) == 1) {
                    blocks[p][b / 16][colRow++] = b % 4;
                    blocks[p][b / 16][colRow++] = (b % 16) / 4;
                }
            }
        }
        
    }
    public int getGridValue(int col, int row) {
        int[] block = blocks[blockCurrent][blockRotation];
        for (int i = 0; i < block.length; i+=2) {
            if (col == block[i] + blockCol && row == block[i + 1] + blockRow) {
                return blockCurrent + 1;
            }
        }
        return grid[row][col];
    }
    
   public int getNextPieceValue(int col, int row) {
        int[] block = blocks[blockNext][0];
        for (int i = 0; i < block.length; i+=2) {
            if (col == block[i] && row == block[i + 1]) {
                return blockNext + 1;
            }
        }
        return 0;
    }
    public boolean isGameOver() {
        return gameOver;
    }

    public int getScore() {
        return score;
    }

    public int getGridCols() {
        return gridCols;
    }

    public int getGridRows() {
        return gridRows;
    }
    private void print(int x, int y, String c) {
    	for (int col = 0; col < c.length(); col++) {
            screen[x + col][y] = c.charAt(col);
        }
       
    }


   public static void main(String[] args) { 
   }
}
