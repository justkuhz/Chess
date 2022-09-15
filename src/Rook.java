

import java.util.LinkedList;
import java.util.List;

/**
 * This class is where the Rook piece implements its version of move and getLegalMoves from the parent class Piece
 *
 * @author Kyle Techentin, Ken Zhu, Jesse Montel
 * @since 2022-09-14
 */
public class Rook extends Piece {

    public Rook(int color, Square initSq, String img_file, String type) {
        super(color, initSq, img_file, type);
    }

    /**
     * This method will return the legal moves of the rook piece
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

        // Rooks can only move linearly so we get all the moves it can make from here
        int[] occupies = getLinearOccupations(board, x, y);

        // These for loops are just adding all of the moves that are stored in occupies into the legal moves linked
        // list.
        for (int i = occupies[0]; i <= occupies[1]; i++) {
            if (i != y) legalMoves.add(board[i][x]);
        }
        
        for (int i = occupies[2]; i <= occupies[3]; i++) {
            if (i != x) legalMoves.add(board[y][i]);
        }
        
        return legalMoves;
    }

}
