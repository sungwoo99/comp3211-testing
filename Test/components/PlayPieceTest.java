package components;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;
import java.util.HashMap;

class PlayPieceTest {

    PlayPiece playPiece = new PlayPiece();
    private HashMap<String, Integer> map_X_P1 = new HashMap<>();
    private HashMap<String, Integer> map_Y_P1 = new HashMap<>();
    private HashMap<String, Integer> map_X_P2 = new HashMap<>();
    private HashMap<String, Integer> map_Y_P2 = new HashMap<>();
    private String[] input;

    /*
    * This is used for checking whether the intended move by the user is valid or not
    * All the different validation checks that are performed are described in details below
    */
    @Test
    void playGamePiece() {
        //First the position of the game pieces of both the players are recorded in the corresponding Row and Column HashMaps
        map_X_P1.put("P1 Tiger", 5); map_X_P1.put("P1 Cat", 3); map_X_P1.put("P1 Dog", 2); map_X_P1.put("P1 Elephant", 3); map_X_P1.put("P1 Wolf", 3); map_X_P1.put("P1 Rat", 6);
        map_Y_P1.put("P1 Tiger", 1); map_Y_P1.put("P1 Cat", 6); map_Y_P1.put("P1 Dog", 5); map_Y_P1.put("P1 Elephant", 2); map_Y_P1.put("P1 Wolf", 3); map_Y_P1.put("P1 Rat", 5);
        map_X_P2.put("P2 Tiger", 5); map_X_P2.put("P2 Lion", 7); map_X_P2.put("P2 Dog", 8); map_X_P2.put("P2 Elephant", 6); map_X_P2.put("P2 Wolf", 2); map_X_P2.put("P2 Rat", 5);
        map_Y_P2.put("P2 Tiger", 7); map_Y_P2.put("P2 Lion", 6); map_Y_P2.put("P2 Dog", 4); map_Y_P2.put("P2 Elephant", 4); map_Y_P2.put("P2 Wolf", 4); map_Y_P2.put("P2 Rat", 5);

        /* An instance of the class "GamePieceDependentValidity" is created
         * Then the class method "invalidPiece" is used to check the validity
         * The implementation details of the method is described in the corresponding unit test for the class
         * If that method returns true, then this corresponding error message will also be returned to the user
         */
        input = new String[]{"P1 Monkey", "B", "2"};
        assertEquals("The game piece is not correct!", playPiece.playGamePiece(input, map_X_P1, map_Y_P1, map_X_P2, map_Y_P2));


        /* An instance of the class "GamePieceDependentValidity" is created
         * Then the class method "moveOutOfBoard" is used to check the validity
         * The implementation details of the method is described in the unit test for the class
         * If that method returns true, then this corresponding error message will also be returned to the user
         */
        input = new String[]{"P1 Cat", "H", "3"};
        assertEquals("The move is out of bounds of the game board!", playPiece.playGamePiece(input, map_X_P1, map_Y_P1, map_X_P2, map_Y_P2));


        /* An instance of the class "GamePieceDependentValidity" is created
         * Then the class method "moveOverWaterSquare" is used to check the validity
         * The implementation details of the method is described in the unit test for the class
         * If that method returns true, then this corresponding error message will also be returned to the user
         */
        input = new String[]{"P2 Tiger", "D", "5"};
        assertEquals("The move over the water square is not valid!", playPiece.playGamePiece(input, map_X_P2, map_Y_P2, map_X_P1, map_Y_P1));


        /* An instance of the class "MoveOnOwnPiece" is created
         * Then the class method "moveValidity" is used to check the validity
         * The implementation details of the method is described in the unit test for the class
         * If that method returns true, then this corresponding error message will also be returned to the user
         */
        input = new String[]{"P1 Elephant", "C", "3"};
        assertEquals("Can not move to its own piece!", playPiece.playGamePiece(input, map_X_P1, map_Y_P1, map_X_P2, map_Y_P2));


        /* An instance of the class "MoveOnWaterSquare" is created
         * Then the class method "moveValidity" is used to check the validity
         * The implementation details of the method is described in the unit test for the class
         * If that method returns true, then this corresponding error message will also be returned to the user
         */
        input = new String[]{"P2 Lion", "F", "6"};
        assertEquals("Only the game piece Rat can move into the water squares!", playPiece.playGamePiece(input, map_X_P2, map_Y_P2, map_X_P1, map_Y_P1));


        /* An instance of the class "MoveMoreThanOneSquare" is created
         * Then the class method "moveValidity" is used to check the validity
         * The implementation details of the method is described in the unit test for the class
         * If that method returns true, then this corresponding error message will also be returned to the user
         */
        input = new String[]{"P2 Dog", "A", "8"};
        assertEquals("Can not move more than one square vertically or horizontally!", playPiece.playGamePiece(input, map_X_P2, map_Y_P2, map_X_P1, map_Y_P1));


        /* An instance of the class "MoveOnOwnDen" is created
         * Then the class method "moveValidity" is used to check the validity
         * The implementation details of the method is described in the unit test for the class
         * If that method returns true, then this corresponding error message will also be returned to the user
         */
        input = new String[]{"P2 Dog", "D", "9"};
        assertEquals("Can not move into own den!", playPiece.playGamePiece(input, map_X_P2, map_Y_P2, map_X_P1, map_Y_P1));


        /* This first checks if there is an opponent's gamePiece on the square that the current player is attempting to move
         * If this is the case, then we will check if the opponent's piece can be captured by the current player's piece or not, by using the class method "canCapturePiece"
         * If that method returns false, then this corresponding error message will be returned to the user
         */
        input = new String[]{"P1 Rat", "D", "6"};
        assertEquals("Move is not valid.", playPiece.playGamePiece(input, map_X_P1, map_Y_P1, map_X_P2, map_Y_P2));


        //If none of the error messages stated above are returned after doing all the validation checks, then the following message will be returned to the user
        input = new String[]{"P1 Tiger", "A", "6"};
        assertEquals("Valid Move", playPiece.playGamePiece(input, map_X_P1, map_Y_P1, map_X_P2, map_Y_P2));

    }

