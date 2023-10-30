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
        this.consecPassPlays = -1000;
        this.tabuleiro = new Piece[size][size];
        this.equipas = new Team[2];
        this.equipas[0] = new Team(0);
        this.equipas[1] = new Team(1);
        this.totalPieces = new ArrayList<>();
        this.currentTeam = false;
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

    public int isCurrentTeamNumb() {
        if (currentTeam) {
            return 1; //BRANCA
        } else {
            return 0; //PRETA
        }
    }

    public boolean temPeca(int x, int y) {
        return tabuleiro[y][x] != null;
    }

    public Piece getPeca(int posX, int posY) {
        return tabuleiro[posY][posX];
    }

    boolean validaMove(int oriX, int oriY, int destX, int destY) {
        if (destX < size && destY < size && destX > 0 && destY > 0) {
            int deslocX = Math.abs(destX - oriX);
            int deslocY = Math.abs(destY - oriY);
            int deslocTotal = deslocY + deslocX;
            return deslocTotal <= 2;
        } else {
            return false;
        }
    }

    void tiraPecaOrigem(int origemX, int origemY) {
        tabuleiro[origemX][origemY] = null;
    }

    void metePecaDestino(Piece pecaMovida, int destX, int destY) {
        tabuleiro[destX][destY] = pecaMovida;
    }

    public void comeu(int team) {
        equipas[team].comeu();
        this.consecPassPlays = 0;
        this.currentTeam = !currentTeam;
    }

    public void moveu(int team) {
        equipas[team].fino();
        this.consecPassPlays++;
        this.currentTeam = !currentTeam;
    }
}
