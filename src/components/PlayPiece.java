package components;
import java.util.HashMap;
import java.lang.Math;


abstract class checkMoveValidity{
    public abstract boolean moveValidity(String gamePiece, Integer row, Integer column, HashMap<String, Integer> map_X_currPlayer, HashMap<String, Integer> map_Y_currPlayer);
}

//Checks whether the intended move by the current player is attempting to move to a position, which already consists of another game piece of the same player
//More implementation details can be found in the corresponding unit test for the class
class MoveOnOwnPiece extends checkMoveValidity{
    public boolean moveValidity(String gamePiece, Integer row, Integer column, HashMap<String, Integer> map_X_currPlayer, HashMap<String, Integer> map_Y_currPlayer){
        if(map_X_currPlayer.get(gamePiece) == row && map_Y_currPlayer.get(gamePiece) == column){
            return true;
        }
        for(String animal : map_X_currPlayer.keySet()){
            if(map_X_currPlayer.get(animal) == row && map_Y_currPlayer.get(animal) == column && !animal.equals(gamePiece)){
                return true;
            }
        }
        return false;
    }
}

//This is used to test if the player is attempting to move more than one square either horizontally or vertically
//More implementation details can be found in the corresponding unit test for the class
class MoveMoreThanOneSquare extends checkMoveValidity {

    public boolean moveValidity(String gamePiece, Integer row, Integer column, HashMap<String, Integer> map_X_currPlayer, HashMap<String, Integer> map_Y_currPlayer){
        if(Math.abs(map_X_currPlayer.get(gamePiece) - row) > 1 || Math.abs(map_Y_currPlayer.get(gamePiece) - column) > 1){
            return true;
        }
        else if((Math.abs(map_X_currPlayer.get(gamePiece) - row) == 1 && map_Y_currPlayer.get(gamePiece) != column) || (Math.abs(map_Y_currPlayer.get(gamePiece) - column) == 1 && map_X_currPlayer.get(gamePiece) != row)){
            return true;
        }
        return false;
    }
}

//This validates if a game piece other than the Rat is trying to move to a water square
//More implementation details can be found in the corresponding unit test for the class
class MoveOnWaterSquare extends checkMoveValidity {
    public boolean moveValidity(String gamePiece, Integer row, Integer column, HashMap<String, Integer> map_X_currPlayer, HashMap<String, Integer> map_Y_currPlayer){
        if((!gamePiece.equals("P1 Rat") && !gamePiece.equals("P2 Rat")) && (row == 4 || row == 5 || row == 6) && (column == 2 || column == 3 || column == 5 || column == 6)){
            return true;
        }
        return false;
    }
}

//Checking whether the player is attempting to move to its own den
//More implementation details can be found in the corresponding unit test for the class
class MoveOnOwnDen extends checkMoveValidity {
    public boolean moveValidity(String gamePiece, Integer row, Integer column, HashMap<String, Integer> map_X_currPlayer, HashMap<String, Integer> map_Y_currPlayer){
        String player = gamePiece.split("\\s+")[0];
        if(player.equals("P1")){
            if(row == 1 && column == 4){
                return true;
            }
        } else {
            if(row == 9 && column == 4){
                return true;
            }
        }
        return false;
    }
}

//More implementation details about each of the methods can be found in the corresponding unit tests for the class
class GamePieceDependentValidity {
    public Boolean onValidSquareForCrossingRiver = false;

    //Checking for an incorrect game piece, i.e. Monkey
    public boolean invalidPiece(String gamePiece){
        String animal = gamePiece.split("\\s+")[1];
        if(!(animal.equals("Elephant") || animal.equals("Lion") || animal.equals("Tiger") || animal.equals("Leopard") || animal.equals("Wolf") || animal.equals("Dog") || animal.equals("Cat") || animal.equals("Rat"))){
            return true;
        }
        return false;
    }

    //This will check whether the intended move is within the bounds of the game board
    public boolean moveOutOfBoard(Integer row, Integer column){
        if(row < 1 || row > 9 || column == 0){
            return true;
        }
        return false;
    }