    /*
    * This is used for checking if the current player's gamePiece can capture the opponent player's piece
    * It will check for all the valid methods of capturing a gamePiece
    * If one of those methods are true, then it will return true
    * Or else will return false
    * */
    @Test
    void canCapturePiece() {
        map_X_P1.put("P1 Tiger", 5); map_X_P1.put("P1 Cat", 3); map_X_P1.put("P1 Dog", 2); map_X_P1.put("P1 Elephant", 3); map_X_P1.put("P1 Wolf", 3); map_X_P1.put("P1 Rat", 5); map_X_P1.put("P1 Leopard", 8);
        map_Y_P1.put("P1 Tiger", 1); map_Y_P1.put("P1 Cat", 6); map_Y_P1.put("P1 Dog", 5); map_Y_P1.put("P1 Elephant", 2); map_Y_P1.put("P1 Wolf", 3); map_Y_P1.put("P1 Rat", 4); map_Y_P1.put("P1 Leopard", 4);
        map_X_P2.put("P2 Tiger", 5); map_X_P2.put("P2 Lion", 6); map_X_P2.put("P2 Dog", 8); map_X_P2.put("P2 Elephant", 6); map_X_P2.put("P2 Wolf", 2); map_X_P2.put("P2 Rat", 4);
        map_Y_P2.put("P2 Tiger", 7); map_Y_P2.put("P2 Lion", 1); map_Y_P2.put("P2 Dog", 3); map_Y_P2.put("P2 Elephant", 4); map_Y_P2.put("P2 Wolf", 4); map_Y_P2.put("P2 Rat", 4);


        /* In all the tests below, the first "checkIfPiecePresent" method is used to determine if there is an opponent gamePiece on the square that the current player is trying to move
         * This is done by iterating over the row and column HashMap of the opponent and comparing their positions with the position of the intended move
         * If such a piece is found, then it will return true,or else will return false
         * It also sets the value of a class attribute (called "piecePresent") to the name of the opponent's gamePiece that is being found, so that we can use the canCapturePiece() method later on
         * This class attribute is used in all the below tests to represent the opponent's gamePiece that is present on the square that the current player is trying to move to
         */
        assertEquals("P2 Elephant", playPiece.checkIfPiecePresent(6, "D", map_X_P2, map_Y_P2));

        /* This "checkIfPiecePresent" method is also used to determine if there is an opponent gamePiece on the square that the current user is trying to move
         * The implementation is mostly the same, but instead it will take a character as the parameter for the column position
         * And, it will directly return the name of the opponent piece if it is found, or will return an empty String if not found
         */
        assertTrue(playPiece.checkIfPiecePresent(6, 4, map_X_P2, map_Y_P2));

        /* Used for determining if a Rat piece can capture the opponent's Elephant piece
         * Firstly, using the pieceOnWaterSquare() method, it is checked if both the current and the opponent player's pieces are on land or on a water square
         * If both of the pieces are on land, then it will return true
         */
        assertTrue(playPiece.canCapturePiece("P1 Rat", 6, 4, map_X_P1, map_Y_P1, map_X_P2, map_Y_P2));


        assertEquals("P1 Rat", playPiece.checkIfPiecePresent(5, "D", map_X_P1, map_Y_P1));
        assertTrue(playPiece.checkIfPiecePresent(5, 4, map_X_P1, map_Y_P1));
        /* Used for determining if a Rat piece can capture the opponent's Rat piece
         * Firstly, using the pieceOnWaterSquare() method, it is checked if both the current and the opponent player's Rat piece are on land or on water squares
         * If both of the pieces are on land or on water, then it will return true
         */
        assertTrue(playPiece.canCapturePiece("P2 Rat", 5, 4, map_X_P2, map_Y_P2, map_X_P1, map_Y_P1));


        assertEquals("P2 Wolf", playPiece.checkIfPiecePresent(2, "D", map_X_P2, map_Y_P2));
        assertTrue(playPiece.checkIfPiecePresent(2, 4, map_X_P2, map_Y_P2));
        /* Used for determining if the current player can capture an opponent's piece on its own trap square
         * Here, the opponent player's wolf is on the current player's trap square
         * This is found out by comparing the position of the class attribute set above (opponent's game piece) with the trap square positions of the current player
         * So in this case, it returns true, as the player 1's Dog can capture the P2's wolf, on player 1's own trap square
         */
        assertTrue(playPiece.canCapturePiece("P1 Dog", 2, 4, map_X_P1, map_Y_P1, map_X_P2, map_Y_P2));


        assertEquals("P1 Leopard", playPiece.checkIfPiecePresent(8, "D", map_X_P1, map_Y_P1));
        assertTrue(playPiece.checkIfPiecePresent(8, 4, map_X_P1, map_Y_P1));
        /* Also used for determining if the current player can capture an opponent's piece on its own trap square
         * The implementation is the same as described above, but for testing purposes, a player 2's piece will capture the player 1's piece
         */
        assertTrue(playPiece.canCapturePiece("P2 Dog", 8, 4, map_X_P2, map_Y_P2, map_X_P1, map_Y_P1));


        assertEquals("P1 Tiger", playPiece.checkIfPiecePresent(5, "A", map_X_P1, map_Y_P1));
        assertTrue(playPiece.checkIfPiecePresent(5, 1, map_X_P1, map_Y_P1));
        /* Used for checking the capturing of gamePieces based on their rankings
         * Firstly, the pieceOnWaterSquare() method is used to check if both the current and the opponent player's Rat piece are on land or on water squares
         * Then, their ranking is compared using the gamePiece names
         * If both of the pieces are on land and the current and opponent player's piece is not a Rat and an Elephant respectively, then it will return true
         */
        assertTrue(playPiece.canCapturePiece("P2 Lion", 5, 1, map_X_P2, map_Y_P2, map_X_P1, map_Y_P1));


        assertEquals("P1 Rat", playPiece.checkIfPiecePresent(5, "D", map_X_P1, map_Y_P1));
        assertTrue(playPiece.checkIfPiecePresent(5, 4, map_X_P1, map_Y_P1));
        //returns false as an Elephant is trying to capture the opponent's Rat piece, which is not allowed
        assertFalse(playPiece.canCapturePiece("P2 Elephant", 5, 4, map_X_P2, map_Y_P2, map_X_P1, map_Y_P1));

        //returns an empty string for the gamePiece name, as there are no gamePieces of Player 1 with the position (6, 8)
        assertEquals("", playPiece.checkIfPiecePresent(6, "H", map_X_P1, map_Y_P1));
        //returns false as there are no gamePieces of Player 1 with the position (6, 8)
        assertFalse(playPiece.checkIfPiecePresent(6, 8, map_X_P1, map_Y_P1));
    }

