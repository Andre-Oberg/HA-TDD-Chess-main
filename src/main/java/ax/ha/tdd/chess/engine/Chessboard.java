package ax.ha.tdd.chess.engine;

import ax.ha.tdd.chess.engine.pieces.ChessPiece;
import ax.ha.tdd.chess.engine.pieces.ChessPieceStub;
import ax.ha.tdd.chess.engine.pieces.PieceType;
import java.util.ArrayList;

import java.util.Iterator;
import java.util.List;

public class Chessboard implements Iterable<ChessPiece[]> {
    // This could just as easily be replaced with a List or Set,
    // since the ChessPieces right now keep track of their own location.
    // Feel free to change this however you like
    // [y][x]
    
    
    private final ChessPiece[][] board = new ChessPiece[8][8];

    private List<Coordinates> whitePlayerPieces = new ArrayList<>();
    private List<Coordinates> blackPlayerPieces = new ArrayList<>();
    
    private List<Coordinates> whiteAccessibleFields = new ArrayList<>();
    private List<Coordinates> blackAccessibleFields = new ArrayList<>();
    
    public static Chessboard startingBoard() {
        final Chessboard chessboard = new Chessboard();

        chessboard.withMirroredPiece(PieceType.PAWN, List.of(0,1,2,3,4,5,6,7), 1)
                .withMirroredPiece(PieceType.ROOK, List.of(0,7), 0)
                .withMirroredPiece(PieceType.KNIGHT, List.of(1,6), 0)
                .withMirroredPiece(PieceType.BISHOP, List.of(2,5), 0)
                .withMirroredPiece(PieceType.QUEEN, List.of(3), 0)
                .withMirroredPiece(PieceType.KING, List.of(4), 0);
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
    private Chessboard withMirroredPiece(final PieceType pieceType,
                                         final List<Integer> xCoordinates, final int yCoordinate) {
        xCoordinates.forEach(xCoordinate -> {
            addPiece(new ChessPieceStub(pieceType, Player.BLACK, new Coordinates(xCoordinate, yCoordinate)));
            addPiece(new ChessPieceStub(pieceType, Player.WHITE, new Coordinates(xCoordinate, 7 - yCoordinate)));
        });
        return this;
    }

    @Override
    public Iterator<ChessPiece[]> iterator() {
        return List.of(board).iterator();
    }
    
    /*public ChessPiece movePiece(Coordinates coordinate) {
        ChessPiece tmp = this.piece;
        addPiece(null, coordinate);
        return tmp;
    }
    
    public void setPiece(ChessPiece piece, Coordinates coordinate) {
        return void;
    }*/
    
    public void getAccessibleFields(Chessboard chessboard) {
        
        whiteAccessibleFields.clear();
        blackAccessibleFields.clear();
              
        //System.out.println("Black pieces: "+blackPlayerPieces.size()+" White pieces: "+whitePlayerPieces.size());
        /*whitePlayerPieces.forEach(temp -> {
            whiteAccessibleFields.addAll(chessboard.getPiece(temp).getMoves(this, temp.getX(), temp.getY()));
        });*/
        
        whitePlayerPieces.forEach(temp -> {
            for (int i = 0; i < 8; i++) {
                for (int j = 0; j < 8; j++) {
                    if (chessboard.getPiece(temp).canMove(chessboard, new Coordinates(i, j)) == true && whiteAccessibleFields.contains(new Coordinates(i, j)) != true) {
                        whiteAccessibleFields.add(new Coordinates(i, j));
                    }
                }
            }
        });
        
        System.out.println("Before black");
        
        blackPlayerPieces.forEach(temp -> {
            for (int i = 0; i < 8; i++) {
                for (int j = 0; j < 8; j++) {
                    if (chessboard.getPiece(temp).canMove(chessboard, new Coordinates(i, j)) == true && blackAccessibleFields.contains(new Coordinates(i, j)) != true) {
                        blackAccessibleFields.add(new Coordinates(i, j));
                    }
                }
            }
        });
        
        System.out.println("Black accesible fields: "+blackAccessibleFields.size()+" White accesible fields: "+whiteAccessibleFields.size());
    }
    
    public void getPlayerPieces(Chessboard chessboard) {
        
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                System.out.println("X: "+i+" Y: "+j);
                if (chessboard.getPiece(new Coordinates(i, j)) != null) {
                    if (chessboard.getPiece(new Coordinates(i, j)).getPlayer() == Player.BLACK){
                        blackPlayerPieces.add(new Coordinates(i, j));
                    } else if (chessboard.getPiece(new Coordinates(i, j)).getPlayer() == Player.WHITE) {
                        whitePlayerPieces.add(new Coordinates(i, j));
                    }
                }
            }
        }
        System.out.println("Black pieces: "+blackPlayerPieces.size()+" White pieces: "+whitePlayerPieces.size());
        
    }
    
}
