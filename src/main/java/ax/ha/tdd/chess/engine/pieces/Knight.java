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
public class Knight extends ChessPiece{

    public Knight(PieceType pieceType, Player player, Coordinates location) {
        super(pieceType, player, location);
    }

    @Override
    public String getSymbol() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    public boolean getMoves(Chessboard chessboard, Coordinates destination) {
        
        // All possible moves for the knight
        int X[] = { 2, 1, -1, -2, -2, -1, 1, 2 };
        int Y[] = { 1, 2, 2, 1, -1, -2, -2, -1 };

        for (int i = 0; i<8; i++) {
            if (this.getLocation().getX()+X[i] == destination.getX() && this.getLocation().getY()+Y[i] == destination.getY()) {
                return true;
            }
        }
        
        return false;
            
        //return false;
    }
    
    @Override
    public boolean canMove(Chessboard chessboard, Coordinates destination) {
        if (checkIfDestinationAvaiable(chessboard, destination)) {
            return getMoves(chessboard, destination);
        }
        return false;
    }
    
}
