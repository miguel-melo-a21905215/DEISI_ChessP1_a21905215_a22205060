package pt.ulusofona.lp2.deisichess;

import java.util.ArrayList;

public class Board {
    int size;
    int consecPassPlays;
    Piece[][] tabuleiro;
    Team[] equipas;
    ArrayList<Piece> totalPieces;
    boolean currentTeam;

    int numeroPecas;


    public Board(int size, int numeroPecas) {
        this.size = size;
        this.consecPassPlays = 0;
        this.tabuleiro = new Piece[size][size];
        this.equipas = new Team[1];
        this.totalPieces = new ArrayList<>();
    }

    public Board() {
    }

    public Piece[][] getTabuleiro() {
        return tabuleiro;
    }

    public Team[] getEquipas() {
        return equipas;
    }

    public ArrayList<Piece> getTotalPieces() {
        return totalPieces;
    }

    public int getSize() {
        return size;
    }

    public int getConsecPassPlays() {
        return consecPassPlays;
    }

    public boolean isCurrentTeam() {
        return currentTeam;
    }

    boolean validaMove(int id, int destX, int destY) {
        if ("valido" == "valido") {
            return true;
        } else {
            return false;
        }
    }

    void tiraPecaOrigem(int origemX, int origemY) {
        tabuleiro[origemX][origemY] = null;
    }

    void metePecaDestino(int team, int id, int destX, int destY) {
        tabuleiro[destX][destY] = equipas[team].getTeamPieces().get(id);
    }

    void comeu(int team) {
        equipas[team].comeu();
        this.consecPassPlays = 0;
        this.currentTeam = !currentTeam;
    }

    void moveu(int team) {
        equipas[team].fino();
        this.consecPassPlays++;
        this.currentTeam = !currentTeam;
    }
}
