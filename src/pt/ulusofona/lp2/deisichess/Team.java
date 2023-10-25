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
