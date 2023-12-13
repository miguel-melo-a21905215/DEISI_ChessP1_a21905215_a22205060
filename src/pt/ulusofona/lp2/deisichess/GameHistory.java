package pt.ulusofona.lp2.deisichess;

import java.util.ArrayList;

/*TODO - FUNCAO PARA CORRER OS MOVES GUARDADOS -> TER UNDO DEPOIS DE LER UM FICHEIRO DE JOGO A MEIO*/
public class GameHistory {


    private ArrayList<String> moves;
    private ArrayList<Board> history;


    public GameHistory() {
        this.moves = new ArrayList<>();
        this.history = new ArrayList<>();
    }


    public String moveToString(int oriX, int oriY, int destX, int destY) {
        String result = moves.size() + "\t\t:";
        result += oriX + "\t\t:" + oriY + "\t\t:" + destX + "\t\t:" + destY;


        return result;
    }

    public void startingBoard(Board currentBoard) {
        this.history = new ArrayList<>();
        this.moves = new ArrayList<>();

        history.add(currentBoard.cloneBoard());

        moves.add("---------MOVE HISTORY---------\n" +
                "moveID\t:oriX\t:oriY\t:destX\t:destY");

    }

    public Board getStartingBoard() {
        return history.get(0);
    }

    public Board getPreviousBoard() {
        Board result = history.get(history.size() - 2);
        removeLastMove();
        return result;

    }

    public void addNewMove(String move, Board currentBoard) {
        moves.add(move);
        history.add(currentBoard.cloneBoard());
    }

    public void removeLastMove() {
        if (history.size() > 1 && moves.size() > 1) {
            moves.remove(moves.size() - 1);
            history.remove(history.size() - 1);
        }
    }


    public ArrayList<String> getMoves() {
        return moves;
    }


}
