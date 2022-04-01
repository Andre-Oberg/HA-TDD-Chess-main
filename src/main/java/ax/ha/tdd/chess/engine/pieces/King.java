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
    
        //
        
        int X[] = { -1, 0, 1, -1, 1, -1, 0, 1 };
        int Y[] = { 1, 1, 1, 0, -1, -1, -1, 0 };
        
        // Praktiskt samma kod som användes för knight
        for (int i = 0; i < 8; i++) {
            if (this.getLocation().getX()+X[i] == destination.getX() && this.getLocation().getY()+Y[i] == destination.getY()) {
                //this.setMoved();
                return true;
                //moveCheck = true;
            }
        }
        
        /*if (canCastle(chessboard, destination)) {
            moveCheck = true;
        }*/
        
        return false;
        //return moveCheck;
    }
    
    @Override
    public boolean canMove(Chessboard chessboard, Coordinates destination) {
        
        boolean moveCheck = false;
        
        if (checkIfDestinationAvaiable(chessboard, destination)) {
            //System.out.println("Wont castle");
            moveCheck = getMoves(chessboard, destination);
        } else if (canCastle(chessboard, destination)) {
            //System.out.println("Wont castle");
            //System.out.println("Staring castling check");
            moveCheck = true;
            //this.setMoved();
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
            //System.out.println("Checking: "+i +" y: "+this.getLocation().getY());
            location = new Coordinates(i, this.getLocation().getY());
            if (chessboard.getPiece(location) != null) {
                //System.out.println("Castle check failed because something was between");
                return false;
            }
            checkList.add(location);
        }
        
        
        
        //
        
        List<Coordinates> opponentFields = chessboard.getAccessibleFields(this.player);
        
        for(int i=0; i<checkList.size(); i++) {
            System.out.println("CheckList X: "+checkList.get(i).getX()+" Y: "+checkList.get(i).getY());
        }
        
        for(int i=0; i<opponentFields.size(); i++) {
            System.out.println("opponentFields X: "+opponentFields.get(i).getX()+" Y: "+opponentFields.get(i).getY());
        }
        
        System.out.println("Checking opponent fields "+opponentFields.size());
        if (checkList.stream().anyMatch(Coordinates -> opponentFields.contains(Coordinates))) {
            System.out.println("Size is: "+opponentFields.size());
            System.out.println("Opponent can reach a field which is required to move though to castle");
            return false;
        }
        
        return true;
    }
    
    public boolean canCastle(Chessboard chessboard, Coordinates destination) {
        
        System.out.println("Castle check active "+this.getPlayer().getSymbol()+" "+this.moved);
        
        if (getMoved() == false) {
            System.out.println("Piece hasn't moved yet");
            if (chessboard.getPiece(destination) != null) {
                System.out.println("Castle check active 1");
                ChessPiece check = chessboard.getPiece(destination);
                if (check.getPieceType() == ROOK && check.getMoved() == false) {
                    System.out.println("Castle check active 2");
                    return validateSpaces(chessboard, destination);
                }
            }
        }
        
        return false;
    }
    
    //public boolean casteling
    
}
