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
        this.inPlayPieces = 0;
        this.teamPieces = new HashMap<>();
    }


    public void addPieceToHmap(Piece newPiece) {
        teamPieces.put(newPiece.getId(), newPiece);
    }

    void incrementarInPlay() {
        this.inPlayPieces++;
    }

    void decrementarInPlay() {
        this.inPlayPieces--;
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

    void invalida() {
        this.numFalhadas++;
    }

    void moveuSemComer() {
        this.numJogadas++;
    }

    void comeu() {
        this.numCapturadas++;
        this.numJogadas++;
    }
}

