package pt.ulusofona.lp2.deisichess;

import javax.swing.*;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.*;

public class GameManager {

    Board board = new Board();
    String winnerMessage = "";
    GameHistory gameHistory = new GameHistory();


    //a preencher pelo gameOver

    public GameManager(Board board, String winner) {
        this.board = board;
        this.winnerMessage = winner;
    }

    public GameManager() {

    }

    //TODO loadGame() passa a ser void e Erros são identificados com a nova classe
    public void loadGame(File file) throws InvalidGameInputException, IOException {

        try (Scanner scanner = new Scanner(file)) {
            int boardSize;
            int numPecas;

            boardSize = Integer.parseInt((scanner.nextLine()));                     //size do tabuleiro
            numPecas = Integer.parseInt(scanner.nextLine());                        //numero de pecas presentes no tabuleiro

            this.board = new Board(boardSize, numPecas);
            int currentLine = 0;

            for (int i = 0; i < numPecas; i++) {
                String linha = scanner.nextLine();
                String[] divisao = linha.split(":");

                if (divisao.length == 4) {
                    int id = Integer.parseInt(divisao[0].trim());
                    int type = Integer.parseInt(divisao[1].trim());
                    int team = Integer.parseInt(divisao[2].trim());
                    String nickname = (divisao[3].trim());

                    Piece peca = new Piece(id, type, team, nickname);

                    board.getEquipas()[team].addPieceToHmap(peca);
                    board.getTotalPieces().add(peca);
                    // adiciona ao arraylist das peças na class board
                } else {
                    throw new InvalidGameInputException(currentLine, divisao.length);
                }
            }


            for (int y = 0; y < board.getSize(); y++) {
                String linha = scanner.nextLine();
                String[] divisao = linha.split(":");
                if (divisao.length == boardSize) {
                    for (int x = 0; x < divisao.length; x++) {
                        int id = Integer.parseInt(divisao[x]);
                        if (id != 0 && !(board.getEquipas()[0].verificaInPlay(id) || board.getEquipas()[1].verificaInPlay(id))) {     //verifica se a peca nao esta no tabuleiro ja
                            for (Piece piece : board.getTotalPieces()) {
                                if (piece.getId() == id) {
                                    piece.novaPos(x, y);
                                    board.metePecaDestino(piece, x, y);
                                    piece.marcaInPlay();
                                    board.getEquipas()[piece.getTeam()].incrementarInPlay();

                                }
                            }
                        }
                    }
                }

            }
            gameHistory.startingBoard(board);

            if (scanner.hasNext() && Objects.equals(scanner.nextLine(), "---------MOVE HISTORY---------")) {
                int playCount = 0;
                while (scanner.hasNextLine()) {
                    String line = scanner.nextLine();
                    String[] split = line.split(":");
                    if (line.equals("IDmovedPiece\t:oriX\t:oriY\t:destX\t:destY\t:capture\t:capturedID")) {
                        playCount++;
                    } else if (split.length == 6 && Objects.equals(split[5].trim(), "false")) {
                        int movedID = Integer.parseInt(split[0].trim());
                        int oriX = Integer.parseInt(split[1].trim());
                        int oriY = Integer.parseInt(split[2].trim());
                        int destX = Integer.parseInt(split[3].trim());
                        int destY = Integer.parseInt(split[4].trim());

                        /*TODO - EXECUTAR A move() COM OS DADOS LIDOS + CLONE DO TABULEIRO OBTIDO*/

                        playCount++;

                    } else if (split.length == 7 && Objects.equals(split[5].trim(), "true")) {
                        int movedID = Integer.parseInt(split[0].trim());
                        int oriX = Integer.parseInt(split[1].trim());
                        int oriY = Integer.parseInt(split[2].trim());
                        int destX = Integer.parseInt(split[3].trim());
                        int destY = Integer.parseInt(split[4].trim());
                        int capturedID = Integer.parseInt(split[6].trim());

                        /*TODO - EXECUTAR A move() COM OS DADOS LIDOS + CLONE DO TABULEIRO OBTIDO*/


                        playCount++;
                    }

                }
            }
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }


    }

    public void saveGame(File file) throws IOException {
        /*TODO - VERIFICAR DEPOIS DA MOVE E IMPLEMENTAÇÃO DA CLONE*/

        try (FileWriter writer = new FileWriter(file)) {
            Board startingBoard = gameHistory.getStartingBoard();
            writer.write(startingBoard.getSize() + "\n");
            writer.write(startingBoard.getTotalPieces().size() + "\n");

            /*---------DADOS DE CADA PECA---------*/
            for (Piece piece : startingBoard.getTotalPieces()) {
                writer.write(piece.getId() + ":" + piece.getType() + ":" + piece.getTeam() + ":" + piece.getNickname() + "\n");
            }
            /*---------TABULEIRO---------*/

            for (int y = 0; y < startingBoard.getSize(); y++) {
                for (int x = 0; x < startingBoard.getSize(); x++) {
                    Piece currentPiece = startingBoard.getPecaNaPos(x, y);
                    if (currentPiece == null) {
                        writer.write(0);
                    } else {
                        writer.write(startingBoard.getPecaNaPos(x, y).getId());
                    }
                    if (x < startingBoard.getSize() - 1) {
                        writer.write(":");
                    }
                }
                writer.write("\n");
            }
            /*---------MOVE HISTORY---------*/
            if (gameHistory.getMoves().size() > 1) {
                for (String currentMove : gameHistory.getMoves()) {
                    writer.write(currentMove + "\n");
                }
            }
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }

    public void undo() {
        /*TODO - VERIFICAR DEPOIS DE TER A MOVE FEITA*/

        this.board = gameHistory.getPreviousBoard();
    }

    public String[] getPieceInfo(int id) {

        //ID|tipo(emString)|Pontos da Peca|Equipa|Alcunha|Estado|posX|posY


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
                /*TODO CADA PEÇA TEM O SEU PNG - a atualizar na classe de cada peça*/
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
            return 20; //BRANCA
        } else {
            return 10; //PRETA
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
        } else if ((pecasPretas == 1 && pecasBrancas == 1) || board.getConsecPassPlays() == 10) {
            winnerMessage = "EMPATE";
            return true;

        } else {
            return false;
        }
    }


    /*TODO - VERIFICAR MOVES CORRETOS DEPOIS DA CRIACAO DAS PECAS NOVAS*/
    public boolean move(int oriX, int oriY, int destX, int destY) {
        if (board.temPeca(oriX, oriY) && (destX != oriX || destY != oriY)) {
            Piece pecaMovida = board.getPecaNaPos(oriX, oriY);
            if (pecaMovida.isInPlay() && pecaMovida.getTeam() == board.isCurrentTeamNumb() && (board.validaMove(oriX, oriY, destX, destY))) {  //MOVE VALIDO
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
        board.falhou();
        return false;
    }

    public List<Comparable> getHints(int x, int y) throws IOException {
        return null;
    }

    public Map<String, String> customizeBoard() {
        return new HashMap<>();
    }

    public JPanel getAuthorsPanel() {
        JPanel result = new JPanel();

        ImageIcon gifIcon = new ImageIcon("src/images/14outta14.gif");
        JLabel gifLabel = new JLabel(gifIcon);

        result.add(gifLabel);
        return result;
    }

}