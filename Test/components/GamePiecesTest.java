package components;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;
import java.util.HashMap;

class GamePiecesTest {
    GamePieces gamePiece = new GamePieces();
    private HashMap<String, Integer> map_X_P1 = new HashMap<>();
    private HashMap<String, Integer> map_Y_P1 = new HashMap<>();
    private HashMap<String, Integer> map_X_P2 = new HashMap<>();
    private HashMap<String, Integer> map_Y_P2 = new HashMap<>();

    /*
    * Removes the gamePiece from the corresponding player's HashMap
    * */
    @Test
    void removeGamePiece() {
        map_X_P1.put("P1 Tiger", 1); map_X_P1.put("P1 Lion", 1); map_X_P1.put("P1 Cat", 2); map_X_P1.put("P1 Dog", 2); map_X_P1.put("P1 Elephant", 3); map_X_P1.put("P1 Wolf", 3); map_X_P1.put("P1 Leopard", 3); map_X_P1.put("P1 Rat", 3);
        map_Y_P1.put("P1 Tiger", 1); map_Y_P1.put("P1 Lion", 7); map_Y_P1.put("P1 Cat", 2); map_Y_P1.put("P1 Dog", 6); map_Y_P1.put("P1 Elephant", 1); map_Y_P1.put("P1 Wolf", 3); map_Y_P1.put("P1 Leopard", 5); map_Y_P1.put("P1 Rat", 7);

        map_X_P2.put("P2 Tiger", 9); map_X_P2.put("P2 Lion", 9); map_X_P2.put("P2 Cat", 8); map_X_P2.put("P2 Dog", 8); map_X_P2.put("P2 Elephant", 7); map_X_P2.put("P2 Wolf", 7); map_X_P2.put("P2 Leopard", 7); map_X_P2.put("P2 Rat", 7);
        map_Y_P2.put("P2 Tiger", 7); map_Y_P2.put("P2 Lion", 1); map_Y_P2.put("P2 Cat", 6); map_Y_P2.put("P2 Dog", 2); map_Y_P2.put("P2 Elephant", 7); map_Y_P2.put("P2 Wolf", 5); map_Y_P2.put("P2 Leopard", 3); map_Y_P2.put("P2 Rat", 1);

        //Using the inbuilt remove method, the gamePiece Rat will be removed from the row and column HashMap of player 1
        gamePiece.removeGamePiece("P1", "P1 Rat");
        //Also removing the piece from the initialized HashMap here, for testing purposes
        map_X_P1.remove("P1 Rat"); map_Y_P1.remove("P1 Rat");
        assertEquals(map_X_P1, gamePiece.getXMap("P1"));
        assertEquals(map_Y_P1, gamePiece.getYMap("P1"));

        //Same implementation, but instead removing the gamePiece for player 2
        gamePiece.removeGamePiece("P2", "P2 Rat");
        map_X_P2.remove("P2 Rat"); map_Y_P2.remove("P2 Rat");
        assertEquals(map_X_P2, gamePiece.getXMap("P2"));
        assertEquals(map_Y_P2, gamePiece.getYMap("P2"));
    }

    /*
    * Used for changing the position of a player's gamePiece after each move
    * */
    @Test
    void setMap() {
        map_X_P1.put("P1 Tiger", 1); map_X_P1.put("P1 Lion", 1); map_X_P1.put("P1 Cat", 2); map_X_P1.put("P1 Dog", 2); map_X_P1.put("P1 Elephant", 3); map_X_P1.put("P1 Wolf", 3); map_X_P1.put("P1 Leopard", 3); map_X_P1.put("P1 Rat", 3);
        map_Y_P1.put("P1 Tiger", 1); map_Y_P1.put("P1 Lion", 7); map_Y_P1.put("P1 Cat", 2); map_Y_P1.put("P1 Dog", 6); map_Y_P1.put("P1 Elephant", 1); map_Y_P1.put("P1 Wolf", 3); map_Y_P1.put("P1 Leopard", 5); map_Y_P1.put("P1 Rat", 7);

        map_X_P2.put("P2 Tiger", 9); map_X_P2.put("P2 Lion", 9); map_X_P2.put("P2 Cat", 8); map_X_P2.put("P2 Dog", 8); map_X_P2.put("P2 Elephant", 7); map_X_P2.put("P2 Wolf", 7); map_X_P2.put("P2 Leopard", 7); map_X_P2.put("P2 Rat", 7);
        map_Y_P2.put("P2 Tiger", 7); map_Y_P2.put("P2 Lion", 1); map_Y_P2.put("P2 Cat", 6); map_Y_P2.put("P2 Dog", 2); map_Y_P2.put("P2 Elephant", 7); map_Y_P2.put("P2 Wolf", 5); map_Y_P2.put("P2 Leopard", 3); map_Y_P2.put("P2 Rat", 1);

        //Using the inbuilt replace method, the row and column position of the gamePiece Rat will be changed in the HashMap for player 1
        //The move will be checked for validity first before calling this class method
        gamePiece.setMap("P1", "P1 Rat", 6, "C");
        //Same changes done to the initialized HashMap here, for testing purposes
        map_X_P1.replace("P1 Rat", 6); map_Y_P1.replace("P1 Rat", 3);
        assertEquals(map_X_P1, gamePiece.getXMap("P1"));
        assertEquals(map_Y_P1, gamePiece.getYMap("P1"));

        //Same implementation, but instead the position of gamePiece for player 2 is changed
        gamePiece.setMap("P2", "P2 Rat", 5, "D");
        map_X_P2.replace("P2 Rat", 5); map_Y_P2.replace("P2 Rat", 4);
        assertEquals(map_X_P2, gamePiece.getXMap("P2"));
        assertEquals(map_Y_P2, gamePiece.getYMap("P2"));
    }

    //Used for checking if the character representation of column is correctly translated to its integer position
    @Test
    void setColumn() {
        assertEquals(1, gamePiece.setColumn('A'));
        assertEquals(2, gamePiece.setColumn('B'));
        assertEquals(3, gamePiece.setColumn('C'));
        assertEquals(4, gamePiece.setColumn('D'));
        assertEquals(5, gamePiece.setColumn('E'));
        assertEquals(6, gamePiece.setColumn('F'));
        assertEquals(7, gamePiece.setColumn('G'));
        //will return 0 if the column position is out of bounds of the board
        assertEquals(0, gamePiece.setColumn('H'));
    }
}