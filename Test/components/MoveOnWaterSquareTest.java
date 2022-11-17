package components;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;
import java.util.HashMap;

class MoveOnWaterSquareTest {
    MoveOnWaterSquare moveOnWaterSquare = new MoveOnWaterSquare();
    private HashMap<String, Integer> map_X_P1 = new HashMap<>();
    private HashMap<String, Integer> map_Y_P1 = new HashMap<>();

    /* This validates if a game piece other than the Rat is trying to move to a water square
     * The positions of the water squares will have some of the following row and column positions
     * (row = 4, column = 2), (row = 5, column = 2), (row = 6, column = 2), (row = 4, column = 3), (row = 5, column = 3),.................... (row = 6, column = 6)
     * The inputted position of the gamePiece is compared with all the following positions of the water squares mentioned above
     * If the position matches and the gamePiece is not a Rat, then it will return false, or else will return true
     */
    @Test
    void moveValidity() {
        map_X_P1.put("P1 Tiger", 5); map_X_P1.put("P1 Rat", 4);
        map_Y_P1.put("P1 Tiger", 4); map_Y_P1.put("P1 Rat", 4);

        //returns true as the gamePiece Wolf is attempting to move to a water square
        assertTrue(moveOnWaterSquare.moveValidity("P1 Wolf", 5, 5, map_X_P1, map_Y_P1));
        //returns false as the gamePiece Rat is allowed to move to a water square
        assertFalse(moveOnWaterSquare.moveValidity("P1 Rat", 4, 5, map_X_P1, map_Y_P1));
    }
}