

import java.util.List;
import java.util.LinkedList;

/**
 * This class is where the pawn implements its version of move and getLegalMoves from the parent class piece
 *
 * @author Kyle Techentin, Ken Zhu, Jesse Montel
 * @since 2022-09-14
 */
public class Pawn extends Piece {
    private boolean wasMoved;
    
    public Pawn(int color, Square initSq, String img_file, String type) {
        super(color, initSq, img_file, type);
    }
    
    @Override
    public boolean move(Square fin) {
        boolean b = super.move(fin);
        wasMoved = true;
        return b;
    }

    /**
     * This method will return the legal moves of the pawn piece
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
        int c = this.getColor();

        // Black pawn movement
        if (c == 0) {
            if (!wasMoved) {
                if (!board[y+2][x].isOccupied()) { // If the black pawn has not been moved and there isn't a piece in
                    legalMoves.add(board[y+2][x]); // the way, it can be moved two spaces
                }
            }
            // This checks if the black pawn can just move 1 pace forward
            if (y+1 < 8) {
                if (!board[y+1][x].isOccupied()) {
                    legalMoves.add(board[y+1][x]);
                }
            }

            // This checks if there is a piece the pawn can capture to the SE from the black piece.
            if (x+1 < 8 && y+1 < 8) {
                if (board[y+1][x+1].isOccupied()) {
                    legalMoves.add(board[y+1][x+1]);
                }
            }
            // This checks if there is a piece the pawn can capture to the SW from the black piece.
            if (x-1 >= 0 && y+1 < 8) {
                if (board[y+1][x-1].isOccupied()) {
                    legalMoves.add(board[y+1][x-1]);
                }
            }
        }
        // White pawn movement
        if (c == 1) {
            if (!wasMoved) {
                if (!board[y-2][x].isOccupied()) { // If the black pawn has not been moved and there isn't a piece in
                    legalMoves.add(board[y-2][x]); // the way, it can be moved two spaces
                }
            }
            // This checks if the white pawn can just move 1 pace forward
            if (y-1 >= 0) {
                if (!board[y-1][x].isOccupied()) {
                    legalMoves.add(board[y-1][x]);
                }
            }
            // This checks if there is a piece the pawn can capture to the NE from the white piece.
            if (x+1 < 8 && y-1 >= 0) {
                if (board[y-1][x+1].isOccupied()) {
                    legalMoves.add(board[y-1][x+1]);
                }
            }
            // This checks if there is a piece the pawn can capture to the NW from the white piece.
            if (x-1 >= 0 && y-1 >= 0) {
                if (board[y-1][x-1].isOccupied()) {
                    legalMoves.add(board[y-1][x-1]);
                }
            }
        }
        
        return legalMoves;
    }
}
