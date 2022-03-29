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

    /*public void Capture() {
    
    }*/
    
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
        
        //List<Coordinates> validMoves = new ArrayList<>();
        System.out.println("Before starts");
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
        
        System.out.println("Before move forward 1: "+(destination.getY()-this.getLocation().getY()));
        // Checks if pawn can move forward once   
        if ((destination.getY()+1) - (this.getLocation().getY()+1) == 1) {
            if (destination.getX() == this.getLocation().getX() && chessboard.getPiece(destination) == null) {
                    return true;
            }
        }
        
        System.out.println("Before move forward 2");
        // Checks if pawn can move forward twice
        if (hasMoved() != true) {
            if ((destination.getY()+1) - (this.getLocation().getY()+1) == 2) {
                if (destination.getX() == this.getLocation().getX() && chessboard.getPiece(destination) == null) {
                    return true;
                }
            }
        }
        
        System.out.println("Before move forward 3");
        // Checks if pawn can capture   
        Coordinates left = new Coordinates(this.getLocation().getX()-1, this.getLocation().getX()-oneMove);
        Coordinates right =  new Coordinates(this.getLocation().getX()+1, this.getLocation().getX()-oneMove);
        
        if (destination == left || destination == right) {
            if (chessboard.getPiece(destination) != null) {
                if (chessboard.getPiece(destination).getPlayer() != this.player) {
                    return true;
                }
            }
        }
            
        return false;
    }
    
    /*public ChessPiece Promote(Chessboard chessboard) {
        
    }*/
    
    @Override
    public boolean canMove(Chessboard chessboard, Coordinates destination) {
        return getMoves(chessboard, destination);
    }
    
    
    
}
