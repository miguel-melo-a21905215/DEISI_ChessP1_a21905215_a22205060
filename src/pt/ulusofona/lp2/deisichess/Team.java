package pt.ulusofona.lp2.deisichess;

import java.util.HashMap;
import java.util.Objects;

public class Team {
    int idEquipa;
    int numJogadas;
    int numFalhadas;
    int numCapturadas;
    HashMap<Integer, Piece> teamPieces;
    int inPlayPieces;
    boolean kingAlive;

    public Team(int idEquipa) {
        this.idEquipa = idEquipa;
        this.numJogadas = 0;
        this.numFalhadas = 0;
        this.numCapturadas = 0;
        this.inPlayPieces = 0;
        this.teamPieces = new HashMap<>();
        this.kingAlive = false;
    }


    public void addPieceToHmap(Piece newPiece) {
        if (Objects.equals(newPiece.getTypeStr(), "Rei")) {
            this.kingAlive = true;
        }
        teamPieces.put(newPiece.getId(), newPiece);
    }


    public int convertNumEquipas(int num) {
        if (num == 20) {
            return 1;
        } else {
            return 0;
        }
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
        return this.teamPieces.containsKey(id) && this.teamPieces.get(id).isInPlay();
    }

    public boolean isKingAlive() {
        return kingAlive;
    }

    public void killKing() {
        this.kingAlive = false;
    }
}

