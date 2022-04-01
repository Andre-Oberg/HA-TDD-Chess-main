/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ax.ha.tdd.chess.engine.pieces;

import ax.ha.tdd.chess.engine.Chessboard;
import ax.ha.tdd.chess.engine.Coordinates;
import ax.ha.tdd.chess.engine.Player;

/**
 *
 * @author André
 */
public class Rook extends ChessPiece {
    
    public Rook(PieceType pieceType, Player player, Coordinates location) {
        super(pieceType, player, location);
    }

    @Override
    public String getSymbol() {
        return pieceType.getSymbol();
    }
    
    public boolean getMoves(Chessboard chessboard, Coordinates destination) {
        if (moveStraight(chessboard, destination)) {
            //this.setMoved();
            return true;
        }
        return false;
        //return moveStraight(chessboard, destination);
        /*int startX = this.getLocation().getX();
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

        return false;*/
    }
    
    @Override
    public boolean canMove(Chessboard chessboard, Coordinates destination) {
        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
        return getMoves(chessboard, destination);
    }
    
}