    /*
     * Used for checking if a gamePiece is on a water square
     * The water squares will have some fixed row and column position
     * (row = 4, column = 2), (row = 5, column = 2), (row = 6, column = 2), (row = 4, column = 3), (row = 5, column = 3),.................... (row = 6, column = 6)
     * So the gamePiece positions from the HashMap is compared with the following water square positions
     * */
    @Test
    void pieceOnWaterSquare() {
         map_X_P1.put("P1 Dog", 2); map_X_P1.put("P1 Wolf", 3); map_X_P1.put("P1 Rat", 6);
         map_Y_P1.put("P1 Dog", 5); map_Y_P1.put("P1 Wolf", 3); map_Y_P1.put("P1 Rat", 5);
         map_X_P2.put("P2 Dog", 8); map_X_P2.put("P2 Wolf", 2); map_X_P2.put("P2 Rat", 5);
         map_Y_P2.put("P2 Dog", 4); map_Y_P2.put("P2 Wolf", 4); map_Y_P2.put("P2 Rat", 4);

        //returns true as player 1's Rat is on a water square
        assertTrue(playPiece.pieceOnWaterSquare("P1 Rat", map_X_P1, map_Y_P1));
        //returns false as player 2's Rat is not on a water square
        assertFalse(playPiece.pieceOnWaterSquare("P2 Rat", map_X_P2, map_Y_P2));
    }

