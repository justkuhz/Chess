

import java.awt.Graphics;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.LinkedList;
import java.util.List;

import javax.imageio.ImageIO;

/**
 * This class is abstract and also the parent class of all other named piece classes.
 * From this class those pieces can inherit and overwrite many different methods that
 * will be used to get their move set, read the board, and know their own properties.
 *
 * @author Kyle Techentin, Ken Zhu, Jesse Montel
 * @since 2022-09-14
 */
public abstract class Piece {
    private final int color;
    private final String type;
    private Square currentSquare;
    private BufferedImage img;

    public boolean hasMoved = false;
    
    public Piece(int color, Square initSq, String img_file, String type) {
        this.color = color;
        this.currentSquare = initSq;
        this.type = type;
        
        try {
            if (this.img == null) {
              this.img = ImageIO.read(getClass().getResource(img_file));
            }
          } catch (IOException e) {
            System.out.println("File not found: " + e.getMessage());
          }
    }

    /**
     *
     *
     * @param fin this is the final square that a piece ends on after moving
     * @return returns false inside the boolean if there is a piece of the same color in the final move spot, but
     * returns true if it is null, or there was a piece of a different color there
     */
    public boolean move(Square fin) {
        Piece occup = fin.getOccupyingPiece();
        
        if (occup != null) {
            if (occup.getColor() == this.color) return false;
            else fin.capture(this);
        }
        
        currentSquare.removePiece();
        this.currentSquare = fin;
        currentSquare.put(this);
        hasMoved = true;
        return true;
    }
    
    public Square getPosition() {
        return currentSquare;
    }
    
    public void setPosition(Square sq) {
        this.currentSquare = sq;
    }
    
    public int getColor() {
        return color;
    }
    
    public Image getImage() {
        return img;
    }

    public String getType() {
        return type;
    }

    /**
     * This method
     *
     * @param g is the objects personal information, think color, x and y coordinates etc...
     */
    public void draw(Graphics g) {
        int x = currentSquare.getX();
        int y = currentSquare.getY();
        
        g.drawImage(this.img, x, y, null);
    }

    /**
     * This method will get the occupancy of pieces that are in linear squares to the piece who's coordinates are
     * passed in.
     *
     * @param board passes in the board of individual squares so that we can access what is actually on those squares
     * @param x This passes in the x coordinate of piece in question
     * @param y This passes in the y coordinates of piece in question
     * @return array the of last position in all directions of the piece in question
     */
    public int[] getLinearOccupations(Square[][] board, int x, int y) {
        int lastYabove = 0;
        int lastXright = 7;
        int lastYbelow = 7;
        int lastXleft = 0;

        // These for loops will find how far there is until another piece
        for (int i = 0; i < y; i++) {
            if (board[i][x].isOccupied()) { // iterating through the y-values of this column
                if (board[i][x].getOccupyingPiece().getColor() != this.color) {  // If a piece is met check the color
                    lastYabove = i; // If it is an opposing piece we stop the
                } else lastYabove = i + 1; // If the color is not different you cannot move as far
            }
        }

        for (int i = 7; i > y; i--) {
            if (board[i][x].isOccupied()) {
                if (board[i][x].getOccupyingPiece().getColor() != this.color) {
                    lastYbelow = i;
                } else lastYbelow = i - 1;
            }
        }

        for (int i = 0; i < x; i++) {
            if (board[y][i].isOccupied()) {
                if (board[y][i].getOccupyingPiece().getColor() != this.color) {
                    lastXleft = i;
                } else lastXleft = i + 1;
            }
        }

        for (int i = 7; i > x; i--) {
            if (board[y][i].isOccupied()) {
                if (board[y][i].getOccupyingPiece().getColor() != this.color) {
                    lastXright = i;
                } else lastXright = i - 1;
            }
        }
        
        int[] occups = {lastYabove, lastYbelow, lastXleft, lastXright};
        
        return occups;
    }
    /**
     * This method will get the occupancy of pieces that are in linear squares to the piece who's coordinates are
     * passed in.
     *
     * @param board passes in the board of individual squares so that we can access what is actually on those squares
     * @param x This passes in the x coordinate of piece in question
     * @param y This passes in the y coordinates of piece in question
     * @return A list of squares that hold
     */
    public List<Square> getDiagonalOccupations(Square[][] board, int x, int y) {
        LinkedList<Square> diagOccup = new LinkedList<Square>();
        
        int xNW = x - 1;
        int xSW = x - 1;
        int xNE = x + 1;
        int xSE = x + 1;
        int yNW = y - 1;
        int ySW = y + 1;
        int yNE = y - 1;
        int ySE = y + 1;
        
        while (xNW >= 0 && yNW >= 0) {
            if (board[yNW][xNW].isOccupied()) {
                if (board[yNW][xNW].getOccupyingPiece().getColor() == this.color) {
                    break;
                } else {
                    diagOccup.add(board[yNW][xNW]);
                    break;
                }
            } else {
                diagOccup.add(board[yNW][xNW]);
                yNW--;
                xNW--;
            }
        }
        
        while (xSW >= 0 && ySW < 8) {
            if (board[ySW][xSW].isOccupied()) {
                if (board[ySW][xSW].getOccupyingPiece().getColor() == this.color) {
                    break;
                } else {
                    diagOccup.add(board[ySW][xSW]);
                    break;
                }
            } else {
                diagOccup.add(board[ySW][xSW]);
                ySW++;
                xSW--;
            }
        }
        
        while (xSE < 8 && ySE < 8) {
            if (board[ySE][xSE].isOccupied()) {
                if (board[ySE][xSE].getOccupyingPiece().getColor() == this.color) {
                    break;
                } else {
                    diagOccup.add(board[ySE][xSE]);
                    break;
                }
            } else {
                diagOccup.add(board[ySE][xSE]);
                ySE++;
                xSE++;
            }
        }
        
        while (xNE < 8 && yNE >= 0) {
            if (board[yNE][xNE].isOccupied()) {
                if (board[yNE][xNE].getOccupyingPiece().getColor() == this.color) {
                    break;
                } else {
                    diagOccup.add(board[yNE][xNE]);
                    break;
                }
            } else {
                diagOccup.add(board[yNE][xNE]);
                yNE--;
                xNE++;
            }
        }
        
        return diagOccup;
    }
    
    // No implementation, to be implemented by each subclass
    public abstract List<Square> getLegalMoves(Board b);
}