    //This finds out if the Tiger and the Lion gamePiece can cross the water squares vertically or horizontally
    public boolean moveOverWaterSquare(String gamePiece, Integer row, Integer column, HashMap<String, Integer> map_X_currPlayer, HashMap<String, Integer> map_Y_currPlayer, HashMap<String, Integer> map_X_prevPlayer, HashMap<String, Integer> map_Y_prevPlayer){
        String currPlayerRat = "";
        String prevPlayerRat = "";
        Integer minRowOrColumn;
        if(gamePiece.split("\\s+")[0].equals("P1")){
            currPlayerRat = "P1 Rat";
            prevPlayerRat = "P2 Rat";
        } else {
            currPlayerRat = "P2 Rat";
            prevPlayerRat = "P1 Rat";
        }

        if( (gamePiece.split("\\s+")[1].equals("Tiger") || gamePiece.split("\\s+")[1].equals("Lion"))
        && ( (Math.abs(map_X_currPlayer.get(gamePiece) - row) - 1 == 3 && map_Y_currPlayer.get(gamePiece) == column) ||
             (Math.abs(map_Y_currPlayer.get(gamePiece) - column) - 1 == 2 && map_X_currPlayer.get(gamePiece) == row) )
        )
        {
            //if the Tiger or the Lion piece is attempting to cross the water squares vertically
            if(
                (Math.abs(map_X_currPlayer.get(gamePiece) - row) - 1 == 3 && map_Y_currPlayer.get(gamePiece) == column) &&
                (column == 2 || column == 3 || column == 5 || column == 6) &&
                ((map_X_currPlayer.get(gamePiece) == 7 && row == 3) || (map_X_currPlayer.get(gamePiece) == 3 && row == 7))
            ){
                onValidSquareForCrossingRiver = true;
                minRowOrColumn = Math.min(map_X_currPlayer.get(gamePiece), row) + 1;

                //checking for Rat pieces in the intervening water squares
                for(int i=minRowOrColumn; i<minRowOrColumn+3; i++){
                    if(
                        (map_X_currPlayer.get(currPlayerRat) == i && map_Y_currPlayer.get(currPlayerRat) == column) ||
                        (map_X_prevPlayer.get(prevPlayerRat) == i && map_Y_prevPlayer.get(prevPlayerRat) == column)
                    ){
                        return true;
                    }
                }
            }

            //if the Tiger or the Lion piece is attempting to cross the water squares horizontally
            else if(
                (Math.abs(map_Y_currPlayer.get(gamePiece) - column) - 1 == 2 && map_X_currPlayer.get(gamePiece) == row) &&
                (row == 4 || row == 5 || row == 6) &&
                (
                    (map_Y_currPlayer.get(gamePiece) == 1 && column == 4) ||
                    (map_Y_currPlayer.get(gamePiece) == 4 && column == 7) ||
                    (map_Y_currPlayer.get(gamePiece) == 4 && column == 1) ||
                    (map_Y_currPlayer.get(gamePiece) == 7 && column == 4)
                 )
            ){
                onValidSquareForCrossingRiver = true;
                minRowOrColumn = Math.min(map_Y_currPlayer.get(gamePiece), column) + 1;

                //checking for Rat pieces in the intervening water squares
                for(int i=minRowOrColumn; i<minRowOrColumn+2; i++){
                    if(
                        (map_X_currPlayer.get(currPlayerRat) == row && map_Y_currPlayer.get(currPlayerRat) == i) ||
                        (map_X_prevPlayer.get(prevPlayerRat) == row && map_Y_prevPlayer.get(prevPlayerRat) == i)
                    ){
                        return true;
                    }
                }
            }
        }
        return false;
    }
}

public class PlayPiece {
    private HashMap<String, Integer> pieceRank = new HashMap<>();
    private String piecePresent = "";

    //setting the gamePiece ranks
    public PlayPiece() {
        pieceRank.put("Elephant", 8);
        pieceRank.put("Lion", 7);
        pieceRank.put("Tiger", 6);
        pieceRank.put("Leopard", 5);
        pieceRank.put("Wolf", 4);
        pieceRank.put("Dog", 3);
        pieceRank.put("Cat", 2);
        pieceRank.put("Rat", 1);
    }