    /*
    * Used for determining whether a player has won the game or not
    * */
    @Test
    void decideGameWinner() {
        map_X_P1.put("P1 Elephant", 7);
        map_Y_P1.put("P1 Elephant", 4);
        //returns "exit" as player 1 still has one piece left on the board
        //The row and column HashMap of the players are searched to determine if one player has captured all of its opponent pieces
        assertEquals("exit", playPiece.decideGameWinner("P1", "P1 Elephant", map_X_P1, map_Y_P1, map_X_P2, map_Y_P2));

        //player 1 piece removed for testing purposes and one piece added for player 2
        map_X_P1.remove("P1 Elephant"); map_Y_P1.remove("P1 Elephant");
        map_X_P2.put("P2 Elephant", 4);
        map_Y_P2.put("P2 Elephant", 4);
        //returns "exit" as player 2 still has one piece left on the board
        assertEquals("exit", playPiece.decideGameWinner("P2", "P2 Elephant", map_X_P2, map_Y_P2, map_X_P1, map_Y_P1));

        map_X_P1.put("P1 Elephant", 9);
        map_Y_P1.put("P1 Elephant", 4);
        //returns "exit" as the Elephant piece of player 1 has moved into the opponent's den
        //This is found out by comparing the intended move of the Elephant with the position of the opponent's den (i.e. row = 9 and column = 4)
        assertEquals("exit", playPiece.decideGameWinner("P1", "P1 Elephant", map_X_P1, map_Y_P1, map_X_P2, map_Y_P2));

        map_X_P1.replace("P1 Elephant", 7); map_Y_P1.replace("P1 Elephant", 4);
        map_X_P2.replace("P2 Elephant", 1);
        //returns "exit" as the Elephant piece of player 2 has moved into the opponent's den square
        //Found out by comparing the intended move of the Elephant with the position of the opponent's den (i.e. row = 1 and column = 4)
        assertEquals("exit", playPiece.decideGameWinner("P2", "P2 Elephant", map_X_P2, map_Y_P2, map_X_P1, map_Y_P1));

        //positions of both the player's elephants are changed
        map_X_P2.replace("P2 Elephant", 5);
        //returns "no winner" as both of the players still have at least one gamePiece left
        assertEquals("No Winner", playPiece.decideGameWinner("P1", "P1 Elephant", map_X_P1, map_Y_P1, map_X_P2, map_Y_P2));
        assertEquals("No Winner", playPiece.decideGameWinner("P2", "P2 Elephant", map_X_P2, map_Y_P2, map_X_P1, map_Y_P1));
    }

    //Used for checking if the character representation of column is correctly translated to its integer position
    @Test
    void setColumn() {
        assertEquals(1, playPiece.setColumn('A'));
        assertEquals(2, playPiece.setColumn('B'));
        assertEquals(3, playPiece.setColumn('C'));
        assertEquals(4, playPiece.setColumn('D'));
        assertEquals(5, playPiece.setColumn('E'));
        assertEquals(6, playPiece.setColumn('F'));
        assertEquals(7, playPiece.setColumn('G'));
        //will return 0 if the column position is out of bounds of the board
        assertEquals(0, playPiece.setColumn('H'));
    }
}