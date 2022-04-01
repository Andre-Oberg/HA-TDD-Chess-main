/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ax.ha.tdd.chess.engine.pieces;

import ax.ha.tdd.chess.engine.Chessboard;
import ax.ha.tdd.chess.engine.Coordinates;
import ax.ha.tdd.chess.engine.Player;
import static ax.ha.tdd.chess.engine.pieces.PieceType.ROOK;
import java.util.ArrayList;
import java.util.List;
/**
 *
 * @author André
 */
public class King extends ChessPiece {
    
    public King(PieceType pieceType, Player player, Coordinates location) {
        super(pieceType, player, location);
    }

    @Override
    public String getSymbol() {
        return pieceType.getSymbol();
    }

    public boolean getMoves(Chessboard chessboard, Coordinates destination) {
        
        int X[] = { -1, 0, 1, -1, 1, -1, 0, 1 };
        int Y[] = { 1, 1, 1, 0, -1, -1, -1, 0 };
        
        // Praktiskt samma kod som användes för knight
        for (int i = 0; i < 8; i++) {
            if (this.getLocation().getX()+X[i] == destination.getX() && this.getLocation().getY()+Y[i] == destination.getY()) {
                return true;
            }
        }
        
        return false;
    }
    
    @Override
    public boolean canMove(Chessboard chessboard, Coordinates destination) {
        
        boolean moveCheck = false;
        
        if (checkIfDestinationAvaiable(chessboard, destination)) {
            moveCheck = getMoves(chessboard, destination);
        } else if (canCastle(chessboard, destination)) {
            moveCheck = true;
        }
        return moveCheck;
    }
    
    public boolean validateSpaces(Chessboard chessboard, Coordinates destination) {
        int xStart = 0;
        int xEnd = 0; 
        
        List<Coordinates> checkList = new ArrayList<>();
        
        if (this.getLocation().getX() < destination.getX()) {
            xStart = this.getLocation().getX();
            xEnd = destination.getX();
        } else {
            xStart = destination.getX();
            xEnd = this.getLocation().getX();
        }
        
        Coordinates location = null;
        
        checkList.add(destination);
        checkList.add(this.getLocation());
        
        for (int i = xStart+1; i <= xEnd-1; i++) {
            location = new Coordinates(i, this.getLocation().getY());
            if (chessboard.getPiece(location) != null) {
                return false;
            }
            checkList.add(location);
        }
        
        List<Coordinates> opponentFields = chessboard.getAccessibleFields(this.player);

        if (checkList.stream().anyMatch(Coordinates -> opponentFields.contains(Coordinates))) {
            return false;
        }
        
        return true;
    }
    
    public boolean canCastle(Chessboard chessboard, Coordinates destination) {
        
        if (getMoved() == false) {
            if (chessboard.getPiece(destination) != null) {
                ChessPiece check = chessboard.getPiece(destination);
                if (check.getPieceType() == ROOK && check.getMoved() == false) {
                    return validateSpaces(chessboard, destination);
                }
            }
        }
        
        return false;
    }
    
}
