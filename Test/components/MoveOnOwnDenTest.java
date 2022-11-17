package components;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;
import java.util.HashMap;

class MoveOnOwnDenTest {
    MoveOnOwnDen moveOnOwnDen = new MoveOnOwnDen();
    private HashMap<String, Integer> map_X_P1 = new HashMap<>();
    private HashMap<String, Integer> map_Y_P1 = new HashMap<>();
    private HashMap<String, Integer> map_X_P2 = new HashMap<>();
    private HashMap<String, Integer> map_Y_P2 = new HashMap<>();

    /*
     * Checking whether the player is attempting to move to its own den
     * The position of the dens are fixed for both of the players
     * Firstly, we find out the current player's turn
     * Then the position of the intended move by the player is compared with that player's den position
     * If it matches, it will return true, or will return false
     */
    @Test
    void moveValidity() {
        map_X_P1.put("P1 Tiger", 1);
        map_Y_P1.put("P1 Tiger", 3);

        map_X_P2.put("P2 Tiger", 9);
        map_Y_P2.put("P2 Tiger", 3);

        //returns true as P1 is attempting to move to its own den position (i.e. 1, 4)
        assertTrue(moveOnOwnDen.moveValidity("P1 Tiger", 1, 4, map_X_P1, map_Y_P1));
        //returns true as P2 is attempting to move to its own den position (i.e. 9, 4)
        assertTrue(moveOnOwnDen.moveValidity("P2 Tiger", 9, 4, map_X_P2, map_Y_P2));
        //returns false as P1 is not moving its piece to its own den
        assertFalse(moveOnOwnDen.moveValidity("P1 Tiger", 2, 3, map_X_P1, map_Y_P1));

    }
}