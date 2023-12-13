package pt.ulusofona.lp2.deisichess;

import java.util.ArrayList;

public class Board {
    private int size;
    private Piece[][] tabuleiro;
    private Team[] equipas;
    private ArrayList<Piece> totalPieces;
    private boolean currentTeam;
    private int consecPassPlays;
    private int numeroPecas;
    private boolean firstCaptureOcurred;
    private int turn;


    public Board(int size, int numeroPecas) {
        this.size = size;
        this.numeroPecas = numeroPecas;
        this.consecPassPlays = -1000;
        this.tabuleiro = new Piece[size][size];
        this.equipas = new Team[2];
        this.equipas[0] = new Team(10);
        this.equipas[1] = new Team(20);
        this.totalPieces = new ArrayList<>();
        this.currentTeam = false;
        this.firstCaptureOcurred = false;
        this.turn = 0;

    }

    public Board(int size, Piece[][] tabuleiro, Team[] equipas, ArrayList<Piece> totalPieces, boolean currentTeam, int consecPassPlays, int numeroPecas) {
        this.size = size;
        this.tabuleiro = tabuleiro;
        this.equipas = equipas;
        this.totalPieces = totalPieces;
        this.currentTeam = currentTeam;
        this.consecPassPlays = consecPassPlays;
        this.numeroPecas = numeroPecas;
        this.firstCaptureOcurred = true;
    }

    public Board() {
    }

    public int getNumeroPecas() {
        return numeroPecas;
    }

    public void setNumeroPecas(int numeroPecas) {
        this.numeroPecas = numeroPecas;
    }

    public boolean isFirstCapture() {
        return firstCaptureOcurred;
    }

    public void setFirstCapture(boolean firstCapture) {
        this.firstCaptureOcurred = firstCapture;
    }

    public int getTurn() {
        return turn;
    }

    public void setTurn(int turn) {
        this.turn = turn;
    }

    public Piece[][] getTabuleiro() {
        return tabuleiro;
    }

    public void setTabuleiro(Piece[][] tabuleiro) {
        this.tabuleiro = tabuleiro;
    }

    public Team[] getEquipas() {
        return equipas;
    }

    public void setEquipas(Team[] equipas) {
        this.equipas = equipas;
    }

    public ArrayList<Piece> getTotalPieces() {
        return totalPieces;
    }

    public void setTotalPieces(ArrayList<Piece> totalPieces) {
        this.totalPieces = totalPieces;
    }

    public int getSize() {
        return size;
    }

    public void setSize(int size) {
        this.size = size;
    }

    public int getConsecPassPlays() {
        return consecPassPlays;
    }

    public void setConsecPassPlays(int consecPassPlays) {
        this.consecPassPlays = consecPassPlays;
    }

    public boolean isCurrentTeam() {
        return currentTeam;
    }

    public void setCurrentTeam(boolean currentTeam) {
        this.currentTeam = currentTeam;
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


    public boolean generalMoveValidation(int oriX, int oriY, int destX, int destY) {

        /*Verifica:
        ->destino e diferente da origem
        ->as coordenadas de origem e destino estão dentro do tabuleiro
        ->tem peça na origem e se essa peça é da equipa a jogar
        ->tem peça no destino e se essa peça é da equipa contrária
         */

        if (oriX == destX && oriY == destY) {
            this.equipas[convertNumEquipas(isCurrentTeamNumb())].invalida();
            return false;
        }
        if (!temPeca(oriX, oriY) || !coordenadasDentroTabuleiro(destX, destY) || !coordenadasDentroTabuleiro(oriX, oriY)) {
            this.equipas[convertNumEquipas(isCurrentTeamNumb())].invalida();
            return false;
        }

        Piece pecaMovida = getPecaNaPos(oriX, oriY);
        if (pecaMovida.getTeam() != isCurrentTeamNumb() || !pecaMovida.isInPlay()) {
            this.equipas[convertNumEquipas(isCurrentTeamNumb())].invalida();
            return false;
        }

        if (temPeca(destX, destY) && (getPecaNaPos(destX, destY).getTeam() == isCurrentTeamNumb())) {
            this.equipas[convertNumEquipas(isCurrentTeamNumb())].invalida();
            return false;
        }

        return true;
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
        this.consecPassPlays = 0;
        equipas[convertNumEquipas(isntCurrentTeamNumb())].decrementarInPlay();
        currentTeam = !currentTeam;
        if (!firstCaptureOcurred) {
            firstCaptureOcurred = true;
        }


    }

    public void moveu() {
        equipas[convertNumEquipas(isCurrentTeamNumb())].moveuSemComer();
        this.currentTeam = !currentTeam;
        if (firstCaptureOcurred) {
            this.consecPassPlays++;
        }
    }

    public boolean coordenadasDentroTabuleiro(int x, int y) {
        return x >= 0 && x < getSize() && y >= 0 && y < getSize();
    }

    //TODO - NAO PODE SER FEITO ASSIM, TEM QUE SER ESPECIFICO A CADA PEÇA
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


    public Board cloneBoard() {
        Board clonedBoard = new Board(this.size, this.totalPieces.size());

        clonedBoard.currentTeam = this.currentTeam;
        clonedBoard.consecPassPlays = this.consecPassPlays;

        for (Piece consideredPiece : this.totalPieces) {
            Piece clonedPiece = consideredPiece.clonePiece();

            clonedBoard.totalPieces.add(clonedPiece);
            clonedBoard.equipas[convertNumEquipas(clonedPiece.getTeam())].addPieceToHmap(clonedPiece);
            clonedBoard.getTabuleiro()[consideredPiece.getPosX()][consideredPiece.getPosY()] = clonedPiece;

        }
        return clonedBoard;
    }

    public void homerClock(int turn) {
        if (turn % 3 == 0) {
            for (Piece consideredPiece : totalPieces) {
                if (consideredPiece.getType() == 6) {
                    consideredPiece.goSleep();
                }
            }
        } else {
            for (Piece consideredPiece : totalPieces) {
                if (consideredPiece.getType() == 6) {
                    consideredPiece.awake();
                }
            }
        }
    }

    public void jokerClock(int turn) {
        for (Piece piece : totalPieces) {
            if (piece.getType()==7) {
                piece.jokerClock();
            }
        }
    }


    public Piece getPieceByID(int id) {
        for (Piece piece : totalPieces) {
            if (piece.getId() == id) {
                return piece;
            }
        }
        return null;
    }

}
