

import java.util.List;

/**
 * This is a child class of Piece and it retains any information pertinent to the bishop class
 * and when the bishop is instantiated as an obj
 *
 * @author Kyle Techentin, Ken Zhu, Jesse Montel
 * @version 3.0
 * @since 2022-09-14
 */
public class Bishop extends Piece {

    public Bishop(int color, Square initSq, String img_file, String type) {
        super(color, initSq, img_file, type);
    }

    /**
     * This method will return the legal moves of the bishop piece
     *
     * @param b This is the board state being passes in so we can access the getDiagonalOccupations method.
     * @return a list of squares with the diagonal moves the piece is able to make.
     */
    @Override
    public List<Square> getLegalMoves(Board b) {
        Square[][] board = b.getSquareArray();
        int x = this.getPosition().getXNum();
        int y = this.getPosition().getYNum();
        
        return getDiagonalOccupations(board, x, y); // This piece is a bishop so it can only move diagonally in all
        // directions so this is all we have to check for
    }
}
