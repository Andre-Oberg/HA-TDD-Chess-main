package ax.ha.tdd.chess.engine;

import ax.ha.tdd.chess.engine.pieces.ChessPieceStub;
import ax.ha.tdd.chess.engine.pieces.*;
import ax.ha.tdd.chess.engine.pieces.PieceType;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class ChessboardTest {

    /*@Test
    public void Chessboard_fromBeginning_isEmpty() {
        final Chessboard chessboard = new Chessboard();
        for (int x = 0; x < 8; x++) {
            for (int y = 0; y < 8; y++) {
                Assertions.assertNull(chessboard.getPiece(new Coordinates(x, y)));
            }
        }
    }

    @Test
    public void fullboard_whitePieces_isInCorrectSpot() {
        final Chessboard chessboard = Chessboard.startingBoard();
        for (int x = 0; x < 8; x++) {
            for (int y = 6; y < 8; y++) {
                Assertions.assertEquals(Player.WHITE, chessboard.getPiece(new Coordinates(x, y)).getPlayer());
            }
        }
    }

    @Test
    public void fullboard_BlackPieces_isInCorrectSpot() {
        final Chessboard chessboard = Chessboard.startingBoard();
        for (int x = 0; x < 8; x++) {
            for (int y = 0; y < 2; y++) {
                Assertions.assertEquals(Player.BLACK, chessboard.getPiece(new Coordinates(x, y)).getPlayer());
            }
        }
    }
    
    @Test
    public void fullboard_Pawns_isInCorrectSpot() {
        final Chessboard chessboard = Chessboard.startingBoard();
        for (int x = 0; x < 8; x++) {
            Assertions.assertEquals("P", chessboard.getPiece(new Coordinates(x, 1)).getSymbol());
            Assertions.assertEquals("P", chessboard.getPiece(new Coordinates(x, 6)).getSymbol());
        }
        
    }

    @Test
    public void fullboard_Rooks_isInCorrectSpot() {
        final Chessboard chessboard = Chessboard.startingBoard();
        Assertions.assertEquals("R", chessboard.getPiece(new Coordinates(0, 0)).getSymbol());
        Assertions.assertEquals("R", chessboard.getPiece(new Coordinates(7, 0)).getSymbol());
        Assertions.assertEquals("R", chessboard.getPiece(new Coordinates(0, 7)).getSymbol());
        Assertions.assertEquals("R", chessboard.getPiece(new Coordinates(7, 7)).getSymbol());
    }

    @Test
    public void fullboard_Knights_isInCorrectSpot() {
        final Chessboard chessboard = Chessboard.startingBoard();
        Assertions.assertEquals("K", chessboard.getPiece(new Coordinates(1, 0)).getSymbol());
        Assertions.assertEquals("K", chessboard.getPiece(new Coordinates(6, 0)).getSymbol());
        Assertions.assertEquals("K", chessboard.getPiece(new Coordinates(1, 7)).getSymbol());
        Assertions.assertEquals("K", chessboard.getPiece(new Coordinates(6, 7)).getSymbol());
    }

    @Test
    public void fullboard_Bishops_isInCorrectSpot() {
        final Chessboard chessboard = Chessboard.startingBoard();
        Assertions.assertEquals("B", chessboard.getPiece(new Coordinates(2, 0)).getSymbol());
        Assertions.assertEquals("B", chessboard.getPiece(new Coordinates(5, 0)).getSymbol());
        Assertions.assertEquals("B", chessboard.getPiece(new Coordinates(2, 7)).getSymbol());
        Assertions.assertEquals("B", chessboard.getPiece(new Coordinates(5, 7)).getSymbol());
    }

    @Test
    public void fullboard_Kings_isInCorrectSpot() {
        final Chessboard chessboard = Chessboard.startingBoard();
        Assertions.assertEquals(new ChessPieceStub(PieceType.KING, Player.BLACK), chessboard.getPiece(new Coordinates(4, 0)));
        Assertions.assertEquals(new ChessPieceStub(PieceType.KING, Player.WHITE), chessboard.getPiece(new Coordinates(4, 7)));
    }

    @Test
    public void fullboard_Queens_isInCorrectSpot() {
        final Chessboard chessboard = Chessboard.startingBoard();
        Assertions.assertEquals(new ChessPieceStub(PieceType.QUEEN, Player.BLACK), chessboard.getPiece(new Coordinates(3, 0)));
        Assertions.assertEquals(new ChessPieceStub(PieceType.QUEEN, Player.WHITE), chessboard.getPiece(new Coordinates(3, 7)));
    }*/
    
    // Tests for Pawns
    
    @Test
    public void pawnGetAvailableCoordinates() {
        Chessboard chessboard = new Chessboard();

        for (int i = 0; i < 8; i++) {
            chessboard.addPiece(new Pawn(PieceType.PAWN, Player.WHITE, new Coordinates(i, 6)));
            chessboard.addPiece(new Pawn(PieceType.PAWN, Player.BLACK, new Coordinates(i, 1)));
        }
        
        chessboard.addPiece(new Knight(PieceType.KNIGHT, Player.WHITE, new Coordinates(1, 7)));
        chessboard.addPiece(new Knight(PieceType.KNIGHT, Player.BLACK, new Coordinates(1, 0)));
        
        chessboard.addPiece(new Knight(PieceType.KNIGHT, Player.WHITE, new Coordinates(6, 7)));
        chessboard.addPiece(new Knight(PieceType.KNIGHT, Player.BLACK, new Coordinates(6, 0)));

    }
    
    
    @Test
    public void pawnCanMove1Forward() {
        Chessboard chessboard = new Chessboard();

        chessboard.addPiece(new Pawn(PieceType.PAWN, Player.WHITE, new Coordinates(1, 6)));
        chessboard.addPiece(new Pawn(PieceType.PAWN, Player.BLACK, new Coordinates(1, 1)));
        Assertions.assertEquals(true, chessboard.getPiece(new Coordinates(1, 6)).canMove(chessboard, (new Coordinates(1, 5))));
        Assertions.assertEquals(true, chessboard.getPiece(new Coordinates(1, 1)).canMove(chessboard, (new Coordinates(1, 2))));
    }
    
    @Test
    public void pawnCantMove1Backward() {
        Chessboard chessboard = new Chessboard();

        chessboard.addPiece(new Pawn(PieceType.PAWN, Player.WHITE, new Coordinates(1, 6)));
        chessboard.addPiece(new Pawn(PieceType.PAWN, Player.BLACK, new Coordinates(1, 1)));
        Assertions.assertEquals(false, chessboard.getPiece(new Coordinates(1, 6)).canMove(chessboard, (new Coordinates(1, 7))));
        Assertions.assertEquals(false, chessboard.getPiece(new Coordinates(1, 1)).canMove(chessboard, (new Coordinates(1, 0))));
    }
    
    @Test
    public void pawnCanMove2Forward() {
        Chessboard chessboard = new Chessboard();
        
        chessboard.addPiece(new Pawn(PieceType.PAWN, Player.WHITE, new Coordinates(1, 6)));
        chessboard.addPiece(new Pawn(PieceType.PAWN, Player.BLACK, new Coordinates(1, 1)));
        Assertions.assertEquals(true, chessboard.getPiece(new Coordinates(1, 6)).canMove(chessboard, (new Coordinates(1, 4))));
        Assertions.assertEquals(true, chessboard.getPiece(new Coordinates(1, 1)).canMove(chessboard, (new Coordinates(1, 3))));
    }
    
    @Test
    public void pawnCanMove1ForwardAndToSideWithoutCapture() {
        Chessboard chessboard = new Chessboard();
        
        chessboard.addPiece(new Pawn(PieceType.PAWN, Player.WHITE, new Coordinates(1, 6)));
        chessboard.addPiece(new Pawn(PieceType.PAWN, Player.BLACK, new Coordinates(1, 1)));
        Assertions.assertEquals(false, chessboard.getPiece(new Coordinates(1, 6)).canMove(chessboard, (new Coordinates(2, 4))));
        Assertions.assertEquals(false, chessboard.getPiece(new Coordinates(1, 1)).canMove(chessboard, (new Coordinates(0, 3))));
    }
    
    @Test
    public void pawnCanCapture() {
        Chessboard chessboard = new Chessboard();
        
        // Check to see if same color piece can capture each other
        chessboard.addPiece(new Pawn(PieceType.PAWN, Player.WHITE, new Coordinates(2, 2)));
        chessboard.addPiece(new Pawn(PieceType.PAWN, Player.WHITE, new Coordinates(1, 1)));
        Assertions.assertEquals(false, chessboard.getPiece(new Coordinates(2, 2)).canMove(chessboard, (new Coordinates(1, 1))));
        
        // Check to see it moves to opposing color if you can capture both but the left one is of same type
        chessboard.addPiece(new Pawn(PieceType.PAWN, Player.WHITE, new Coordinates(6, 6)));
        chessboard.addPiece(new Pawn(PieceType.PAWN, Player.WHITE, new Coordinates(5, 5)));
        chessboard.addPiece(new Pawn(PieceType.PAWN, Player.BLACK, new Coordinates(7, 5)));
        Assertions.assertEquals(true, chessboard.getPiece(new Coordinates(6, 6)).canMove(chessboard, (new Coordinates(7, 5))));
        
    }
    
    @Test
    public void pawnCantMoveForward() {
        Chessboard chessboard = new Chessboard();
        
        // Check to see if same color piece can capture each other
        chessboard.addPiece(new Pawn(PieceType.PAWN, Player.WHITE, new Coordinates(2, 6)));
        chessboard.addPiece(new Pawn(PieceType.PAWN, Player.BLACK, new Coordinates(2, 5)));
        
        chessboard.addPiece(new Pawn(PieceType.PAWN, Player.BLACK, new Coordinates(2, 1)));
        chessboard.addPiece(new Pawn(PieceType.PAWN, Player.BLACK, new Coordinates(2, 2)));

        Assertions.assertEquals(false, chessboard.getPiece(new Coordinates(2, 6)).canMove(chessboard, (new Coordinates(2, 4))));
        Assertions.assertEquals(false, chessboard.getPiece(new Coordinates(2, 1)).canMove(chessboard, (new Coordinates(2, 3))));
    }
    
    @Test
    public void pawnCanPromote() {
    
    }
    
    // Tests for Rook
    @Test
    public void rookCanMoveForward() {
        Chessboard chessboard = new Chessboard();

        chessboard.addPiece(new Rook(PieceType.ROOK, Player.WHITE, new Coordinates(0, 0)));
        chessboard.addPiece(new Rook(PieceType.ROOK, Player.BLACK, new Coordinates(7, 7)));


        Assertions.assertEquals(true, chessboard.getPiece(new Coordinates(0, 0)).canMove(chessboard, (new Coordinates(0, 7))));
        Assertions.assertEquals(true, chessboard.getPiece(new Coordinates(7, 7)).canMove(chessboard, (new Coordinates(7, 0))));
    }
    
    @Test
    public void rookCanMoveSideways() {
        Chessboard chessboard = new Chessboard();

        chessboard.addPiece(new Rook(PieceType.ROOK, Player.WHITE, new Coordinates(0, 0)));
        chessboard.addPiece(new Rook(PieceType.ROOK, Player.BLACK, new Coordinates(7, 7)));

        Assertions.assertEquals(true, chessboard.getPiece(new Coordinates(0, 0)).canMove(chessboard, (new Coordinates(7, 0))));
        Assertions.assertEquals(true, chessboard.getPiece(new Coordinates(7, 7)).canMove(chessboard, (new Coordinates(0, 7))));
    }
    
    @Test
    public void rookCannotMovepastPiece() {
        Chessboard chessboard = new Chessboard();
        
        // Check to see if same color piece can capture each other
        chessboard.addPiece(new Rook(PieceType.ROOK, Player.WHITE, new Coordinates(0, 0)));
        chessboard.addPiece(new Rook(PieceType.ROOK, Player.BLACK, new Coordinates(7, 7)));
        
        chessboard.addPiece(new Rook(PieceType.ROOK, Player.WHITE, new Coordinates(1, 0)));
        chessboard.addPiece(new Rook(PieceType.ROOK, Player.BLACK, new Coordinates(6, 7)));
        
        chessboard.addPiece(new Rook(PieceType.ROOK, Player.WHITE, new Coordinates(1, 5)));
        chessboard.addPiece(new Rook(PieceType.ROOK, Player.WHITE, new Coordinates(6, 5)));

        Assertions.assertEquals(false, chessboard.getPiece(new Coordinates(0, 0)).canMove(chessboard, (new Coordinates(5, 0))));
        Assertions.assertEquals(false, chessboard.getPiece(new Coordinates(7, 7)).canMove(chessboard, (new Coordinates(5, 7))));
        Assertions.assertEquals(false, chessboard.getPiece(new Coordinates(1, 5)).canMove(chessboard, (new Coordinates(6, 5))));
    }
    
    @Test
    public void rookCanCapturePiece() {
        Chessboard chessboard = new Chessboard();
        
        // Check to see if same color piece can capture each other
        chessboard.addPiece(new Rook(PieceType.ROOK, Player.WHITE, new Coordinates(0, 0)));
        chessboard.addPiece(new Rook(PieceType.ROOK, Player.BLACK, new Coordinates(2, 0)));
        
        chessboard.addPiece(new Rook(PieceType.ROOK, Player.WHITE, new Coordinates(5, 5)));
        chessboard.addPiece(new Rook(PieceType.ROOK, Player.BLACK, new Coordinates(5, 6)));

        Assertions.assertEquals(true, chessboard.getPiece(new Coordinates(0, 0)).canMove(chessboard, (new Coordinates(2, 0))));
        Assertions.assertEquals(true, chessboard.getPiece(new Coordinates(5, 5)).canMove(chessboard, (new Coordinates(5, 6))));
    }
    
    // Tests for Knights
    @Test
    public void knightBasicMovement() {
        Chessboard chessboard = new Chessboard();
        
        // Check to see if same color piece can capture each other
        chessboard.addPiece(new Knight(PieceType.KNIGHT, Player.WHITE, new Coordinates(1, 7)));
        chessboard.addPiece(new Knight(PieceType.KNIGHT, Player.BLACK, new Coordinates(1, 0)));
        
        chessboard.addPiece(new Knight(PieceType.KNIGHT, Player.WHITE, new Coordinates(6, 7)));
        chessboard.addPiece(new Knight(PieceType.KNIGHT, Player.BLACK, new Coordinates(6, 0)));
        
        Assertions.assertEquals(true, chessboard.getPiece(new Coordinates(1, 7)).canMove(chessboard, (new Coordinates(0, 5))));
        Assertions.assertEquals(true, chessboard.getPiece(new Coordinates(1, 0)).canMove(chessboard, (new Coordinates(2, 2))));
        
        Assertions.assertEquals(true, chessboard.getPiece(new Coordinates(6, 7)).canMove(chessboard, (new Coordinates(5, 5))));
        Assertions.assertEquals(true, chessboard.getPiece(new Coordinates(6, 0)).canMove(chessboard, (new Coordinates(4, 1))));
    }
    
    @Test
    public void knightMovingOverOtherPieces() {
        Chessboard chessboard = new Chessboard();
        
        chessboard.addPiece(new Knight(PieceType.KNIGHT, Player.WHITE, new Coordinates(1, 7)));
        chessboard.addPiece(new Knight(PieceType.KNIGHT, Player.BLACK, new Coordinates(2, 6)));
        
        chessboard.addPiece(new Knight(PieceType.KNIGHT, Player.BLACK, new Coordinates(6, 0)));
        chessboard.addPiece(new Knight(PieceType.KNIGHT, Player.BLACK, new Coordinates(1, 5)));
        
        
        Assertions.assertEquals(true, chessboard.getPiece(new Coordinates(1, 7)).canMove(chessboard, (new Coordinates(3, 6))));
        Assertions.assertEquals(true, chessboard.getPiece(new Coordinates(6, 0)).canMove(chessboard, (new Coordinates(5, 2))));
        
    }
    
    @Test
    public void knightMovingCapturingOpposingPieces() {
        Chessboard chessboard = new Chessboard();
        
        // Check to see if same color piece can capture each other
        chessboard.addPiece(new Knight(PieceType.KNIGHT, Player.WHITE, new Coordinates(1, 7)));
        chessboard.addPiece(new Knight(PieceType.KNIGHT, Player.BLACK, new Coordinates(3, 6)));
        
        chessboard.addPiece(new Knight(PieceType.KNIGHT, Player.BLACK, new Coordinates(6, 0)));
        chessboard.addPiece(new Knight(PieceType.KNIGHT, Player.WHITE, new Coordinates(5, 2)));
       
        Assertions.assertEquals(true, chessboard.getPiece(new Coordinates(1, 7)).canMove(chessboard, (new Coordinates(3, 6))));
        Assertions.assertEquals(true, chessboard.getPiece(new Coordinates(6, 0)).canMove(chessboard, (new Coordinates(5, 2))));
    }
    
    @Test
    public void knightTryingToCaptureSameColorPiece() {
        Chessboard chessboard = new Chessboard();
        
        // Check to see if same color piece can capture each other
        chessboard.addPiece(new Knight(PieceType.KNIGHT, Player.WHITE, new Coordinates(1, 7)));
        chessboard.addPiece(new Knight(PieceType.KNIGHT, Player.WHITE, new Coordinates(3, 6)));
        
        chessboard.addPiece(new Knight(PieceType.KNIGHT, Player.BLACK, new Coordinates(6, 0)));
        chessboard.addPiece(new Knight(PieceType.KNIGHT, Player.BLACK, new Coordinates(5, 2)));
       
        Assertions.assertEquals(false, chessboard.getPiece(new Coordinates(1, 7)).canMove(chessboard, (new Coordinates(3, 6))));
        Assertions.assertEquals(false, chessboard.getPiece(new Coordinates(6, 0)).canMove(chessboard, (new Coordinates(5, 2))));
    }

    @Test
    public void knightTyringToMakeInvalidMove() {
        Chessboard chessboard = new Chessboard();
        
        // Check to see if same color piece can capture each other
        chessboard.addPiece(new Knight(PieceType.KNIGHT, Player.WHITE, new Coordinates(1, 7)));
        chessboard.addPiece(new Knight(PieceType.KNIGHT, Player.WHITE, new Coordinates(6, 7)));
        
        chessboard.addPiece(new Knight(PieceType.KNIGHT, Player.BLACK, new Coordinates(1, 0)));
        chessboard.addPiece(new Knight(PieceType.KNIGHT, Player.BLACK, new Coordinates(6, 0)));
       
        Assertions.assertEquals(false, chessboard.getPiece(new Coordinates(1, 7)).canMove(chessboard, (new Coordinates(2, 6))));
        Assertions.assertEquals(false, chessboard.getPiece(new Coordinates(6, 7)).canMove(chessboard, (new Coordinates(4, 5))));
        
        Assertions.assertEquals(false, chessboard.getPiece(new Coordinates(1, 0)).canMove(chessboard, (new Coordinates(2, 1))));
        Assertions.assertEquals(false, chessboard.getPiece(new Coordinates(6, 0)).canMove(chessboard, (new Coordinates(0, 5))));

    }   
    // Tests for Bishop
    
    @Test
    public void bishopBasicMovement() {
        Chessboard chessboard = new Chessboard();
        
        chessboard.addPiece(new Bishop(PieceType.BISHOP, Player.BLACK, new Coordinates(2, 0)));
        chessboard.addPiece(new Bishop(PieceType.BISHOP, Player.BLACK, new Coordinates(5, 0)));
        
        chessboard.addPiece(new Bishop(PieceType.BISHOP, Player.WHITE, new Coordinates(2, 7)));
        chessboard.addPiece(new Bishop(PieceType.BISHOP, Player.WHITE, new Coordinates(5, 7)));
        
        Assertions.assertEquals(true, chessboard.getPiece(new Coordinates(2, 0)).canMove(chessboard, (new Coordinates(4, 2))));
        Assertions.assertEquals(true, chessboard.getPiece(new Coordinates(5, 7)).canMove(chessboard, (new Coordinates(0, 2))));
        
    }
    
    @Test
    public void bishopCapture() {
        Chessboard chessboard = new Chessboard();
        
        chessboard.addPiece(new Bishop(PieceType.BISHOP, Player.BLACK, new Coordinates(0, 5)));
        chessboard.addPiece(new Bishop(PieceType.BISHOP, Player.WHITE, new Coordinates(3, 2)));
        
        chessboard.addPiece(new Bishop(PieceType.BISHOP, Player.BLACK, new Coordinates(5, 7)));
        chessboard.addPiece(new Bishop(PieceType.BISHOP, Player.WHITE, new Coordinates(3, 5)));
        
        Assertions.assertEquals(true, chessboard.getPiece(new Coordinates(0, 5)).canMove(chessboard, (new Coordinates(3, 2))));
        Assertions.assertEquals(true, chessboard.getPiece(new Coordinates(5, 7)).canMove(chessboard, (new Coordinates(3, 5))));
        
    }
    
    @Test
    public void bishopInvalidMovement() {
        Chessboard chessboard = new Chessboard();
        
        chessboard.addPiece(new Bishop(PieceType.BISHOP, Player.BLACK, new Coordinates(5, 4)));
        chessboard.addPiece(new Bishop(PieceType.BISHOP, Player.WHITE, new Coordinates(2, 7)));
        
        chessboard.addPiece(new Bishop(PieceType.BISHOP, Player.BLACK, new Coordinates(5, 0)));
        chessboard.addPiece(new Bishop(PieceType.BISHOP, Player.WHITE, new Coordinates(2, 3)));
        
        chessboard.addPiece(new Bishop(PieceType.BISHOP, Player.WHITE, new Coordinates(0, 7)));
        chessboard.addPiece(new Bishop(PieceType.BISHOP, Player.BLACK, new Coordinates(7, 0)));
        
        // Tests with chess pieces in the way
        Assertions.assertEquals(false, chessboard.getPiece(new Coordinates(2, 7)).canMove(chessboard, (new Coordinates(6, 3))));
        Assertions.assertEquals(false, chessboard.getPiece(new Coordinates(5, 0)).canMove(chessboard, (new Coordinates(0, 5))));
        
        // Tests where the coordinate is not possible for bishop
        Assertions.assertEquals(false, chessboard.getPiece(new Coordinates(0, 7)).canMove(chessboard, (new Coordinates(0, 6))));
        Assertions.assertEquals(false, chessboard.getPiece(new Coordinates(7, 0)).canMove(chessboard, (new Coordinates(6, 3))));
        
    }
    
    // Tests for Queen (Basicly the exact tests from Rook and Bishop)
    @Test
    public void queenBasicMovement() {
        Chessboard chessboard = new Chessboard();
        
        chessboard.addPiece(new Queen(PieceType.QUEEN, Player.BLACK, new Coordinates(3, 0)));
        chessboard.addPiece(new Queen(PieceType.QUEEN, Player.WHITE, new Coordinates(3, 7)));
        
        chessboard.addPiece(new Queen(PieceType.QUEEN, Player.BLACK, new Coordinates(0, 4)));
        chessboard.addPiece(new Queen(PieceType.QUEEN, Player.WHITE, new Coordinates(7, 3)));
        
        //Move straight tests
        Assertions.assertEquals(true, chessboard.getPiece(new Coordinates(3, 0)).canMove(chessboard, (new Coordinates(7, 0))));
        Assertions.assertEquals(true, chessboard.getPiece(new Coordinates(3, 7)).canMove(chessboard, (new Coordinates(6, 4))));
        
        //Move diagonally tests
        Assertions.assertEquals(true, chessboard.getPiece(new Coordinates(0, 4)).canMove(chessboard, (new Coordinates(4, 0))));
        Assertions.assertEquals(true, chessboard.getPiece(new Coordinates(7, 3)).canMove(chessboard, (new Coordinates(5, 5))));
    }
    
    @Test
    public void queenCaptureTest() {
        Chessboard chessboard = new Chessboard();
        
        chessboard.addPiece(new Queen(PieceType.QUEEN, Player.BLACK, new Coordinates(3, 0)));
        chessboard.addPiece(new Queen(PieceType.QUEEN, Player.WHITE, new Coordinates(3, 7)));
        
        chessboard.addPiece(new Queen(PieceType.QUEEN, Player.BLACK, new Coordinates(7, 0)));
        chessboard.addPiece(new Queen(PieceType.QUEEN, Player.WHITE, new Coordinates(7, 7)));
        
        Assertions.assertEquals(true, chessboard.getPiece(new Coordinates(3, 0)).canMove(chessboard, (new Coordinates(3, 7))));
        
        Assertions.assertEquals(true, chessboard.getPiece(new Coordinates(7, 7)).canMove(chessboard, (new Coordinates(7, 0))));
        
        
        
    }
    
    @Test
    public void queenInvalidMove() {
        Chessboard chessboard = new Chessboard();
        
        chessboard.addPiece(new Queen(PieceType.QUEEN, Player.BLACK, new Coordinates(3, 0)));
        chessboard.addPiece(new Queen(PieceType.QUEEN, Player.WHITE, new Coordinates(3, 7)));
        
        Assertions.assertEquals(false, chessboard.getPiece(new Coordinates(3, 0)).canMove(chessboard, (new Coordinates(4, 7))));
        Assertions.assertEquals(false, chessboard.getPiece(new Coordinates(3, 7)).canMove(chessboard, (new Coordinates(5, 2))));

    }
    
    
    // Tests for King
    @Test
    public void kingBasicMoves() {
        Chessboard chessboard = new Chessboard();
        
        chessboard.addPiece(new King(PieceType.KING, Player.BLACK, new Coordinates(4, 0)));
        chessboard.addPiece(new King(PieceType.KING, Player.WHITE, new Coordinates(4, 7)));
        
        Assertions.assertEquals(true, chessboard.getPiece(new Coordinates(4, 0)).canMove(chessboard, (new Coordinates(5, 0))));
        Assertions.assertEquals(true, chessboard.getPiece(new Coordinates(4, 7)).canMove(chessboard, (new Coordinates(5, 6))));
        
    }
    
    @Test
    public void kingCapture() {
        Chessboard chessboard = new Chessboard();
        
        chessboard.addPiece(new King(PieceType.KING, Player.BLACK, new Coordinates(4, 0)));
        chessboard.addPiece(new King(PieceType.KING, Player.WHITE, new Coordinates(5, 0)));
        
        chessboard.addPiece(new King(PieceType.KING, Player.BLACK, new Coordinates(4, 6)));
        chessboard.addPiece(new King(PieceType.KING, Player.WHITE, new Coordinates(4, 7)));
        
        Assertions.assertEquals(true, chessboard.getPiece(new Coordinates(4, 0)).canMove(chessboard, (new Coordinates(5, 0))));
        Assertions.assertEquals(true, chessboard.getPiece(new Coordinates(4, 7)).canMove(chessboard, (new Coordinates(4, 6))));
        
    }
    
    @Test
    public void kingInvalidMovement() {
        Chessboard chessboard = new Chessboard();
        
        chessboard.addPiece(new King(PieceType.KING, Player.BLACK, new Coordinates(4, 0)));
        chessboard.addPiece(new King(PieceType.KING, Player.WHITE, new Coordinates(4, 7)));
        
        Assertions.assertEquals(false, chessboard.getPiece(new Coordinates(4, 0)).canMove(chessboard, (new Coordinates(6, 0))));
        Assertions.assertEquals(false, chessboard.getPiece(new Coordinates(4, 7)).canMove(chessboard, (new Coordinates(4, 5))));

        
    }
    
    // Hj??lper om man kommer ih??g att man satt in en kontroller f??r spelare efter att man gjort ett test s????slipper man fels??ka l??tta fel
    // Kommer klaga att p?? att den assertar fel detta h??nder pga bordet fylls med pj??ser vilket leder till att man inte kan castla
    @Test
    public void kingValidCastling() {
        Chessboard chessboard = new Chessboard();
        Game newGame = new Game(chessboard);
        
        
        newGame.board.addPiece(new King(PieceType.KING, Player.BLACK, new Coordinates(4, 0)));
        newGame.board.addPiece(new King(PieceType.KING, Player.WHITE, new Coordinates(4, 7)));
        
        newGame.board.addPiece(new Rook(PieceType.ROOK, Player.BLACK, new Coordinates(7, 0)));
        newGame.board.addPiece(new Rook(PieceType.ROOK, Player.WHITE, new Coordinates(0, 7)));
        
        // Vid detta fall s?? b??r vit inte kunna g??ra castling d?? svartas torn st??r mitemot den vita kungen
        newGame.move("4,7 0,7");

        Assertions.assertEquals("R", newGame.board.getPiece(new Coordinates(4, 7)).getSymbol());
        Assertions.assertEquals("K", newGame.board.getPiece(new Coordinates(0, 7)).getSymbol());
        
        
        newGame.move("4,0 7,0");
        
        Assertions.assertEquals("R", newGame.board.getPiece(new Coordinates(7, 0)).getSymbol());
        Assertions.assertEquals("K", newGame.board.getPiece(new Coordinates(4, 0)).getSymbol());
        
    }
    
    @Test
    public void kingInValidCastling() {
        Chessboard chessboard = new Chessboard();
        Game newGame = new Game(chessboard);
        
        newGame.board.addPiece(new King(PieceType.KING, Player.BLACK, new Coordinates(4, 0)));
        newGame.board.addPiece(new Rook(PieceType.ROOK, Player.WHITE, new Coordinates(1, 1)));
        newGame.board.addPiece(new Rook(PieceType.ROOK, Player.BLACK, new Coordinates(0, 0)));
        
        //B??r leda till fail d?? motst??ndaren har ett torn som kan g?? mellan kungen och tornet som man f??rs??ker castla till
        newGame.move("4,0 0,0");
        
        Assertions.assertEquals("K", newGame.board.getPiece(new Coordinates(4, 0)).getSymbol());
        Assertions.assertEquals("R", newGame.board.getPiece(new Coordinates(1, 1)).getSymbol());
        Assertions.assertEquals("R", newGame.board.getPiece(new Coordinates(0, 0)).getSymbol());
        
        newGame.board.deletePiece(new Coordinates(4, 0));
        newGame.board.deletePiece(new Coordinates(1, 1));
        newGame.board.deletePiece(new Coordinates(0, 0));
                
        newGame.board.addPiece(new King(PieceType.KING, Player.WHITE, new Coordinates(4, 7)));
        newGame.board.addPiece(new Rook(PieceType.ROOK, Player.WHITE, new Coordinates(7, 7)));
        newGame.board.addPiece(new Rook(PieceType.ROOK, Player.BLACK, new Coordinates(7, 0)));
        
        newGame.move("4,7 7,7");
        
        Assertions.assertEquals("K", newGame.board.getPiece(new Coordinates(4, 7)).getSymbol());
        Assertions.assertEquals("R", newGame.board.getPiece(new Coordinates(7, 7)).getSymbol());
        Assertions.assertEquals("R", newGame.board.getPiece(new Coordinates(7, 0)).getSymbol());
        
        newGame.board.deletePiece(new Coordinates(4, 7));
        newGame.board.deletePiece(new Coordinates(7, 7));
        newGame.board.deletePiece(new Coordinates(7, 0));
        
        newGame.board.addPiece(new King(PieceType.KING, Player.WHITE, new Coordinates(4, 0)));
        newGame.board.addPiece(new Rook(PieceType.ROOK, Player.WHITE, new Coordinates(7, 0)));
        newGame.board.addPiece(new Queen(PieceType.QUEEN, Player.BLACK, new Coordinates(4, 7)));
        
        newGame.move("4,0 7,0");
        
        Assertions.assertEquals("K", newGame.board.getPiece(new Coordinates(4, 0)).getSymbol());
        Assertions.assertEquals("R", newGame.board.getPiece(new Coordinates(7, 0)).getSymbol());
        Assertions.assertEquals("Q", newGame.board.getPiece(new Coordinates(4, 7)).getSymbol());
        
    }
    
    @Test 
    public void deletePiece() {
        Chessboard chessboard = new Chessboard();
        
        chessboard.addPiece(new King(PieceType.KING, Player.BLACK, new Coordinates(4, 0)));
        chessboard.addPiece(new King(PieceType.KING, Player.WHITE, new Coordinates(4, 7)));
        
        chessboard.deletePiece(new Coordinates(4, 0));
        
        Assertions.assertEquals(null, chessboard.getPiece(new Coordinates(4, 0)));
    }

    /*@Test
    public void testCheck() {
        Chessboard chessboard = new Chessboard();
        Game newGame = new Game(chessboard);
        
        // Should only result in check because the king can go to the queens position
        
        System.out.println("Check control for black king");
        newGame.board.addPiece(new King(PieceType.KING, Player.BLACK, new Coordinates(4, 0)));
        newGame.board.addPiece(new Bishop(PieceType.BISHOP, Player.WHITE, new Coordinates(7, 2)));
        newGame.board.addPiece(new Bishop(PieceType.BISHOP, Player.WHITE, new Coordinates(7, 3)));

        newGame.board.getPlayerPieces();
        newGame.board.updateAccessibleFields();
        newGame.board.checkGameState(Player.BLACK);
        
        System.out.println("Check control for white king");
        newGame.board.addPiece(new King(PieceType.KING, Player.BLACK, new Coordinates(4, 0)));
        newGame.board.addPiece(new Pawn(PieceType.PAWN, Player.BLACK, new Coordinates(3, 6)));
        newGame.board.addPiece(new King(PieceType.KING, Player.WHITE, new Coordinates(4, 7)));
        
        newGame.board.getPlayerPieces();
        newGame.board.updateAccessibleFields();
        newGame.board.checkGameState(Player.WHITE);
    }

    @Test
    public void testCheckMate() {
        Chessboard chessboard = new Chessboard();
        Game newGame = new Game(chessboard);
        
        System.out.println("Checkmate control for black king");
        newGame.board.addPiece(new King(PieceType.KING, Player.BLACK, new Coordinates(4, 0)));
        newGame.board.addPiece(new Rook(PieceType.ROOK, Player.WHITE, new Coordinates(0, 1)));
        newGame.board.addPiece(new Rook(PieceType.ROOK, Player.WHITE, new Coordinates(1, 0)));
        
        newGame.board.getPlayerPieces();
        newGame.board.updateAccessibleFields();
        newGame.board.checkGameState(Player.BLACK);

        System.out.println("Checkmate control for white king");
        newGame.board.addPiece(new King(PieceType.KING, Player.WHITE, new Coordinates(4, 7)));
        newGame.board.addPiece(new Rook(PieceType.ROOK, Player.BLACK, new Coordinates(1, 6)));
        newGame.board.addPiece(new Rook(PieceType.ROOK, Player.BLACK, new Coordinates(0, 7)));
        
        newGame.board.getPlayerPieces();
        newGame.board.updateAccessibleFields();
        newGame.board.checkGameState(Player.WHITE);
    }
    
    @Test
    public void testCheckMateWithAlliedPieces() {
        Chessboard chessboard = new Chessboard();
        Game newGame = new Game(chessboard);
        
        System.out.println("Checkmate control for black king 2");
        newGame.board.addPiece(new King(PieceType.KING, Player.BLACK, new Coordinates(4, 0)));
        newGame.board.addPiece(new Pawn(PieceType.PAWN, Player.BLACK, new Coordinates(3, 1)));
        newGame.board.addPiece(new Pawn(PieceType.PAWN, Player.BLACK, new Coordinates(5, 1)));
        newGame.board.addPiece(new Queen(PieceType.QUEEN, Player.BLACK, new Coordinates(3, 0)));
        newGame.board.addPiece(new Bishop(PieceType.BISHOP, Player.BLACK, new Coordinates(5, 0)));
        
        newGame.board.addPiece(new Rook(PieceType.ROOK, Player.WHITE, new Coordinates(4, 3)));
        
        newGame.board.getPlayerPieces();
        newGame.board.updateAccessibleFields();
        newGame.board.checkGameState(Player.BLACK);

        System.out.println("Checkmate control for white king 2");
        newGame.board.addPiece(new King(PieceType.KING, Player.WHITE, new Coordinates(4, 7)));
        newGame.board.addPiece(new Pawn(PieceType.PAWN, Player.WHITE, new Coordinates(3, 6)));
        newGame.board.addPiece(new Pawn(PieceType.PAWN, Player.WHITE, new Coordinates(4, 6)));
        newGame.board.addPiece(new Queen(PieceType.QUEEN, Player.WHITE, new Coordinates(3, 7)));
        newGame.board.addPiece(new Bishop(PieceType.BISHOP, Player.WHITE, new Coordinates(5, 7)));
        
        newGame.board.addPiece(new Knight(PieceType.KNIGHT, Player.BLACK, new Coordinates(3, 5)));
        
        newGame.board.getPlayerPieces();
        newGame.board.updateAccessibleFields();
        newGame.board.checkGameState(Player.WHITE);
    }*/
    
}
