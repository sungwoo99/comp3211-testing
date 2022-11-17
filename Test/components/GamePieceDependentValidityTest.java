package components;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;
import java.util.HashMap;


class GamePieceDependentValidityTest {
    GamePieceDependentValidity gamePieceDependentValidity = new GamePieceDependentValidity();
    private HashMap<String, Integer> map_X_P1 = new HashMap<>();
    private HashMap<String, Integer> map_Y_P1 = new HashMap<>();
    private HashMap<String, Integer> map_X_P2 = new HashMap<>();
    private HashMap<String, Integer> map_Y_P2 = new HashMap<>();

    /* Checking for an incorrect game piece, i.e. Monkey
     * Here, all the valid game piece names will be compared with the game piece name inputted by the player
     * If the piece is not found, it will return true or else will return false
     */
    @Test
    void invalidPiece() {
        //returns true as Monkey is not a valid gamePiece
        assertTrue(gamePieceDependentValidity.invalidPiece("P1 Monkey"));
        //returns false as Elephant is a valid gamePiece
        assertFalse(gamePieceDependentValidity.invalidPiece("P1 Elephant"));
    }

    /* This will check whether the intended move is within the bounds of the game board
     * Firstly, the character inputted for the column position is converted to the corresponding integer value (A --> 1, B --> 2, C --> 3..........., G --> 7)
     * In the game, the rows are considered to be in the range of 1 to 9 and the columns are between 1 to 7
     * Then the inputted rows and columns are compared with these corresponding ranges and if either one of the intended positions are not in this range, then it will return true or else will return false
     */
    @Test
    void moveOutOfBoard() {
        //returns true as position (10, 3) is out of bounds of the gameBoard
        assertTrue(gamePieceDependentValidity.moveOutOfBoard(10, 3));
        //returns False as (9, 3) is within the bounds of the board
        assertFalse(gamePieceDependentValidity.moveOutOfBoard(9, 3));
    }

    /* This finds out if the Tiger and the Lion gamePiece can cross the water squares vertically or horizontally
     * There are some fixed positions from which the lion and the tiger can cross the river
     * These include moving from the position (row = 7, column = 2) to position (row = 3, column = 2) or moving from (4, 1) to (4, 4), and so on
     * The current position from the HashMap and the intended move position from user input is compared, in order to find out if it matches with the following combinations
     * If the move matches one of the following combinations, then the HashMaps of the current and the opponent player is compared to find any Rat piece in the intervening squares
     * This is done by iterating all the row and column HashMaps and comparing with the X and Y position of the intervening water squares
     * If a current or opponent player's Rat gamePiece is found in any such squares, then it will return true, or else will return false
     */
    @Test
    void moveOverWaterSquare() {
        //The gamePieces are put into the corresponding HashMaps for testing
        map_X_P1.put("P1 Tiger", 5); map_X_P1.put("P1 Lion", 3); map_X_P1.put("P1 Rat", 6);
        map_Y_P1.put("P1 Tiger", 1); map_Y_P1.put("P1 Lion", 2); map_Y_P1.put("P1 Rat", 2);
        map_X_P2.put("P2 Tiger", 5); map_X_P2.put("P2 Lion", 7); map_X_P2.put("P2 Rat", 5);
        map_Y_P2.put("P2 Tiger", 7); map_Y_P2.put("P2 Lion", 6); map_Y_P2.put("P2 Rat", 5);

        //returns true as a Rat piece is present one of the intervening squares, while the Lion is trying to cross vertically
        assertTrue(gamePieceDependentValidity.moveOverWaterSquare("P1 Lion", 7, 2, map_X_P1, map_Y_P1, map_X_P2, map_Y_P2));
        //returns true as a Rat piece is present one of the intervening squares, while the Tiger is trying to cross horizontally
        assertTrue(gamePieceDependentValidity.moveOverWaterSquare("P2 Tiger", 5, 4, map_X_P2, map_Y_P2, map_X_P1, map_Y_P1));

        //returns false as the Tiger is on a correct square for crossing and there are no intervening Rat pieces
        assertFalse(gamePieceDependentValidity.moveOverWaterSquare("P1 Tiger", 5, 4, map_X_P1, map_Y_P1, map_X_P2, map_Y_P2));
        //returns false as the Lion is on a correct square for crossing and there are no intervening Rat pieces
        assertFalse(gamePieceDependentValidity.moveOverWaterSquare("P2 Lion", 3, 6, map_X_P2, map_Y_P2, map_X_P1, map_Y_P1));
    }
}