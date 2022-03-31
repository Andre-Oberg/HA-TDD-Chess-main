package ax.ha.tdd.chess.engine.pieces;

import ax.ha.tdd.chess.engine.Chessboard;
import ax.ha.tdd.chess.engine.Coordinates;
import ax.ha.tdd.chess.engine.Player;
import static java.lang.Math.abs;

import java.util.Objects;

public abstract class ChessPiece {

    protected final Player player;
    protected final PieceType pieceType;

    protected Coordinates location;

    public ChessPiece(PieceType pieceType, final Player player,
                      final Coordinates location) {
        this.pieceType = pieceType;
        this.player = player;
        this.location = location;
    }

    public abstract String getSymbol();

    public PieceType getPieceType() { return pieceType; }

    public Player getPlayer() {
        return player;
    }

    public Coordinates getLocation() {
        return location;
    }


    /**
     * Suggestion of design:
     * Checks if the chessPiece can move to a certain destination.
     * I preferred to keep this logic tightly coupled to the pieces instead of the board,
     * since that makes the separation of logic easier.
     *
     * @param chessboard chessboard
     * @param destination destination
     * @return true if piece can move to the destination
     */
    public abstract boolean canMove(final Chessboard chessboard, final Coordinates destination);

    protected boolean moveStraight(Chessboard chessboard, Coordinates destination) {
        
        int startX = this.getLocation().getX();
        int endX = destination.getX();
        
        int startY = this.getLocation().getY();
        int endY = destination.getY();
        
        int start = 1;
        int end = 1;
        System.out.println("End Pos is: "+endX+" "+endY);
        
        // Check if Y is the value which doesnt change
        if (startY == endY) {
            if (startX < endX) {
                start = startX+1;
                end = endX;
            } else if (startX > endX) {
                start = endX+1;
                end = startX;
            } else {
                return false;
            }

            System.out.println("Before for loop Y");
            for (int i = start; i < end; i++) {
                if (chessboard.getPiece(new Coordinates(i, startY)) != null) {
                    System.out.println("Broke at for loop Y: "+i);
                    return false;
                } 
            }
              
            System.out.println("At end start Y");
            return checkIfDestinationAvaiable(chessboard, destination);
        }
        
        // Check if X is the value which doesnt change
        if (startX == endX) {
            if (startY < endY) {
                start = startY+1;
                end = endY;
            } else if (startY > endY) {
                start = endY+1;
                end = startY;
            } else {
                return false;
            }
            System.out.println("Before for loop X");
            for (int i = start; i < end; i++) {
                if (chessboard.getPiece(new Coordinates(startX, i)) != null) {
                    System.out.println("Broke at for loop X: "+i);
                    return false;
                }
            }
            System.out.println("At end start X");
            return checkIfDestinationAvaiable(chessboard, destination);
        }

        return false;
    }
    
    protected boolean moveDiagonally(final Chessboard chessboard, final Coordinates destination) {
        
        int xStart = 0;
        int xEnd = 0;
            
        int yStart = 0;
        int yEnd = 0;
        
        System.out.println("Values x: "+destination.getX()+" "+this.getLocation().getX());
        System.out.println("Values y: "+destination.getY()+" "+this.getLocation().getY());
        
        System.out.println(Math.abs(destination.getX()-this.getLocation().getX()));
        System.out.println(Math.abs(destination.getY()-this.getLocation().getY()));
        
        int xTot = Math.abs(destination.getX()-this.getLocation().getX());
        int yTot = Math.abs(destination.getY()-this.getLocation().getY());
        
        System.out.println("Before 1st if: "+xTot+" "+yTot);
        if  (xTot == yTot) {
            
            
            System.out.println("Before 1st if in if");
            if (destination.getX() < this.getLocation().getX()) {
                System.out.println("X destination is smaller then this");
                xStart = destination.getX(); 
                xEnd = this.getLocation().getX();
            } else if (destination.getX() > this.getLocation().getX()) {
                System.out.println("X this is smaller then destination");
                xStart = this.getLocation().getX();
                xEnd = destination.getX();
            } else {
                return false;
            }
            
            
            
            System.out.println("Before 2nd if in if");
            if (destination.getY() < this.getLocation().getY()) {
                yStart = this.getLocation().getY(); 
                //yEnd = this.getLocation().getY();
            } else if (destination.getY() > this.getLocation().getY()) {
                yStart = destination.getY();
                //yEnd = destination.getY();
            } else {
                return false;
            }
            
            System.out.println("Before increase coordinates: "+xStart+" "+yStart);
            
            yStart -= 1;
            xStart += 1;
            
            System.out.println("Before while coordinates: "+xStart+" "+yStart);
            
            for (; xStart < xEnd; xStart++, yStart--) {
                System.out.println("Currently at: "+xStart+" "+yStart);
                if (chessboard.getPiece(new Coordinates(xStart, yStart)) != null) {
                    System.out.println("Found piece at: "+xStart+" "+yStart);
                    return false;
                }
            }
            
            return true;
            
        }
        
        return false;
    }
    
    protected boolean checkIfDestinationAvaiable(final Chessboard chessboard, final Coordinates destination) {
        if (chessboard.getPiece(destination) == null) {
            return true;
        }
        if (this.player != chessboard.getPiece(destination).getPlayer()) {
            return true;
        }
        return false;
    }
    
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ChessPiece that = (ChessPiece) o;
        return player == that.player && pieceType == that.pieceType;
    }

    @Override
    public int hashCode() {
        return Objects.hash(player, pieceType);
    }

    @Override
    public String toString() {
        return getPlayer().name() + " " + getClass().getSimpleName();
    }
}
