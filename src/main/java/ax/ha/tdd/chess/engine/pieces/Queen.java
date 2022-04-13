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
public class Queen extends ChessPiece {

    public Queen(PieceType pieceType, Player player, Coordinates location) {
        super(pieceType, player, location);
    }

    @Override
    public String getSymbol() {
        return pieceType.getSymbol();
    }

    public boolean getMoves(Chessboard chessboard, Coordinates destination) {
        if (checkIfDestinationAvaiable(chessboard, destination)) {
            System.out.println("Can move before second if");
            if (moveStraight(chessboard, destination) || moveDiagonally(chessboard, destination)) {
                return true;
            }
        }
        return false;
    }
    
    @Override
    public boolean canMove(Chessboard chessboard, Coordinates destination) {
        System.out.println("Queen Selected");
        return getMoves(chessboard, destination);
    }
    
}
