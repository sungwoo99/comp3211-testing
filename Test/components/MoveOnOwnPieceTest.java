package components;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;
import java.util.HashMap;

class MoveOnOwnPieceTest {

    MoveOnOwnPiece moveOnOwnPiece = new MoveOnOwnPiece();
    private HashMap<String, Integer> map_X_P1 = new HashMap<>();
    private HashMap<String, Integer> map_Y_P1 = new HashMap<>();

    /* Checking whether the intended move by the current player is attempting to move to a position, which already consists of another game piece of the same player
     * This is done by iterating through the row and column Hashmap of the current player
     * Then we can find out if any of the remaining game pieces have the same row and column position of the inputted positions
     * It will return true if any such game piece is found, or else will return false
     */
    @Test
    void moveValidity() {
        map_X_P1.put("P1 Wolf", 3); map_X_P1.put("P1 Rat", 3);
        map_Y_P1.put("P1 Wolf", 3); map_Y_P1.put("P1 Rat", 4);

        //Returns True as Wolf is attempting to move to a square that already has the Rat piece
        assertTrue(moveOnOwnPiece.moveValidity("P1 Rat", 3, 4, map_X_P1, map_Y_P1));

        //Returns True as Wolf is attempting to move to its current position
        assertTrue(moveOnOwnPiece.moveValidity("P1 Rat", 3, 3, map_X_P1, map_Y_P1));

        //Returns False as the move to the intended square is not occupied by any other gamePieces
        assertFalse(moveOnOwnPiece.moveValidity("P1 Rat", 4, 4, map_X_P1, map_Y_P1));
    }
}