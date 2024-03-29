package pt.ulusofona.lp2.deisichess;

import java.util.HashMap;
import java.util.Objects;

public class Team {
    private int idEquipa;
    private int numJogadas;
    private int numFalhadas;
    private int numCapturadas;
    private HashMap<Integer, Piece> teamPieces;
    private int inPlayPieces;

    public Team(int idEquipa) {
        this.idEquipa = idEquipa;
        this.numJogadas = 0;
        this.numFalhadas = 0;
        this.numCapturadas = 0;
        this.inPlayPieces = 0;
        this.teamPieces = new HashMap<>();
    }

    public int getIdEquipa() {
        return idEquipa;
    }

    public HashMap<Integer, Piece> getTeamPieces() {
        return teamPieces;
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
        for (Piece consideredPiece : teamPieces.values()) {
            if (Objects.equals(consideredPiece.getTypeStr(), "Rei") && consideredPiece.isInPlay()) {
                return true;
            }
        }
        return false;
    }


}

