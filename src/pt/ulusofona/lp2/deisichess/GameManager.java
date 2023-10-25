package pt.ulusofona.lp2.deisichess;

import java.io.File;
import java.util.ArrayList;
import java.util.Objects;

public class GameManager {

    Board board;
    String winner; //a preencher pelo gameOver

    boolean loadGame(File file) {
        return true;
    }

    String[] getPieceInfo(int id) {

        //ID|tipo|Equipa|Alcunha|Estado|posX|posY

        String[] result = new String[7];
        result[0] = String.valueOf(id);
        while (!Objects.equals(result[1], "")) {
            for (Piece piece : board.totalPieces) {
                if (id == piece.id) {
                    result[1] = String.valueOf(piece.type);
                    result[2] = String.valueOf(piece.team);
                    result[3] = String.valueOf(piece.nickname);
                    result[4] = String.valueOf(piece.inPlay);
                    result[5] = String.valueOf(piece.posX);
                    result[6] = String.valueOf(piece.posY);
                    return result;
                }
            }
        }
        result[1] = "barraca";
        return result;
    }

    String getPieceInfoAsString(int id) {
        String espBarra = " | ";
        String[] pieceInfo = getPieceInfo(id);
        StringBuilder result = new StringBuilder();
        result.append(pieceInfo[5]).append(espBarra);           //posX
        result.append(pieceInfo[6]).append(espBarra);           //posY
        result.append(pieceInfo[2]).append(espBarra);           //team
        result.append(pieceInfo[3]).append(" @ ");              //nickname
        result.append('(').append(pieceInfo[5]);                //posX
        result.append(',').append(pieceInfo[6]).append(')');    //posY

        return result.toString();
    }

    String[] getSquareInfo(int x, int y) {
        Piece piece = board.tabuleiro[x][y];
        String[] result = new String[7];
        result[0] = String.valueOf(piece.id);
        result[1] = String.valueOf(piece.type);
        result[2] = String.valueOf(piece.team);
        result[3] = String.valueOf(piece.nickname);
        result[4] = null;

        return result;
    }

    int getBoardSize() {
        return board.size;
    }

    int getCurrentTeamID() {
        if (board.currentTeam) {
            return 1;
        } else {
            return 0;
        }
    }

    ArrayList<String> getGameResults() {
        ArrayList<String> result = new ArrayList<>();
        result.add(winner);

        //EQUIPA PRETA
        result.add(String.valueOf(board.equipas[0].numCapturadas));
        result.add(String.valueOf(board.equipas[0].numJogadas));
        result.add(String.valueOf(board.equipas[0].numFalhadas));

        //EQUIPA BRANCA
        result.add(String.valueOf(board.equipas[1].numCapturadas));
        result.add(String.valueOf(board.equipas[1].numJogadas));
        result.add(String.valueOf(board.equipas[1].numFalhadas));

        return result;
    }

    boolean gameOver() {
        return true;
    }

}