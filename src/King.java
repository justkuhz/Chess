

import java.util.LinkedList;
import java.util.List;

public class King extends Piece {

    public King(int color, Square initSq, String img_file, String type) {
        super(color, initSq, img_file, type);
    }

    @Override
    public List<Square> getLegalMoves(Board b) {
LinkedList<Square> legalMoves = new LinkedList<Square>();
        
        Square[][] board = b.getSquareArray();
        
        int x = this.getPosition().getXNum();
        int y = this.getPosition().getYNum();
//Determining if castling is possible
        if (!hasMoved){
            for(int j = 0; j < 8; j++){
                for(int i = 0; i < 8; i++){
                    if(this.getColor() == 1) {
                        if (board[j][i].isOccupied()) {
                            if (board[j][i].getOccupyingPiece().getType().equals("wRook")) {
                                if (!board[j][i].getOccupyingPiece().hasMoved) {
                                    if (i == 7 && !board[j][5].isOccupied() && !board[j][6].isOccupied()) {
                                        legalMoves.add(board[j][i-1]);
                                    }
                                    if (i == 0 && !board[j][1].isOccupied() && !board[j][2].isOccupied() && !board[j][3].isOccupied()) {
                                        legalMoves.add(board[j][i+2]);
                                    }
                                }
                            }
                        }
                    }
                    if(this.getColor() == 0) {
                        if (board[j][i].isOccupied()) {
                            if (board[j][i].getOccupyingPiece().getType().equals("bRook")) {
                                if (!board[j][i].getOccupyingPiece().hasMoved) {
                                    if (i == 7 && !board[j][5].isOccupied() && !board[j][6].isOccupied()) {
                                        legalMoves.add(board[j][i - 1]);
                                    }
                                    if (i == 0 && !board[j][1].isOccupied() && !board[j][2].isOccupied() && !board[j][3].isOccupied()) {
                                        legalMoves.add(board[j][i + 2]);
                                    }
                                }
                            }
                        }
                    }
                }
            }
        }

        for (int i = 1; i > -2; i--) {
            for (int k = 1; k > -2; k--) {
                if(!(i == 0 && k == 0)) {
                    try {
                        if(!board[y + k][x + i].isOccupied() || 
                                board[y + k][x + i].getOccupyingPiece().getColor() 
                                != this.getColor()) {
                            legalMoves.add(board[y + k][x + i]);
                        }
                    } catch (ArrayIndexOutOfBoundsException e) {
                        continue;
                    }
                }
            }
        }
        
        return legalMoves;
    }

}
