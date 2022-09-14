

import java.util.List;

/**
 * This is a child class of Piece and it retains any information pertinent to the bishop class
 * and when the bishop is instantiated as an obj
 *
 * @author Kyle Techentin
 * @version 3.0
 * @since 2022-09-14
 */
public class Bishop extends Piece {

    public Bishop(int color, Square initSq, String img_file, String type) {
        super(color, initSq, img_file, type);
    }
    
    @Override
    public List<Square> getLegalMoves(Board b) {
        Square[][] board = b.getSquareArray();
        int x = this.getPosition().getXNum();
        int y = this.getPosition().getYNum();
        
        return getDiagonalOccupations(board, x, y);
    }
}