    public String playGamePiece(String[] input, HashMap<String, Integer> map_X_currPlayer, HashMap<String, Integer> map_Y_currPlayer, HashMap<String, Integer> map_X_prevPlayer, HashMap<String, Integer> map_Y_prevPlayer){
        MoveOnOwnPiece moveOnOwnPiece = new MoveOnOwnPiece();
        MoveMoreThanOneSquare moveMoreThanOneSquare = new MoveMoreThanOneSquare();
        MoveOnWaterSquare moveOnWaterSquare = new MoveOnWaterSquare();
        MoveOnOwnDen moveOnOwnDen = new MoveOnOwnDen();
        GamePieceDependentValidity gamePieceDependentValidity = new GamePieceDependentValidity();

        Integer column = setColumn(input[1].charAt(0));
        Integer row = Integer.parseInt(input[2]);

        //Doing validation checks for each of the player's moves
        //More implementation details about each of the methods can be found in the corresponding unit tests for the class
        if(gamePieceDependentValidity.invalidPiece(input[0])){
            return("The game piece is not correct!");
        }
        else if(gamePieceDependentValidity.moveOutOfBoard(row, column)){
            return("The move is out of bounds of the game board!");
        }
        else if(moveOnOwnPiece.moveValidity(input[0], row, column, map_X_currPlayer, map_Y_currPlayer)){
            return("Can not move to its own piece!");
        }
        else if(moveOnWaterSquare.moveValidity(input[0], row, column, map_X_currPlayer, map_Y_currPlayer)){
            return("Only the game piece Rat can move into the water squares!");
        }
        else if(moveMoreThanOneSquare.moveValidity(input[0], row, column, map_X_currPlayer, map_Y_currPlayer)){
            if(gamePieceDependentValidity.moveOverWaterSquare(input[0], row, column, map_X_currPlayer, map_Y_currPlayer, map_X_prevPlayer, map_Y_prevPlayer)){
                return("The move over the water square is not valid!");
            }
            else if(!gamePieceDependentValidity.onValidSquareForCrossingRiver){
                return("Can not move more than one square vertically or horizontally!");
            }
        }
        else if(moveOnOwnDen.moveValidity(input[0], row, column, map_X_currPlayer, map_Y_currPlayer)){
            return("Can not move into own den!");
        }
        else if(checkIfPiecePresent(row, column, map_X_prevPlayer, map_Y_prevPlayer)){
            if(!canCapturePiece(input[0], row, column, map_X_currPlayer, map_Y_currPlayer, map_X_prevPlayer, map_Y_prevPlayer)){
                return("Move is not valid.");
            }
        }
        return "Valid Move";

    }

    //This is used for checking if the current player's gamePiece can capture the opponent player's piece
    //More implementation details of this method can be found in the corresponding unit test for the class
    public boolean canCapturePiece(String gamePiece, Integer row, Integer column, HashMap<String, Integer> map_X_currPlayer, HashMap<String, Integer> map_Y_currPlayer, HashMap<String, Integer> map_X_prevPlayer, HashMap<String, Integer> map_Y_prevPlayer){

        //The Rat can capture the opponent's Elephant piece, if both of them are on land squares
        if(gamePiece.split("\\s+")[1].equals("Rat") && piecePresent.split("\\s+")[1].equals("Elephant") &&
            !pieceOnWaterSquare(gamePiece, map_X_currPlayer, map_Y_currPlayer)) {

            System.out.println("The Rat piece captures the opponent's Elephant piece");
            return true;
        }
        //The Rat can capture the opponent's Rat piece, if both of them are on land or on water squares
        else if(gamePiece.split("\\s+")[1].equals("Rat") && piecePresent.split("\\s+")[1].equals("Rat") &&
        ((pieceOnWaterSquare(gamePiece, map_X_currPlayer, map_Y_currPlayer) && pieceOnWaterSquare(piecePresent, map_X_prevPlayer, map_Y_prevPlayer)) ||
         (!pieceOnWaterSquare(gamePiece, map_X_currPlayer, map_Y_currPlayer) && !pieceOnWaterSquare(piecePresent, map_X_prevPlayer, map_Y_prevPlayer)))) {

            System.out.println("The Rat piece captures the opponent's Rat piece");
            return true;
        }
        //if Player 1 moves into Player 2's den
        else if(gamePiece.split("\\s+")[0].equals("P1") && ((map_X_prevPlayer.get(piecePresent) == 1 && (map_Y_prevPlayer.get(piecePresent) == 3 || map_Y_prevPlayer.get(piecePresent) == 5)) ||
                                                            (map_X_prevPlayer.get(piecePresent) == 2 && map_Y_prevPlayer.get(piecePresent) == 4))
        ){
            return true;
        }
        //if Player 2 moves into Player 1's den
        else if(gamePiece.split("\\s+")[0].equals("P2") && ((map_X_prevPlayer.get(piecePresent) == 9 && (map_Y_prevPlayer.get(piecePresent) == 3 || map_Y_prevPlayer.get(piecePresent) == 5)) ||
                                                            (map_X_prevPlayer.get(piecePresent) == 8 && map_Y_prevPlayer.get(piecePresent) == 4))
        ){
            return true;
        }
        //if the current player can capture the opponent's gamePiece with a higher or equal ranked piece
        else if((pieceRank.get(gamePiece.split("\\s+")[1]) >= pieceRank.get(piecePresent.split("\\s+")[1]))
                && !pieceOnWaterSquare(gamePiece, map_X_currPlayer, map_Y_currPlayer) && !pieceOnWaterSquare(piecePresent, map_X_prevPlayer, map_Y_prevPlayer)
                && !gamePiece.split("\\s+")[1].equals("Elephant") && !piecePresent.split("\\s+")[1].equals("Rat")){
            return true;
        }
        return false;
    }

