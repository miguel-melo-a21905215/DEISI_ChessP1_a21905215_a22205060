package pt.ulusofona.lp2.deisichess;

import java.io.File;
import java.util.ArrayList;

/*TODO - FUNCAO PARA CORRER OS MOVES GUARDADOS -> TER UNDO DEPOIS DE LER UM FICHEIRO DE JOGO A MEIO*/
public class GameHistory {

    private ArrayList<File> historyFiles;
    private ArrayList<String> moves;


    public GameHistory() {
        this.moves = new ArrayList<>();
        this.historyFiles = new ArrayList<>();
    }


    public String moveToString(int oriX, int oriY, int destX, int destY) {
        String result = moves.size() + "\t\t:";
        result += oriX + "\t\t:" + oriY + "\t\t:" + destX + "\t\t:" + destY;


        return result;
    }

    public void initiateHistory() {

        this.moves = new ArrayList<>();
        moves.add("---------MOVE HISTORY---------\n" +
                "moveID\t:oriX\t:oriY\t:destX\t:destY");

    }

    public Board getStartingBoard() {

        return null;
    }

    public Board getPreviousBoard() {
        removeLastMove();
        return null;

    }

    public void addNewMove(String move) {
        moves.add(move);
    }

    public void removeLastMove() {

    }


    public ArrayList<String> getMoves() {
        return moves;
    }


}
