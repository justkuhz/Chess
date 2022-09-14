
import javax.swing.*;
/**
 * Chess Game
 * @author Kyle Techentin, Ken Zhu, Jesse Montel
 * @since September 14
 *
 * <br>
 *     This program creates the game of chess so that two players can play against each other on the same device.
 *     This program does not include an engine to play against nor does it offer possible moves for the user. Due to
 *     the latter, this program is not the most user friendly as one must know the rules of chess to be able to
 *     utilize our program.
 */
public class Game implements Runnable {
    public void run() {
        SwingUtilities.invokeLater(new StartMenu());
    }
    
    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Game());
    }
}
