package components;
import java.util.HashMap;
import java.util.Collections;


public class Map {

    //used for displaying the Map UI and the player's game pieces on the corresponding squares
    public void printMap(HashMap<String, Integer> map_X_P1, HashMap<String, Integer> map_Y_P1, HashMap<String, Integer> map_X_P2, HashMap<String, Integer> map_Y_P2){
        System.out.println("   ______________________________________________________________________________________________________________________");
        //for each row, 7 columns are displayed
        for(int row = 9; row >= 1; row--){
            String[] rowPiecesP1 = rowPiecesP1(map_X_P1, row, Collections.frequency(map_X_P1.values(), row));
            String[] rowPiecesP2 = rowPiecesP2(map_X_P2, row, Collections.frequency(map_X_P2.values(), row));
            switch(row){
                case 1:
                case 9:
                    System.out.println("  |                |                |______TRAP______|_______DEN______|______TRAP______|                |                |");
                    break;
                case 2:
                case 8:
                    System.out.println("  |                |                |                |______TRAP______|                |                |                |");
                    break;
                case 4:
                case 5:
                case 6:
                    System.out.println("  |                |______RIVER_____|______RIVER_____|                |______RIVER_____|______RIVER_____|                |");
                    break;
                default:
                    System.out.println("  |                |                |                |                |                |                |                |");

            }
            System.out.println(row + " |                |                |                |                |                |                |                |");
            System.out.print("  ");
            for(int column = 1; column <= 7; column++){
                String columnPieceName;
                String columnPieceP1 = columnPieceP1(map_Y_P1, rowPiecesP1, column);
                String columnPieceP2 = columnPieceP2(map_Y_P2, rowPiecesP2, column);

                columnPieceName = columnPieceP1 != "Not Found" ? columnPieceP1 : columnPieceP2 != "Not Found" ? columnPieceP2 : "";

                switch(columnPieceName){
                    case "P1 Cat":
                    case "P1 Dog": 
                    case "P1 Rat":
                    case "P2 Cat":
                    case "P2 Dog": 
                    case "P2 Rat":  
                        System.out.print("|     " + columnPieceName + "     ");
                        break;

                    case "P1 Lion":
                    case "P1 Wolf":
                    case "P2 Lion":
                    case "P2 Wolf":
                        System.out.print("|     " + columnPieceName + "    ");
                        break;

                    case "P1 Tiger":
                    case "P2 Tiger":
                        System.out.print("|    " + columnPieceName + "    ");
                        break;

                    case "P1 Leopard":
                    case "P2 Leopard":
                        System.out.print("|   " + columnPieceName + "   ");
                        break;

                    case "P1 Elephant":
                    case "P2 Elephant":
                        System.out.print("|   " + columnPieceName + "  ");
                        break;

                    default:
                        System.out.print("|                ");
                        
                }
            }
            System.out.println("|");
            System.out.println("  |________________|________________|________________|________________|________________|________________|________________|");
        }
        System.out.println("           A                B                C                D                 E                F               G        ");
        
    }

    //returns the names of all the gamePieces in a particular row
    public String[] rowPiecesP1(HashMap<String, Integer> map_X_P1, int row, int size){
        int index = 0;
        String[] rowPieces = new String[size];
        for(String gamePiece: map_X_P1.keySet()){
            if(map_X_P1.get(gamePiece) == row){
                rowPieces[index++] = gamePiece;
            }
        }
        return rowPieces;
    }

    public String[] rowPiecesP2(HashMap<String, Integer> map_X_P2, int row, int size){
        int index = 0;
        String[] rowPieces = new String[size];
        for(String gamePiece: map_X_P2.keySet()){
            if(map_X_P2.get(gamePiece) == row){
                rowPieces[index++] = gamePiece;
            }
        }
        return rowPieces;
    }

    //checks whether a gamePiece is present on a particular square
    //By using the column position and the set of gamePieces on a particular row
    public String columnPieceP1(HashMap<String, Integer> map_Y_P1, String[] rowPiecesP1, int column){
        for(String gamePiece: rowPiecesP1){
            if(map_Y_P1.get(gamePiece) == column){
                return gamePiece;
            }
        }
        return "Not Found";
    }

    public String columnPieceP2(HashMap<String, Integer> map_Y_P2, String[] rowPiecesP2, int column){
        for(String gamePiece: rowPiecesP2){
            if(map_Y_P2.get(gamePiece) == column){
                return gamePiece;
            }
        }
        return "Not Found";
    }

}

