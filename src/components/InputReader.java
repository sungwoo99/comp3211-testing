package components;

import java.io.*;

public class InputReader {
    private String playerTurn = "P1";

    public String[] readInput() {
        String[] readInput = new String[3];
        //takes the user input as an array of strings
        try{
            BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
            System.out.println("Turn for Player " + playerTurn);
            System.out.print("Please input your move in the following format (e.g. Elephant A 9):\n");
            readInput = reader.readLine().split("\\s+");
            readInput[0] = readInput[0].toLowerCase();
            readInput[0] = getCurrentPlayerTurn().concat(" " + readInput[0].substring(0, 1).toUpperCase() + readInput[0].substring(1));
            readInput[1] = readInput[1].toUpperCase();
        }
        catch (IOException e){
            System.out.println("The following IOException error occurred: ");
            e.printStackTrace();
        }
        return readInput;
    }

    //changes player turn after each valid move
    public void setPlayerTurn(){
        this.playerTurn = this.playerTurn == "P1" ? "P2" : "P1";
    }

    //gets the current player's turn
    public String getCurrentPlayerTurn(){
        return playerTurn;
    }

    //sets the next player's turn
    public String getOpponentPlayerTurn(){
        if(this.playerTurn == "P1"){
            return "P2";
        }
        return "P1";
    }

    //displays the previous move to the next player
    public void displayPreviousMove(String[] prevMove){
        String opponent = "";
        if(this.playerTurn == "P1"){
            opponent = "Player 2";
        } else {
            opponent = "Player 1";
        }
        System.out.print("Previous move by " + opponent + ":  ");
        for(int i=0; i<prevMove.length; i++){
            if(i == 0){
                System.out.print(prevMove[i] + " to ");
                continue;
            }  
            System.out.print(prevMove[i]);
        }
        System.out.print("\n\n");
    }
}
