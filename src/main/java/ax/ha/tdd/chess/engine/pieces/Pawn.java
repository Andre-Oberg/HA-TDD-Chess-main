/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ax.ha.tdd.chess.engine.pieces;

import ax.ha.tdd.chess.engine.Chessboard;
import ax.ha.tdd.chess.engine.Coordinates;
import ax.ha.tdd.chess.engine.Player;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author André
 */
public class Pawn extends ChessPiece {

    private final PieceType pieceType;
    
    public Pawn(PieceType pieceType, Player player, Coordinates location) {
        super(pieceType, player, location);
        this.pieceType = pieceType;
    }

    @Override
    public String getSymbol() {
        return pieceType.getSymbol();
    }
    
    // Function to see if pawn has moved from it's original square
    public boolean hasMoved() {
        if (this.getPlayer() == Player.WHITE && this.getLocation().getY() == 6) {
            return false; 
        } else if (this.getPlayer() == Player.BLACK && this.getLocation().getY() == 1) {
            return false;
        }
        return true;
    }
    
    public boolean getMoves(Chessboard chessboard, Coordinates destination) {
        
        int oneMove = 0;
        int twoMove = 0;
        
        // Sets the allowed movement of the pawns
        if (this.getPlayer() == Player.WHITE) {
            oneMove = -1;
            twoMove = -2;       
        } else if (this.getPlayer() == Player.BLACK) {
            oneMove = 1;
            twoMove = 2;    
        }
        
        int newY = destination.getY()+1;
        int curY = this.getLocation().getY()+1;
        
        int destY = (destination.getY()+1) - (this.getLocation().getY()+1);

        // Checks if pawn can move forward once   
        if (destY == oneMove) {
            if (destination.getX() == this.getLocation().getX() && chessboard.getPiece(destination) == null) {
                    return true;
            }
            
            // Check for capture
            if ((this.getLocation().getX()+1)-(destination.getX()+1) == 1) {
                if (chessboard.getPiece(destination) != null) {
                    if (chessboard.getPiece(destination).getPlayer() != this.getPlayer()) {
                        return true;
                    }
                }
            } else if ((this.getLocation().getX()+1)-(destination.getX()+1) == -1) {
                if (chessboard.getPiece(destination) != null) {
                    if (chessboard.getPiece(destination).getPlayer() != (this.getPlayer())) {
                        return true;
                    }
                }
           }
        }
        
        // Checks if pawn can move forward twice
        if (hasMoved() != true) {
            if (destY == twoMove) {
                if (chessboard.getPiece(new Coordinates(destination.getX(), destination.getY()-oneMove)) == null) {
                    if (destination.getX() == this.getLocation().getX() && chessboard.getPiece(destination) == null) {
                        return true;
                    }
                }
            }
        }
            
        return false;
    }
    
    @Override
    public boolean canMove(Chessboard chessboard, Coordinates destination) {
        return getMoves(chessboard, destination);
    }
    
    
    
}
