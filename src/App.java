import java.io.IOException;

import components.*;

public final class App {
    public static void main(String[] args) {
        Map map = new Map();
        GamePieces gamePiece = new GamePieces();
        InputReader readInput = new InputReader();
        PlayPiece playPiece = new PlayPiece();
        String moveValidity = "";
        String opponentPiece = "";
        String[] prevMove = {};
        String[] input = {"", "", ""};
        map.printMap(gamePiece.getXMap("P1"), gamePiece.getYMap("P1"), gamePiece.getXMap("P2"), gamePiece.getYMap("P2"));

        while(!input[0].equals("exit")){
            input = readInput.readInput();
            try {
                Integer.parseInt(input[2]);
            } catch (NumberFormatException nfe) {
                System.out.println("Please input the correct row position as an integer!\n");
                continue;
            }
            //moveValidity will store the returned string from the validity check functions
            moveValidity = playPiece.playGamePiece(input, gamePiece.getXMap(readInput.getCurrentPlayerTurn()), gamePiece.getYMap(readInput.getCurrentPlayerTurn()), gamePiece.getXMap(readInput.getOpponentPlayerTurn()), gamePiece.getYMap(readInput.getOpponentPlayerTurn()));
            //it will store the name of the opponent's piece, if there is a piece on the square that the player intends to move
            opponentPiece = playPiece.checkIfPiecePresent(Integer.parseInt(input[2]), input[1], gamePiece.getXMap(readInput.getOpponentPlayerTurn()), gamePiece.getYMap(readInput.getOpponentPlayerTurn()));

            if(moveValidity.equals("Valid Move")){
                //update the map of the current player
                gamePiece.setMap(readInput.getCurrentPlayerTurn(), input[0], Integer.parseInt(input[2]), input[1]);
                prevMove = input.clone();
                if(opponentPiece != ""){
                    //remove the opponent piece, if it can be captured by the current player's piece
                    gamePiece.removeGamePiece(readInput.getOpponentPlayerTurn(), opponentPiece);
                }
                //display the map
                map.printMap(gamePiece.getXMap("P1"), gamePiece.getYMap("P1"), gamePiece.getXMap("P2"), gamePiece.getYMap("P2"));

                //check for the game winner
                //will store the string "exit" if a game winner is found, meaning the game will end after this loop
                input[0] = playPiece.decideGameWinner(readInput.getCurrentPlayerTurn(), input[0], gamePiece.getXMap(readInput.getCurrentPlayerTurn()), gamePiece.getYMap(readInput.getCurrentPlayerTurn()), gamePiece.getXMap(readInput.getOpponentPlayerTurn()), gamePiece.getYMap(readInput.getOpponentPlayerTurn()));
                readInput.setPlayerTurn();
                System.out.println("\nMove is Valid.");
                //display the previous move
                readInput.displayPreviousMove(prevMove);
                continue;
            }
            System.out.println("\n" + moveValidity + "\n");
        }
        System.out.println("\nThank you very much for playing the game. Hope you enjoyed!");
    }
}
