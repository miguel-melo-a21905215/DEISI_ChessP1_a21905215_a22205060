package pt.ulusofona.lp2.deisichess;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class Board {
    private int size;
    private Piece[][] tabuleiro;
    private Team[] equipas;
    private ArrayList<Piece> totalPieces;
    private boolean currentTeam;
    private int consecPassPlays;
    private boolean firstCaptureOcurred;
    private HashMap<Integer, String> capturedPieces;

    public Board(int size) {
        this.size = size;
        this.consecPassPlays = -1000;
        this.tabuleiro = new Piece[size][size];
        this.equipas = new Team[2];
        this.equipas[0] = new Team(10);
        this.equipas[1] = new Team(20);
        this.totalPieces = new ArrayList<>();
        this.currentTeam = false;
        this.firstCaptureOcurred = false;
        this.capturedPieces = new HashMap<>();
    }


    public Board() {
    }

    public Map getCapturedCounters() {
        return capturedPieces;
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


    public boolean generalMoveValidation(int oriX, int oriY, int destX, int destY) {

        /*Verifica:
        ->destino e diferente da origem
        ->as coordenadas de origem e destino estão dentro do tabuleiro
        ->tem peça na origem e se essa peça é da equipa a jogar
        ->tem peça no destino e se essa peça é da equipa contrária
         */

        if (oriX == destX && oriY == destY) {
            return false;
        }
        if (!temPeca(oriX, oriY) || !coordenadasDentroTabuleiro(destX, destY) || !coordenadasDentroTabuleiro(oriX, oriY)) {
            return false;
        }

        Piece pecaMovida = getPecaNaPos(oriX, oriY);
        if (pecaMovida.getTeam() != isCurrentTeamNumb() || !pecaMovida.isInPlay()) {
            return false;
        }

        if (temPeca(destX, destY) && (getPecaNaPos(destX, destY).getTeam() == isCurrentTeamNumb())) {
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

    public void comeu(int type, String typeStr) {
        equipas[convertNumEquipas(isCurrentTeamNumb())].comeu();
        this.consecPassPlays = 0;
        equipas[convertNumEquipas(isntCurrentTeamNumb())].decrementarInPlay();
        currentTeam = !currentTeam;

        if (!firstCaptureOcurred) {
            firstCaptureOcurred = true;
        }

        if (!capturedPieces.containsKey(type)) {
            capturedPieces.put(type, typeStr);
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
            if (piece.getType() == 7) {
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
