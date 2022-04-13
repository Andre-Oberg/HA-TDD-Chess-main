package ax.ha.tdd.chess.engine;

import static ax.ha.tdd.chess.engine.Player.*;
import ax.ha.tdd.chess.engine.pieces.ChessPiece;
import ax.ha.tdd.chess.engine.pieces.ChessPieceStub;
import ax.ha.tdd.chess.engine.pieces.PieceType;
import static ax.ha.tdd.chess.engine.pieces.PieceType.KING;
import ax.ha.tdd.chess.engine.pieces.*;
import static ax.ha.tdd.chess.engine.pieces.PieceType.ROOK;
import java.util.ArrayList;

import java.util.Iterator;
import java.util.List;

public class Chessboard implements Iterable<ChessPiece[]> {
    // This could just as easily be replaced with a List or Set,
    // since the ChessPieces right now keep track of their own location.
    // Feel free to change this however you like
    // [y][x]
    
    
    private final ChessPiece[][] board = new ChessPiece[8][8];

    /*private List<Coordinates> whitePlayerPieces = new ArrayList<>();
    private List<Coordinates> blackPlayerPieces = new ArrayList<>();
    */
    private List<ChessPiece> whitePlayerPieces = new ArrayList<>();
    private List<ChessPiece> blackPlayerPieces = new ArrayList<>();
    
    private List<Coordinates> whiteAccessibleFields = new ArrayList<>();
    private List<Coordinates> blackAccessibleFields = new ArrayList<>();
    
    public static Chessboard startingBoard() {
        final Chessboard chessboard = new Chessboard();

        /*chessboard.withMirroredPiece(PieceType.PAWN, List.of(0,1,2,3,4,5,6,7), 1)
                .withMirroredPiece(PieceType.ROOK, List.of(0,7), 0)
                .withMirroredPiece(PieceType.KNIGHT, List.of(1,6), 0)
                .withMirroredPiece(PieceType.BISHOP, List.of(2,5), 0)
                .withMirroredPiece(PieceType.QUEEN, List.of(3), 0)
                .withMirroredPiece(PieceType.KING, List.of(4), 0);*/
        
        //chessboard.withMirroredPiece();
        
        return chessboard;
    }

    public ChessPiece getPiece(final Coordinates coordinates) {
        return board[coordinates.getY()][coordinates.getX()];
    }

    public void addPiece(final ChessPiece chessPiece) {
        board[chessPiece.getLocation().getY()][chessPiece.getLocation().getX()] = chessPiece;
    }

    /**
     * Helper method to initialize chessboard with {@link ChessPieceStub}.
     * Basically mirrors all added pieces for both players.
     * When all pieces has been implemented, this should be replaced with the proper implementations.
     *
     * @param pieceType pieceType
     * @param xCoordinates xCoordinates
     * @param yCoordinate yCoordinateOffset
     * @return itself, like a builder pattern
     */
    private Chessboard withMirroredPiece() {
        
        for (int i = 0; i < 8; i++) {
            addPiece(new Pawn(PieceType.PAWN, Player.BLACK, new Coordinates(i, 1)));
            addPiece(new Pawn(PieceType.PAWN, Player.WHITE, new Coordinates(i, 6)));
        }
               
        addPiece(new Rook(PieceType.ROOK, Player.BLACK, new Coordinates(0, 0)));
        addPiece(new Rook(PieceType.ROOK, Player.WHITE, new Coordinates(0, 7)));
        addPiece(new Rook(PieceType.ROOK, Player.BLACK, new Coordinates(7, 0)));
        addPiece(new Rook(PieceType.ROOK, Player.WHITE, new Coordinates(7, 7)));
        
        addPiece(new Knight(PieceType.KNIGHT, Player.BLACK, new Coordinates(1, 0)));
        addPiece(new Knight(PieceType.KNIGHT, Player.WHITE, new Coordinates(1, 7)));
        addPiece(new Knight(PieceType.KNIGHT, Player.BLACK, new Coordinates(6, 0)));
        addPiece(new Knight(PieceType.KNIGHT, Player.WHITE, new Coordinates(6, 7)));
        
        addPiece(new Bishop(PieceType.BISHOP, Player.BLACK, new Coordinates(2, 0)));
        addPiece(new Bishop(PieceType.BISHOP, Player.WHITE, new Coordinates(2, 7)));
        addPiece(new Bishop(PieceType.BISHOP, Player.BLACK, new Coordinates(5, 0)));
        addPiece(new Bishop(PieceType.BISHOP, Player.WHITE, new Coordinates(5, 7)));
        
        addPiece(new Queen(PieceType.QUEEN, Player.BLACK, new Coordinates(3, 0)));
        addPiece(new Queen(PieceType.QUEEN, Player.WHITE, new Coordinates(3, 7)));
        
        addPiece(new King(PieceType.KING, Player.BLACK, new Coordinates(4, 0)));
        addPiece(new King(PieceType.KING, Player.WHITE, new Coordinates(4, 7)));
            
        return this;
    }

    @Override
    public Iterator<ChessPiece[]> iterator() {
        return List.of(board).iterator();
    }
    
