package pt.ulusofona.lp2.deisichess;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Objects;
import java.util.Scanner;

public class GameManager {

    Board board;
    String winner = ""; //a preencher pelo gameOver

    public GameManager(Board board, String winner) {
        this.board = board;
        this.winner = winner;
    }

    public GameManager() {

    }

    public boolean loadGame(File file) {
        Scanner scanner = null;

        try {
            scanner = new Scanner(file);
        } catch (FileNotFoundException e) {
            return false;
        }


        board.size = Integer.parseInt((scanner.nextLine()));
        board.numeroPecas = Integer.parseInt(scanner.nextLine());

        for (int i = 0; i < board.numeroPecas; i++) {
            String linha = scanner.nextLine();
            String[] divisao = linha.split(":");

            Piece peca = new Piece();
            peca.id = Integer.parseInt(divisao[0]);
            peca.type = Integer.parseInt(divisao[1]);
            peca.team = Integer.parseInt(divisao[2]);
            peca.nickname = String.valueOf(Integer.parseInt(divisao[3]));

            board.totalPieces.add(peca); // adiciona ao arraylist das peças na class board
        }


        Board board = new Board();

        for (int i = 0; i < board.size; i++) {
            String linha = scanner.nextLine();
            String[] divisao = linha.split(":");

            for (int j = 0; j < divisao.length; j++) {
                int id = Integer.parseInt(divisao[j]);

                if (id != 0) {
                    Piece piece = new Piece();
                    piece.id = id;

                    // nao percebi se é preciso adicionar mais atributos, mas pelo
                    // que percebi estava lá so o id no video

                    board.tabuleiro[i][j] = piece;
                }
            }
        }

        return false;
    }

    public String[] getPieceInfo(int id) {

        //ID|tipo|Equipa|Alcunha|Estado|posX|posY

        String[] result = new String[7];
        result[0] = String.valueOf(id);
        while (!Objects.equals(result[1], "")) {
            for (Piece piece : board.getTotalPieces()) {
                if (id == piece.getId()) {
                    result[1] = String.valueOf(piece.getType());
                    result[2] = String.valueOf(piece.getTeam());
                    result[3] = String.valueOf(piece.getNickname());
                    result[4] = String.valueOf(piece.isInPlay());
                    result[5] = String.valueOf(piece.getPosX());
                    result[6] = String.valueOf(piece.getPosY());
                    return result;
                }
            }
        }
        result[1] = "barraca";
        return result;
    }

    public String getPieceInfoAsString(int id) {
        String espBarra = " | ";
        String[] pieceInfo = getPieceInfo(id);

        return pieceInfo[5] + espBarra +            //posX
                pieceInfo[6] + espBarra +           //posY
                pieceInfo[2] + espBarra +           //team
                pieceInfo[3] + " @ " +              //nickname
                '(' + pieceInfo[5] +                //posX
                ',' + pieceInfo[6] + ')';           //posY
    }

    public String[] getSquareInfo(int x, int y) {
        Piece piece = board.getTabuleiro()[x][y];
        if (piece == null) {
            return null;
        } else {
            String[] result = new String[7];
            result[0] = String.valueOf(piece.getId());
            result[1] = String.valueOf(piece.getType());
            result[2] = String.valueOf(piece.getTeam());
            result[3] = String.valueOf(piece.getNickname());
            result[4] = null;

            return result;
        }
    }

    public int getBoardSize() {
        return board.getSize();
    }

    public int getCurrentTeamID() {
        if (board.isCurrentTeam()) {
            return 1; //BRANCA
        } else {
            return 0; //PRETA
        }
    }

    public ArrayList<String> getGameResults() {
        ArrayList<String> result = new ArrayList<>();
        result.add(winner);

        //EQUIPA PRETA
        result.add(String.valueOf(board.getEquipas()[0].getNumCapturadas()));
        result.add(String.valueOf(board.getEquipas()[0].getNumJogadas()));
        result.add(String.valueOf(board.getEquipas()[0].getNumFalhadas()));

        //EQUIPA BRANCA
        result.add(String.valueOf(board.getEquipas()[1].getNumCapturadas()));
        result.add(String.valueOf(board.getEquipas()[1].getNumJogadas()));
        result.add(String.valueOf(board.getEquipas()[1].getNumFalhadas()));

        return result;
    }

    public boolean gameOver() {
        if (board.getEquipas()[0].getInPlayPieces() == 0 || board.getEquipas()[1].getInPlayPieces() == 0) {
            if (board.getEquipas()[0].getInPlayPieces() == 0) {
                winner = "VENCERAM AS PRETAS";
            } else {
                winner = "VENCERAM AS BRANCAS";
            }
            return true;
        } else if (board.getEquipas()[0].getInPlayPieces() == 1 && board.getEquipas()[1].getInPlayPieces() == 1) {
            winner = "EMPATE";
            return true;
        } else if (board.getConsecPassPlays() == 10) {
            winner = "EMPATE";
            return true;
        } else {
            return false;
        }
    }

    public boolean move(int oriX, int oriY, int destX, int destY) {
        if (board.temPeca(oriX, oriY)) {
            Piece pecaMovida = board.getTabuleiro()[oriY][oriX];
            if (pecaMovida.isInPlay() && pecaMovida.getTeam() == board.isCurrentTeamNumb()) {
                if (board.validaMove(oriX, oriY, destX, destY)) {                                       //MOVE VALIDO
                    if (board.temPeca(destX, destY)) {
                        if (board.getPeca(destX, destY).getTeam() != board.isCurrentTeamNumb()) {       //PECA NO DESTINO É DA EQUIPA CONTRÁRIA -> COME
                            board.tiraPecaOrigem(oriX, oriY);
                            board.getPeca(destX, destY).capturada();
                            board.metePecaDestino(pecaMovida, destX, destY);
                            board.comeu(getCurrentTeamID());

                        } else {
                            return false;                                                               //PECA NO DESTINO DA MESMA EQUIPA -> INVALIDO
                        }
                    } else if (!board.temPeca(destX, destY)) {                                          //SEM PECA NO DESTINO -> MOVE APENAS
                        board.tiraPecaOrigem(oriX, oriY);
                        board.metePecaDestino(pecaMovida, destX, destY);
                        board.moveu(getCurrentTeamID());
                    }
                }
            }
        }


        return false;
    }

}