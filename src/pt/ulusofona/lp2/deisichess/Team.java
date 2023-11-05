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

    public void incrementarInPlay() {
        this.inPlayPieces++;
    }

    public void decrementarInPlay() {
        this.inPlayPieces--;
    }

    public int getNumJogadas() {
        return numJogadas;
    }

    public int getNumFalhadas() {
        return numFalhadas;
    }

    public HashMap<Integer, Piece> getTeamPieces() {
        return teamPieces;
    }

    public int getNumCapturadas() {
        return numCapturadas;
    }

    public int getInPlayPieces() {
        return inPlayPieces;
    }

    public void invalida() {
        this.numFalhadas++;
    }

    public void moveuSemComer() {
        this.numJogadas++;
    }

    public void comeu() {
        this.numCapturadas++;
        this.numJogadas++;
    }

    public boolean verificaInPlay(int id) {
        if (this.teamPieces.containsKey(id) && this.teamPieces.get(id).isInPlay()) {
            return true;
        } else {
            return false;
        }
    }
}

