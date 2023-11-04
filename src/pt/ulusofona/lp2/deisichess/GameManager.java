package pt.ulusofona.lp2.deisichess;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Objects;
import java.util.Scanner;

public class GameManager {

    Board board;
    String winnerMessage = ""; //a preencher pelo gameOver

    public GameManager(Board board, String winner) {
        this.board = board;
        this.winnerMessage = winner;
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


        int size;
        int numPecas;

        size = Integer.parseInt((scanner.nextLine()));                  //size do tabuleiro
        numPecas = Integer.parseInt(scanner.nextLine());                //numero de pecas presentes no tabuleiro

        this.board = new Board(size, numPecas);

        for (int i = 0; i < numPecas; i++) {
            String linha = scanner.nextLine();
            String[] divisao = linha.split(":");

            int id = Integer.parseInt(divisao[0].trim());
            int type = Integer.parseInt(divisao[1].trim());
            int team = Integer.parseInt(divisao[2].trim());
            String nickname = (divisao[3].trim());

            Piece peca = new Piece(id, type, team, nickname);

            board.getEquipas()[team].addPieceToHmap(peca);
            board.getTotalPieces().add(peca);
            // adiciona ao arraylist das peças na class board
        }


        for (int y = 0; y < board.getSize(); y++) {
            String linha = scanner.nextLine();
            String[] divisao = linha.split(":");

            for (int x = 0; x < divisao.length; x++) {
                int id = Integer.parseInt(divisao[x]);
                if (id != 0) {
                    for (Piece piece : board.getTotalPieces()) {
                        if (piece.getId() == id) {
                            piece.novaPos(x, y);
                            board.metePecaDestino(piece, x, y);
                            piece.marcaInPlay();
                            board.getEquipas()[piece.getTeam()].incrementarInPlay();    //esta linha estava no sitio errado, causava o erro do gameOver
                        }
                    }

                }
            }
        }
        return true;
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
                    if (piece.isInPlay()) {
                        result[4] = "em jogo";
                    } else {
                        result[4] = "capturado";
                    }
                    if (piece.isInPlay()) {
                        result[5] = String.valueOf(piece.getPosX());
                        result[6] = String.valueOf(piece.getPosY());
                    } else {
                        result[5] = "";
                        result[6] = "";
                    }
                    return result;
                }
            }
        }
        result[1] = "ERRO";
        return result;
    }

    public String getPieceInfoAsString(int id) {
        String espBarra = " | ";
        String[] pieceInfo = getPieceInfo(id);

        String result = "";
        Piece peca = new Piece();

        result += pieceInfo[0] + espBarra;              //ID
        result += pieceInfo[1] + espBarra;              //Tipo
        result += pieceInfo[2] + espBarra;              //team
        result += pieceInfo[3] + " @ ";                 //nickname
        for (Piece piece : board.getTotalPieces()) {
            if (piece.getId() == id) {
                peca = piece;
            }
        }
        if (peca.isInPlay()) {
            result += '(' + pieceInfo[5];               //posX
            result += ", " + pieceInfo[6] + ')';        //posY
        } else {
            result += "(n/a)";
        }

        return result;
    }

    public String[] getSquareInfo(int x, int y) {
        Piece piece = board.getTabuleiro()[x][y];
        if (piece == null) {
            return new String[0];
        } else {
            String[] result = new String[5];
            result[0] = String.valueOf(piece.getId());
            result[1] = String.valueOf(piece.getType());
            result[2] = String.valueOf(piece.getTeam());
            result[3] = String.valueOf(piece.getNickname());

            if (piece.getTeam() == 0) {
                result[4] = "crazy_emoji_black.png";

            } else {
                result[4] = "crazy_emoji_white.png";
            }

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

        result.add("JOGO DE CRAZY CHESS");
        result.add("Resultado: " + winnerMessage);
        result.add("---");

        result.add("Equipa das Pretas");
        result.add(String.valueOf(board.getEquipas()[0].getNumCapturadas()));
        result.add(String.valueOf(board.getEquipas()[0].getNumJogadas()));
        result.add(String.valueOf(board.getEquipas()[0].getNumFalhadas()));
        /*------------------------------------------------------------*/
        result.add("Equipa das Brancas");
        result.add(String.valueOf(board.getEquipas()[1].getNumCapturadas()));
        result.add(String.valueOf(board.getEquipas()[1].getNumJogadas()));
        result.add(String.valueOf(board.getEquipas()[1].getNumFalhadas()));

        return result;
    }

    public boolean gameOver() {

        int pecasBrancas = board.getEquipas()[1].getInPlayPieces();
        int pecasPretas = board.getEquipas()[0].getInPlayPieces();


        if (pecasBrancas == 0 || pecasPretas == 0) {
            if (pecasBrancas == 0) {
                winnerMessage = "VENCERAM AS PRETAS";
            } else {
                winnerMessage = "VENCERAM AS BRANCAS";
            }
            return true;
        } else if (pecasPretas == 1 && pecasBrancas == 1) {
            winnerMessage = "EMPATE";
            return true;
        } else if (board.getConsecPassPlays() == 10) {
            winnerMessage = "EMPATE";
            return true;
        } else {
            return false;
        }
    }

    public boolean move(int oriX, int oriY, int destX, int destY) {
        if (board.temPeca(oriX, oriY) && (destX != oriX || destY != oriY)) {
            Piece pecaMovida = board.getPecaNaPos(oriX, oriY);
            if (pecaMovida.isInPlay() && pecaMovida.getTeam() == board.isCurrentTeamNumb()) {
                if (board.validaMove(oriX, oriY, destX, destY)) {                                           //MOVE VALIDO
                    if (board.temPeca(destX, destY)) {
                        if (board.getPecaNaPos(destX, destY).getTeam() != board.isCurrentTeamNumb()) {      //PECA NO DESTINO É DA EQUIPA CONTRÁRIA -> COME

                            board.getPecaNaPos(destX, destY).capturada();
                            board.metePecaDestino(pecaMovida, destX, destY);
                            board.comeu();
                            board.tiraPecaOrigem(oriX, oriY);
                            return true;

                        } else {
                            board.equipas[board.isCurrentTeamNumb()].invalida();
                            return false;                                                                   //PECA NO DESTINO DA MESMA EQUIPA -> INVALIDO
                        }
                    } else if (!board.temPeca(destX, destY)) {                                              //SEM PECA NO DESTINO -> MOVE APENAS
                        board.tiraPecaOrigem(oriX, oriY);
                        board.metePecaDestino(pecaMovida, destX, destY);
                        board.moveu();
                        return true;
                    }
                }
            }
        }
        board.falhou();
        return false;
    }

}