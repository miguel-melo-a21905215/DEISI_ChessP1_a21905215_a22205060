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

        if (oriX == destX && oriY == destY) { // Verifica se as coordenadas de origem e destino são as mesmas.
            return false;
        }

        //Verifica se nao tem peça na origem ou se a origem ou o destino nao tao dentro do tabuleiro
        if (!temPeca(oriX, oriY) || !coordenadasDentroTabuleiro(destX, destY) || !coordenadasDentroTabuleiro(oriX, oriY)) {
            return false;
        }

        Piece pecaMovida = getPecaNaPos(oriX, oriY); // Obtém a peça na posição de origem

        // Verifica se a equipe da peça movida não é a equipe atual ou se a peça não está em jogo
        if (pecaMovida.getTeam() != isCurrentTeamNumb() || !pecaMovida.isInPlay()) {
            return false;
        }

        // Verifica se há uma peça na posição de destino e se pertence à mesma equipe da peça movida
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

    public void comeu(int type, String typeStr) {  // Declaração do método que representa uma peça sendo capturada.

        // Atualiza a contagem de peças capturadas para a equipe atual.
        equipas[convertNumEquipas(isCurrentTeamNumb())].comeu();

        // Reseta para 0 o valor de jogadas consecutivas sem captura.
        this.consecPassPlays = 0;

        // Decrementa o número de peças em jogo para a equipe adversária.
        equipas[convertNumEquipas(isntCurrentTeamNumb())].decrementarInPlay();

        // Muda para a equipa seguinte
        currentTeam = !currentTeam;

        // Se esta for a primeira captura ocorrida no jogo.
        if (!firstCaptureOcurred) {
            firstCaptureOcurred = true;  // Marca que a primeira captura ocorreu
        }

        // Se o tipo de peça ainda não estiver no map de peças capturadas
        if (!capturedPieces.containsKey(type)) {
            capturedPieces.put(type, typeStr);  // Adiciona o tipo de peça ao mapa de peças capturadas.
        }
    }

    public void moveu() {
        equipas[convertNumEquipas(isCurrentTeamNumb())].moveuSemComer(); // Atualiza o contador de movimentos sem captura para a equipe atual
        this.currentTeam = !currentTeam; // Muda para a próxima equipa
        if (firstCaptureOcurred) { // Se já ocorreu a primeira captura no jogo
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
