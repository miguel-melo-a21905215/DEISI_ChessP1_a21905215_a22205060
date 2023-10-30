package pt.ulusofona.lp2.deisichess;

import java.util.HashMap;

public class Team {
    int idEquipa;
    int numJogadas;
    int numFalhadas;
    int numCapturadas;
    HashMap<Integer, Piece> teamPieces;
    int inPlayPieces;

    public Team(int idEquipa) {
        this.idEquipa = idEquipa;
        this.numJogadas = 0;
        this.numFalhadas = 0;
        this.numCapturadas = 0;
        this.teamPieces = new HashMap<>();
    }

    public HashMap<Integer, Piece> getTeamPieces() {
        return teamPieces;
    }

    public void addPieceToHmap(Piece newPiece) {
        teamPieces.put(newPiece.getId(), newPiece);
    }

    public int getIdEquipa() {
        return idEquipa;
    }

    public int getNumJogadas() {
        return numJogadas;
    }

    public int getNumFalhadas() {
        return numFalhadas;
    }

    public int getNumCapturadas() {
        return numCapturadas;
    }

    public int getInPlayPieces() {
        return inPlayPieces;
    }

    void falhou() {
        this.numFalhadas++;
    }

    void fino() {
        this.numJogadas++;
    }

    void comeu() {
        this.numCapturadas++;
        this.numJogadas++;
    }
}