    //Used for checking if a gamePiece is on a water square
    public boolean pieceOnWaterSquare(String gamePiece, HashMap<String, Integer> map_X, HashMap<String, Integer> map_Y){
        if((map_X.get(gamePiece) == 4 || map_X.get(gamePiece) == 5 || map_X.get(gamePiece) == 6) && (map_Y.get(gamePiece) == 2 || map_Y.get(gamePiece) == 3 || map_Y.get(gamePiece) == 5)){
            return true;
        }
        return false;
    }

    //Used to determine if there is an opponent gamePiece on the square that the current player is trying to move
    //If such a piece is found, then it will return true,or else will return false
    //It also sets the value of the class attribute (called "piecePresent") to the name of the opponent's gamePiece that is being found
    public boolean checkIfPiecePresent(Integer row, Integer column, HashMap<String, Integer> map_X_prevPlayer, HashMap<String, Integer> map_Y_prevPlayer){
        for(String animal : map_X_prevPlayer.keySet()){
            if(map_X_prevPlayer.get(animal) == row && map_Y_prevPlayer.get(animal) == column){
                piecePresent = animal;
                return true;
            }
        }
        return false;
    }

    //Used to determine if there is an opponent gamePiece on the square that the current player is trying to move
    //it will return the name of the opponent piece if it is found, or will return an empty String if not found
    public String checkIfPiecePresent(Integer row, String column, HashMap<String, Integer> map_X_prevPlayer, HashMap<String, Integer> map_Y_prevPlayer){
        for(String animal : map_X_prevPlayer.keySet()){
            if(map_X_prevPlayer.get(animal) == row && map_Y_prevPlayer.get(animal) == setColumn(column.charAt(0))){
                return animal;
            }
        }
        return "";
    }

    //Used for determining whether a player has won the game or not
    public String decideGameWinner(String Player, String gamePiece, HashMap<String, Integer> map_X_currPlayer, HashMap<String, Integer> map_Y_currPlayer, HashMap<String, Integer> map_X_prevPlayer, HashMap<String, Integer> map_Y_prevPlayer){
        //if one of the player's HashMap is empty (no pieces left on the board)
        if(map_X_prevPlayer.isEmpty() && map_Y_prevPlayer.isEmpty()){
            if(Player.equals("P1")){
                System.out.println("Player 1 has won after capturing all the Player 2's pieces.");
            } else {
                System.out.println("Player 2 has won after capturing all the Player 1's pieces.");
            }
            return "exit";
        }
        //if the current player is able to move into the opponent player's den
        else if((map_X_currPlayer.get(gamePiece) == 1 && map_Y_currPlayer.get(gamePiece) == 4) || (map_X_currPlayer.get(gamePiece) == 9 && map_Y_currPlayer.get(gamePiece) == 4)){
            if(Player.equals("P1")){
                System.out.println("Player 1 has won after moving into Player 2's Den.");
            } else {
                System.out.println("Player 2 has won after moving into Player 1's Den.");
            }
            return "exit";
        }
        return "No Winner";
    }

    //returns the integer position of the column, from the character position
    public Integer setColumn(Character input){
        switch(input){
            case 'A':
                return 1;

            case 'B':
                return 2;

            case 'C':
                return 3;

            case 'D':
                return 4;

            case 'E':
                return 5;

            case 'F':
                return 6;

            case 'G':
                return 7;

            default:
                return 0;
        }
    }
}
