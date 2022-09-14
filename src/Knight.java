

import java.util.LinkedList;
import java.util.List;
/**
 * This class is where the knight implements its version of move and getLegalMoves from the parent class piece
 *
 * @author Kyle Techentin, Ken Zhu, Jesse Montel
 * @since 2022-09-14
 */
public class Knight extends Piece {

    public Knight(int color, Square initSq, String img_file, String type) {
        super(color, initSq, img_file, type);
    }

    /**
     * This method will return the legal moves of the knight piece
     *
     * @param b This is the board state being passes in so we can access the getDiagonalOccupations method.
     * @return a list of squares with the possible moves the pawn can make based on its game state
     */
    @Override
    public List<Square> getLegalMoves(Board b) {
        LinkedList<Square> legalMoves = new LinkedList<Square>();
        Square[][] board = b.getSquareArray();
        
        int x = this.getPosition().getXNum();
        int y = this.getPosition().getYNum();

        // Finding legal knight moves
        for (int i = 2; i > -3; i--) {
            for (int k = 2; k > -3; k--) {
                if(Math.abs(i) == 2 ^ Math.abs(k) == 2) {
                    if (k != 0 && i != 0) {
                        try {
                            legalMoves.add(board[y + k][x + i]);
                        } catch (ArrayIndexOutOfBoundsException e) {
                            continue;
                        }
                    }
                }
            }
        }
        
        return legalMoves;
    }

}
