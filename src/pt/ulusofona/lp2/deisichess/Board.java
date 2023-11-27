package pt.ulusofona.lp2.deisichess;

import java.util.ArrayList;

public class Board {
    int size;
    Piece[][] tabuleiro;
    Team[] equipas;
    ArrayList<Piece> totalPieces;
    boolean currentTeam;
    int consecPassPlays;
    int numeroPecas;


    public Board(int size, int numeroPecas) {
        this.size = size;
        this.numeroPecas = numeroPecas;
        this.consecPassPlays = -1000;
        this.tabuleiro = new Piece[size][size];
        this.equipas = new Team[2];
        this.equipas[0] = new Team(0);
        this.equipas[1] = new Team(1);
        this.totalPieces = new ArrayList<>();
        this.currentTeam = false;
    }

    public Board(int size, Piece[][] tabuleiro, Team[] equipas, ArrayList<Piece> totalPieces, boolean currentTeam, int consecPassPlays, int numeroPecas) {
        this.size = size;
        this.tabuleiro = tabuleiro;
        this.equipas = equipas;
        this.totalPieces = totalPieces;
        this.currentTeam = currentTeam;
        this.consecPassPlays = consecPassPlays;
        this.numeroPecas = numeroPecas;
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
            return 20; //BRANCA
        } else {
            return 10; //PRETA
        }
    }

    public int isntCurrentTeamNumb() {
        if (!currentTeam) {
            return 10; //PRETA
        } else {
            return 20; //BRANCA
        }
    }

    public int convertNumEquipas(int num) {
        if (num == 20) {
            return 1;
        } else {
            return 0;
        }
    }


    public boolean temPeca(int x, int y) {
        return tabuleiro[x][y] != null;
    }


    public boolean validaMove(int oriX, int oriY, int destX, int destY) {
        int deslocX = Math.abs(destX - oriX);
        int deslocY = Math.abs(destY - oriY);


        return (deslocX == 1 && deslocY == 0) || (deslocX == 0 && deslocY == 1) || (deslocX == 1 && deslocY == 1);
    }


    public void tiraPecaOrigem(int origemX, int origemY) {
        tabuleiro[origemX][origemY] = null;
    }

    public void metePecaDestino(Piece pecaMovida, int destX, int destY) {
        tabuleiro[destX][destY] = pecaMovida;
        pecaMovida.novaPos(destX, destY);
    }

    public Piece getPecaNaPos(int x, int y) {
        return tabuleiro[x][y];
    }

    public void falhou() {
        equipas[convertNumEquipas(isCurrentTeamNumb())].invalida();
    }

    public void comeu() {
        equipas[convertNumEquipas(isCurrentTeamNumb())].comeu();
        consecPassPlays = 0;
        equipas[convertNumEquipas(isntCurrentTeamNumb())].decrementarInPlay();
        currentTeam = !currentTeam;


    }

    public void moveu() {
        equipas[isCurrentTeamNumb()].moveuSemComer();
        this.consecPassPlays++;
        this.currentTeam = !currentTeam;
    }

    public boolean coordenadasDentroTabuleiro(int x, int y) {
        return x >= 0 && x < getSize() && y >= 0 && y < getSize();
    }

    public boolean temPecaNoCaminho(int oriX, int oriY, int destX, int destY) {
        int deltaX = Integer.compare(destX, oriX);
        int deltaY = Integer.compare(destY, oriY);

        int x = oriX + deltaX;
        int y = oriY + deltaY;

        while (x != destX || y != destY) {
            if (temPeca(x, y)) {
                return true; // peça no caminho
            }
            x += deltaX;
            y += deltaY;
        }

        return false; // peça no caminho
    }

}
