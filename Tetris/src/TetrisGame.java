/*
 This class will start the game, display and initialize the grid,
  randomly generate the Tetromino blocks has to show 
  three Tetromino blocks ahead of current block and will 
  initialize the player Tetromino
 */
import java.awt.Font;
import java.awt.HeadlessException;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import javax.swing.JFrame;
import javax.swing.JTextArea;
import javax.swing.SwingUtilities;
public class TetrisGame extends JFrame implements KeyListener {
	private TetrisPlayer player = new TetrisPlayer();
    private TetrisGrid grid = new TetrisGrid(24,40);
    private TetrisNextBlock newBlock = new TetrisNextBlock();
    private JTextArea text = new JTextArea();
	public TetrisGame() throws HeadlessException {
        setTitle("ASCII Text Tetris");
        setSize(410, 490);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        text.setFont(new Font("Courier New", Font.PLAIN, 16));
        text.addKeyListener(this);
        add(text);
        text.requestFocus();
        
        new Thread(new Runnable() {
            @Override
            public void run() {
                while (true) {
                    if (!grid.isGameOver()) {
                        grid.update();
                    }
                    grid.draw();
                    text.setText(grid.convertScreenToText());
                    try {
                        Thread.sleep(200);
                    } catch (InterruptedException ex) { }
                }
            }
        }).start();
    }

	@Override
    public void keyTyped(KeyEvent e) {
    }

    @Override
    public void keyPressed(KeyEvent e) {
        if (e.getID() == KeyEvent.KEY_PRESSED) {
            if (isGameOver()) {
                if (e.getKeyCode() == 32) {
                    player.start();
                }
            }
            else {
                switch (e.getKeyCode()) {
                    case 37: player.move(); break;
                    case 39: player.move(); break;
                    case 38: player.rotate(); break;
                    case 40: player.down(); break;
                    case 65: grid.update(); break;
                }
            }
        }
        repaint();
    }

    @Override
    public void keyReleased(KeyEvent e) {
    }

   public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                TetrisGame view = new TetrisGame();
                view.setVisible(true);
            }
        });
    }
}
