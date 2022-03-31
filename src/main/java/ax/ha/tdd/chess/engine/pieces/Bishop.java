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
public class Bishop extends ChessPiece {

    public Bishop(PieceType pieceType, Player player, Coordinates location) {
        super(pieceType, player, location);
    }

    @Override
    public String getSymbol() {
        return pieceType.getSymbol();
    }

    public boolean getMoves(Chessboard chessboard, Coordinates destination) {
        return moveDiagonally(chessboard, destination);
    }
    
    @Override
    public boolean canMove(Chessboard chessboard, Coordinates destination) {
        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
        if (checkIfDestinationAvaiable(chessboard, destination)) {
            return moveDiagonally(chessboard, destination);
        }
        return false;
    }
    
}
