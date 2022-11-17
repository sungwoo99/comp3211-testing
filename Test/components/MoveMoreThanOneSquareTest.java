package components;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;
import java.util.HashMap;

class MoveMoreThanOneSquareTest {
    MoveMoreThanOneSquare moveMoreThanOneSquare = new MoveMoreThanOneSquare();
    private HashMap<String, Integer> map_X_P1 = new HashMap<>();
    private HashMap<String, Integer> map_Y_P1 = new HashMap<>();

    /*
    * This is used to test if the player is attempting to move more than one square either horizontally or vertically
    * We will check the difference between the gamePiece's current row position (from the HashMap) with the intended row position
    * Similarly, this difference is found for the column position as well
    * If either one of these differences is more than 1, then it will return true, or else will return false
    * Additionally, if the differences are satisfied, then we will check if the player is intending to move diagonally on the board
    * This is done by finding out if both the intended game move of the row and column position is changing, by comparing with the current row and column position
    * */
    @Test
    void moveValidity() {
        map_X_P1.put("P1 Wolf", 2);
        map_Y_P1.put("P1 Wolf", 1);
        //returns true as Wolf is trying to move more than one square
        assertTrue(moveMoreThanOneSquare.moveValidity("P1 Wolf", 4, 1, map_X_P1, map_Y_P1));
        //returns true as Wolf is trying to move diagonally
        assertTrue(moveMoreThanOneSquare.moveValidity("P1 Wolf", 3, 2, map_X_P1, map_Y_P1));
        //returns false as Wolf is only moving one square vertically
        assertFalse(moveMoreThanOneSquare.moveValidity("P1 Wolf", 3, 1, map_X_P1, map_Y_P1));
    }
}