    public void deletePiece(Coordinates oldLocation) {
        board[oldLocation.getY()][oldLocation.getX()] = null;
    }
    
    public boolean checkIfSameColorTower(ChessPiece newPiece, ChessPiece oldPiece) {
        if (oldPiece.getPieceType() == ROOK && oldPiece.getPlayer() == newPiece.getPlayer()) {
            return true;
        }
        return false;
    }
    
    //Funktion för att hjälpa Rook och Bishop (skulle igentligen bara behöva kalla på delete piece)
    public void liftKing(Coordinates king) {
        this.deletePiece(king);
    }
    
    //Funktion för att hjälpa Rook och Bishop
    public void setKing(ChessPiece king) {
        board[king.getLocation().getY()][king.getLocation().getX()] = king;
        //board[king.getLocation().getX()][king.getLocation().getY()] = null;
    }
    
    public void movePiece(Coordinates newLocation, Coordinates oldLocation, ChessPiece newPiece) { 
        
        
        // Behövde specifik if sats ifall man utför castling, annars tas tornet bort och kungen flyttas till dens plats
        if (this.getPiece(newLocation) != null) {
            
            ChessPiece temp = this.getPiece(newLocation);
            if (checkIfSameColorTower(newPiece, temp) == true) {
                newPiece.setLocation(newLocation);
                this.addPiece(newPiece);
                temp.setLocation(oldLocation);
                this.addPiece(temp);
            }
        } else {
            newPiece.setLocation(newLocation);
            this.addPiece(newPiece);
            deletePiece(oldLocation);

        }
    }
    
    public List<Coordinates> getAccessibleFields(Player player) {
        
        
        if (player == BLACK) {
            return whiteAccessibleFields;
        } else {
            return blackAccessibleFields;
        }
    }
    
    public void updateAccessibleFields() {
        
        whiteAccessibleFields.clear();
        blackAccessibleFields.clear();
              
            whitePlayerPieces.forEach(temp -> {
                for (int i = 0; i < 8; i++) {
                    for (int j = 0; j < 8; j++) {
                        if (temp.canMove(this, new Coordinates(i, j)) == true && whiteAccessibleFields.contains(new Coordinates(i, j)) != true) {
                            whiteAccessibleFields.add(new Coordinates(i, j));
                        }
                    }
                }
            });
            
            blackPlayerPieces.forEach(temp -> {
                for (int i = 0; i < 8; i++) {
                    for (int j = 0; j < 8; j++) {
                        if (temp.canMove(this, new Coordinates(i, j)) == true && blackAccessibleFields.contains(new Coordinates(i, j)) != true) {
                            blackAccessibleFields.add(new Coordinates(i, j));
                        }
                    }
                }
            });
    }
    
    public void getPlayerPieces() {
        
        whitePlayerPieces.clear();
        blackPlayerPieces.clear();
        
        ChessPiece temp = null;
        
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                if (this.getPiece(new Coordinates(i, j)) != null) {
                    temp = this.getPiece(new Coordinates(i, j));
                    if (temp.getPlayer() == Player.BLACK){
                        blackPlayerPieces.add(temp);
                    } else if (temp.getPlayer() == Player.WHITE) {
                        whitePlayerPieces.add(temp);
                    }
                }
            }
        }
       
    }
    
    public void getPlayerPiecesExceptForKing() {
        
        whitePlayerPieces.clear();
        blackPlayerPieces.clear();
        
        ChessPiece temp = null;
        
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                if (this.getPiece(new Coordinates(i, j)) != null) {
                    temp = this.getPiece(new Coordinates(i, j));
                    if (temp.getPieceType() != KING) {
                        if (temp.getPlayer() == Player.BLACK){
                            blackPlayerPieces.add(temp);
                        } else if (temp.getPlayer() == Player.WHITE) {
                            whitePlayerPieces.add(temp);
                        }
                    }
                }
            }
        }
    }
    
    public void checkGameState(Player player) {
        System.out.println("Checking status");
        King king = (King) getPlayerKing(player);
        System.out.println("Checking status");
        if (king.isCheck(this)) {
            if (king.isCheckMate(this)) {
                System.out.println(player.getSymbol()+"'s king is in checkmate!");
            }
            else {
                System.out.println(player.getSymbol()+"'s king is in check!!");
            }
        }
    }
    
    public ChessPiece getPlayerKing(Player player) {
        
        ChessPiece temp = null;
        
        
        
        for (int i = 0; i<8; i++) {
            for (int j = 0; j<8; j++)
                if (this.getPiece(new Coordinates(i, j)) != null) {
                    temp = this.getPiece(new Coordinates(i, j));
                    if (temp.getPlayer() == player) {
                        System.out.println("Looking for king");
                        if (temp.getPieceType() == KING){
                            return temp;
                        }
                        //blackPlayerPieces.add(temp);
                    }
                }
        }

        return temp;
    }
    
    public void getPlayerPiecesSize() {
        System.out.println("BP: "+blackPlayerPieces.size()+" WP: "+whitePlayerPieces);
    }
    
}
