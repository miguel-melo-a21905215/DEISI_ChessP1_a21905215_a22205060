package pt.ulusofona.lp2.deisichess;

import java.util.ArrayList;

/*TODO - FUNCAO PARA CORRER OS MOVES GUARDADOS -> TER UNDO DEPOIS DE LER UM FICHEIRO DE JOGO A MEIO*/
public class GameHistory {


    ArrayList<String> moves;
    ArrayList<Board> history;


    public GameHistory() {
        this.moves = new ArrayList<>();
        this.history = new ArrayList<>();
    }

    public String moveToString(int oriX, int oriY, int destX, int destY, boolean capture, Piece movedPiece, Piece capturedPiece) {
        String result = "";
        result += movedPiece.getId() + "\t:" + oriX + "\t:" + oriY + "\t:" + destX + "\t:" + destY + "\t:" + capture;
        if (capture) {
            result += "\t:" + capturedPiece.getId();
        }

        return result;
    }

    public void startingBoard(Board currentBoard) {
        history.add(currentBoard.cloneBoard(currentBoard));

        moves.add("---------MOVE HISTORY---------\n" +
                "IDmovedPiece\t:oriX\t:oriY\t:destX\t:destY\t:capture\t:capturedID");

    }

    public Board getStartingBoard() {
        return history.get(0);
    }

    public Board getPreviousBoard() {
        Board result = history.get(history.size() - 1);
        removeLastMove();
        return result;

    }

    public void addNewMove(String move, Board currentBoard) {
        moves.add(move);
        history.add(currentBoard.cloneBoard(currentBoard));
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
