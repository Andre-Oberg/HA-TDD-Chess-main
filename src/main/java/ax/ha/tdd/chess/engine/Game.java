package ax.ha.tdd.chess.engine;

import static ax.ha.tdd.chess.engine.Player.BLACK;
import static ax.ha.tdd.chess.engine.Player.WHITE;
import ax.ha.tdd.chess.engine.pieces.ChessPiece;
import java.util.ArrayList;
import java.util.List;

public class Game {

    Chessboard board = Chessboard.startingBoard();

    //Feel free to delete this stuff. Just for initial testing.
    boolean isNewGame = true;
    
    Player currentPlayer = BLACK;
    
    public Player getPlayerToMove() {
        //TODO this should reflect the current state.
        //return Player.WHITE;
        if (currentPlayer == WHITE) {
            currentPlayer = BLACK;
        } else {
            currentPlayer = BLACK;
        }
        
        return currentPlayer;
        
    }

    public Chessboard getBoard() {
        return board;
    }

    public String getLastMoveResult(String message) {
        //TODO this should be used to show the player what happened
        //Illegal move, correct move, e2 moved to e4 etc.
        if (isNewGame) {
           return "Game hasn't begun";
        }
        return message;
    }

    public void move(String move) {
        //TODO this should trigger your move logic.
        isNewGame = false;
        
        board.getPlayerPieces();
        board.updateAccessibleFields();
        
        ChessPiece currentPiece = null;
        Coordinates oldLocation = null;
        Coordinates newLocation = null;
        
        int[] numbers = new int[4];
        
        String seperator = ",| ";
        String[] values;

        if (move.isEmpty()) {
            getLastMoveResult("You must type something");
        } else {
            values = move.split(seperator);
            
            for (int i = 0; i<4; i++) {
                if (validInput(values[i])) {
                    numbers[i] = Integer.parseInt(values[i]);
                } else {
                    getLastMoveResult("Invalid input");
                    break;
                }
            }
            
            currentPiece = board.getPiece(new Coordinates(numbers[0], numbers[1]));
            oldLocation = new Coordinates(numbers[0], numbers[1]);
            newLocation = new Coordinates(numbers[2], numbers[3]); 
        }
        
        if (currentPiece.canMove(board, new Coordinates(numbers[2], numbers[3]))) {
            
            currentPiece.setMoved();
            board.movePiece(newLocation, oldLocation, currentPiece);
            getLastMoveResult("Moved: "+oldLocation.getX()+","+oldLocation.getY()+" to: "+newLocation.getX()+","+newLocation.getY());
            //System.out.println("Moved piece");
            
        } else {
            getLastMoveResult("Invalid movement cant move: "+oldLocation.getX()+","+oldLocation.getY()+" to: "+newLocation.getX()+","+newLocation.getY());
        }
        
    
       
    }
    
    public boolean validInput(String value) {
        int number = Integer.parseInt(value);
        
        if (number >= 0 || number <= 7) {
            return true;
        }
        
        return false;
    }
    
}
