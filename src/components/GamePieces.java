package components;
import java.util.*;


public class GamePieces {

    //HashMaps for storing all the row and column positions separately, for both of the players
    private HashMap<String, Integer> map_X_P1 = new HashMap<>();
    private HashMap<String, Integer> map_Y_P1 = new HashMap<>();
    private HashMap<String, Integer> map_X_P2 = new HashMap<>();
    private HashMap<String, Integer> map_Y_P2 = new HashMap<>();

    //setting the initial positions
    public GamePieces() {
        map_X_P1.put("P1 Tiger", 1);
        map_X_P1.put("P1 Lion", 1);
        map_X_P1.put("P1 Cat", 2);
        map_X_P1.put("P1 Dog", 2);
        map_X_P1.put("P1 Elephant", 3);
        map_X_P1.put("P1 Wolf", 3);
        map_X_P1.put("P1 Leopard", 3);
        map_X_P1.put("P1 Rat", 3);

        map_Y_P1.put("P1 Tiger", 1);
        map_Y_P1.put("P1 Lion", 7);
        map_Y_P1.put("P1 Cat", 2);
        map_Y_P1.put("P1 Dog", 6);
        map_Y_P1.put("P1 Elephant", 1);
        map_Y_P1.put("P1 Wolf", 3);
        map_Y_P1.put("P1 Leopard", 5);
        map_Y_P1.put("P1 Rat", 7);

        map_X_P2.put("P2 Tiger", 9);
        map_X_P2.put("P2 Lion", 9);
        map_X_P2.put("P2 Cat", 8);
        map_X_P2.put("P2 Dog", 8);
        map_X_P2.put("P2 Elephant", 7);
        map_X_P2.put("P2 Wolf", 7);
        map_X_P2.put("P2 Leopard", 7);
        map_X_P2.put("P2 Rat", 7);
    
        map_Y_P2.put("P2 Tiger", 7);
        map_Y_P2.put("P2 Lion", 1);
        map_Y_P2.put("P2 Cat", 6);
        map_Y_P2.put("P2 Dog", 2);
        map_Y_P2.put("P2 Elephant", 7);
        map_Y_P2.put("P2 Wolf", 5);
        map_Y_P2.put("P2 Leopard", 3);
        map_Y_P2.put("P2 Rat", 1);
    }

    //removes the gamePiece of a player from both the row and the column HashMap
    public void removeGamePiece(String Player, String gamePiece){
        if(Player.equals("P1")){
            this.map_X_P1.remove(gamePiece);
            this.map_Y_P1.remove(gamePiece);
            return;
        }       
        this.map_X_P2.remove(gamePiece);
        this.map_Y_P2.remove(gamePiece);
    }

    //updates the HashMaps of a player after his/her turn
    public void setMap(String Player, String gamePiece, Integer X, String Y){
        Integer column;
        if(Player.equals("P1")){
            column = setColumn(Y.charAt(0));
            this.map_X_P1.replace(gamePiece, X);
            this.map_Y_P1.replace(gamePiece, column);
            return;
        }
        column = setColumn(Y.charAt(0));
        this.map_X_P2.replace(gamePiece, X);
        this.map_Y_P2.replace(gamePiece, column);
    }

    //returns the row HashMap for a player
    public HashMap<String, Integer> getXMap(String Player){
        if(Player.equals("P1")){
            return map_X_P1;
        }
        return map_X_P2;
    }

    //returns the column HashMap for a player
    public HashMap<String, Integer> getYMap(String Player){
        if(Player.equals("P1")){
            return map_Y_P1;
        }
        return map_Y_P2;
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