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
    
    String lastMove = null;
    
    public Player getPlayerToMove() {
        //TODO this should reflect the current state.
        //return Player.WHITE;
        /*if (currentPlayer == WHITE) {
            currentPlayer = BLACK;
        } else {
            currentPlayer = BLACK;
        }*/
        
        return this.currentPlayer;
        
    }
    
    public void updateCurrentPlayer() {
        if (this.currentPlayer == WHITE) {
            this.currentPlayer = BLACK;
        } else {
            this.currentPlayer = WHITE;
        }
    }

    public Chessboard getBoard() {
        return board;
    }
    
    public void setLastMove(String message) {
        this.lastMove = message;
    }

    public String getLastMoveResult() {
        //TODO this should be used to show the player what happened
        //Illegal move, correct move, e2 moved to e4 etc.
        if (isNewGame) {
           return "Game hasn't begun";
        }
        return this.lastMove;
    }

    public void move(String move) {
        //TODO this should trigger your move logic.
        if (isNewGame == true) {
        
            updateCurrentPlayer();
            isNewGame = false;
        }
        //isNewGame = false;
        System.out.println("Current player: "+getPlayerToMove());
        
        board.getPlayerPieces();
        board.updateAccessibleFields();
        
        board.checkGameState(getPlayerToMove());
        
        ChessPiece currentPiece = null;
        Coordinates oldLocation = null;
        Coordinates newLocation = null;
        
        int[] numbers = new int[4];
        
        String seperator = ",| ";
        String[] values;

        if (move.isEmpty()) {
            setLastMove("You must type something");
            getLastMoveResult();
        } else {
            values = move.split(seperator);
            
            for (int i = 0; i<4; i++) {
                if (validInput(values[i])) {
                    numbers[i] = Integer.parseInt(values[i]);
                } else {
                    setLastMove("Invalid input");
                    getLastMoveResult();
                    break;
                }
            }
            
            if (board.getPiece(new Coordinates(numbers[0], numbers[1])) != null) {
                if ( board.getPiece(new Coordinates(numbers[0], numbers[1])).getPlayer() != getPlayerToMove()) {
                    setLastMove("You can't move one of your opponents chesspieces please select one of your own");
                    getLastMoveResult();
                    System.out.println("You can't move one of your opponents chesspieces please select one of your own");
                } else {
                    currentPiece = board.getPiece(new Coordinates(numbers[0], numbers[1]));
                    oldLocation = new Coordinates(numbers[0], numbers[1]);
                    newLocation = new Coordinates(numbers[2], numbers[3]); 
                }
            }
        }
        
        if (currentPiece != null) {
            if (currentPiece.canMove(board, new Coordinates(numbers[2], numbers[3]))) {
            currentPiece.setMoved();
            board.movePiece(newLocation, oldLocation, currentPiece);
            setLastMove("Moved: "+oldLocation.getX()+","+oldLocation.getY()+" to: "+newLocation.getX()+","+newLocation.getY());
            getLastMoveResult();
            updateCurrentPlayer();
            
            //System.out.println("Moved piece");
            
            } else {
                setLastMove("Invalid movement cant move: "+oldLocation.getX()+","+oldLocation.getY()+" to: "+newLocation.getX()+","+newLocation.getY());
                getLastMoveResult();
            }
        } else {
            setLastMove("No piece selected");
            getLastMoveResult();
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
