

import java.util.LinkedList;
import java.util.List;

/**
 * This class is where the queen piece implements its version of move and getLegalMoves from the parent class Piece
 *
 * @author Kyle Techentin, Ken Zhu, Jesse Montel
 * @since 2022-09-14
 */
public class Queen extends Piece {

    public Queen(int color, Square initSq, String img_file, String type) {
        super(color, initSq, img_file, type);
    }

    /**
     * This method will return the legal moves of the queen piece
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
        
        int[] occups = getLinearOccupations(board, x, y);
        
        for (int i = occups[0]; i <= occups[1]; i++) {
            if (i != y) legalMoves.add(board[i][x]);
        }
        
        for (int i = occups[2]; i <= occups[3]; i++) {
            if (i != x) legalMoves.add(board[y][i]);
        }

        // The queen's move set consists of 8 directions (4 cardinal directions and the 4 diagonals). So by getting
        // all tiles in the 4 cardinal directions as move sets and then adding in the 4 diagonals, we have her
        // entire move set
        List<Square> bMoves = getDiagonalOccupations(board, x, y);
        
        legalMoves.addAll(bMoves);
        
        return legalMoves;
    }
    
}
