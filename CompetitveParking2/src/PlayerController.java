
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class PlayerController implements KeyListener {

    private GUI x;

    public PlayerController(GUI x) {

        this.x = x;

    }

    @Override
    public void keyTyped(KeyEvent e) {
    }

    @Override
    public void keyPressed(KeyEvent e) {
        if (e.getKeyCode() == KeyEvent.VK_LEFT) {
            x.grid.rotateP2(0, -1);
        }
        if (e.getKeyCode() == KeyEvent.VK_RIGHT) {
            x.grid.rotateP2(0, 1);
        }
        if (e.getKeyCode() == KeyEvent.VK_UP) {
            x.grid.rotateP2(-1, 0);
        }
        if (e.getKeyCode() == KeyEvent.VK_DOWN) {
            x.grid.rotateP2(1, 0);
        }
        
        if (e.getKeyCode() == KeyEvent.VK_A) {
            x.grid.rotateP1(0, -1);
        }
        if (e.getKeyCode() == KeyEvent.VK_D) {
            x.grid.rotateP1(0, 1);
        }
        if (e.getKeyCode() == KeyEvent.VK_W) {
            x.grid.rotateP1(-1, 0);
        }
        if (e.getKeyCode() == KeyEvent.VK_S) {
            x.grid.rotateP1(1, 0);
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {
    }
}
