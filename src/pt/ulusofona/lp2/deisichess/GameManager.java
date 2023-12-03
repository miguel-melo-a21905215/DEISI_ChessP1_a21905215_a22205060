package pt.ulusofona.lp2.deisichess;

import javax.swing.*;
import java.io.*;
import java.util.*;

public class GameManager {

    Board board = new Board();
    String winnerMessage = "";
    GameHistory gameHistory = new GameHistory();
    int turn = 0;


    //a preencher pelo gameOver

    public GameManager(Board board, String winner) {
        this.board = board;
        this.winnerMessage = winner;
        this.gameHistory = new GameHistory();
        this.turn = 0;
    }

    public GameManager() {

    }

    //TODO loadGame() VERIFICAR DEPOIS DE TER O SAVE FEITO
    public void loadGame(File file) throws InvalidGameInputException, IOException {

        try {
            Scanner scanner = new Scanner(file);
            int boardSize;
            int numPecas;

            boardSize = Integer.parseInt((scanner.nextLine()));                     //size do tabuleiro
            numPecas = Integer.parseInt(scanner.nextLine());                        //numero de pecas presentes no tabuleiro

            this.board = new Board(boardSize, numPecas);
            int currentLine = 2;
            //LEITURA DADOS DAS PEÇAS
            for (int i = 0; i < numPecas; i++) {
                String linha = scanner.nextLine();
                String[] divisao = linha.split(":");
                currentLine++;

                if (divisao.length == 4) {
                    int id = Integer.parseInt(divisao[0].trim());
                    int type = Integer.parseInt(divisao[1].trim());
                    int team = Integer.parseInt(divisao[2].trim());
                    String nickname = (divisao[3].trim());


                    Piece newPiece = pieceByType(id, type, team, nickname);

                    board.getEquipas()[board.convertNumEquipas(team)].addPieceToHmap(newPiece);
                    board.getTotalPieces().add(newPiece);
                    // adiciona ao arraylist das peças na class board
                } else {

                    throw new InvalidGameInputException(currentLine, divisao.length);
                }
            }
            //LEITURA DO TABULEIRO
            for (int y = 0; y < this.board.getSize(); y++) {
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
                                    board.getEquipas()[board.convertNumEquipas(piece.getTeam())].incrementarInPlay();

                                }
                            }
                        }
                    }
                }

            }

            gameHistory.startingBoard(board);

            if (scanner.hasNext() && Objects.equals(scanner.nextLine().trim(), "---------MOVE HISTORY---------")) {
                int playCount = 0;
                while (scanner.hasNextLine()) {
                    String line = scanner.nextLine();
                    String[] split = line.split(":");
                    if (line.equals("moveID\t:oriX\t:oriY\t:destX\t:destY")) {
                        playCount++;
                    } else if (split.length == 5) {
                        int oriX = Integer.parseInt(split[1].trim());
                        int oriY = Integer.parseInt(split[2].trim());
                        int destX = Integer.parseInt(split[3].trim());
                        int destY = Integer.parseInt(split[4].trim());

                        move(oriX, oriY, destX, destY);

                        playCount++;

                    }

                }
            }
        } catch (FileNotFoundException e) {
            throw new FileNotFoundException("Erro ao ler ficheiro");
        }
    }


    public void saveGame(File file) throws IOException {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(file))) {
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
                        writer.write(0 + "");
                    } else {
                        writer.write(String.valueOf(startingBoard.getPecaNaPos(x, y).getId()));
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
            throw new IOException("File not found: " + file.getPath());
        }
    }


    public void undo() {
        /*TODO - VERIFICAR DEPOIS DE TER A MOVE FEITA*/
        if (gameHistory.getMoves().size() > 1) {
            this.board = gameHistory.getPreviousBoard();
        }
    }

    public String[] getPieceInfo(int id) {

        //ID|tipo(emString)|Pontos da Peca|Equipa|Alcunha|Estado|posX|posY


        String[] result = new String[7];
        result[0] = String.valueOf(id);
        while (!Objects.equals(result[1], "")) {
            for (Piece consideredPiece : board.getTotalPieces()) {
                if (id == consideredPiece.getId()) {
                    result[1] = String.valueOf(consideredPiece.getType());
                    if (result[1].equals("Joker")){
                        result[1] += "/" + consideredPiece.getCopyMoveFrom();
                    }
                    result[2] = String.valueOf(consideredPiece.getPointsWorth());
                    result[3] = String.valueOf(consideredPiece.getTeam());
                    result[4] = String.valueOf(consideredPiece.getNickname());
                    if (consideredPiece.isInPlay()) {
                        result[5] = "em jogo";
                    } else {
                        result[5] = "capturado";
                    }
                    if (consideredPiece.isInPlay()) {
                        result[6] = String.valueOf(consideredPiece.getPosX());
                        result[6] += String.valueOf(consideredPiece.getPosY());
                    } else {
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
        Piece consideredPiece = null;

        result += pieceInfo[0] + espBarra;              //ID
        result += pieceTypeStr(Integer.parseInt(pieceInfo[1])) + espBarra;              //TipoStr
        if (Objects.equals(pieceInfo[2], "1000")) {
            result += "(infinito)" + espBarra;          //SE FOR O REI DEVE ESCREVER INFINTIO EM VEZ DE MIL
        } else {
            result += pieceInfo[2] + espBarra;          //PointsWorth
        }
        result += pieceInfo[3] + espBarra;              //team
        result += pieceInfo[4] + " @ ";                 //nickname
        for (Piece piece : board.getTotalPieces()) {
            if (piece.getId() == id) {
                consideredPiece = piece;
            }
        }
        if (consideredPiece != null && consideredPiece.isInPlay()) {
            result += '(' + String.valueOf(pieceInfo[6].charAt(0));               //posX
            result += ", " + pieceInfo[6].charAt(1) + ')';        //posY
        } else {
            result += "(n/a)";
        }

        return result;
    }

    private String pieceTypeStr(int type) {
        return switch (type) {
            case 0 -> "Rei";
            case 1 -> "Rainha";
            case 2 -> "Ponei Magico";
            case 3 -> "Padre da Vila";
            case 4 -> "Torre Horizontal";
            case 5 -> "Torre Vertical";
            case 6 -> "Homer Simpson";
            case 7 -> "Joker";
            default -> null;
        };
    }

    public String[] getSquareInfo(int x, int y) {
        Piece consideredPiece = board.getTabuleiro()[x][y];
        if (consideredPiece == null) {
            return new String[0];
        } else {
            String[] result = new String[5];
            result[0] = String.valueOf(consideredPiece.getId());
            result[1] = String.valueOf(consideredPiece.getType());
            result[2] = String.valueOf(consideredPiece.getTeam());
            result[3] = String.valueOf(consideredPiece.getNickname());
            result[4] = String.valueOf(consideredPiece.getPngLocation());

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

        if (!board.getEquipas()[0].isKingAlive() && board.getEquipas()[1].isKingAlive()) {
            winnerMessage = "VENCERAM AS PRETAS";
            return true;
        } else if (!board.getEquipas()[1].isKingAlive() && board.getEquipas()[0].isKingAlive()) {
            winnerMessage = "VENCERAM AS BRANCAS";
            return true;
        } else if (!board.getEquipas()[0].isKingAlive() && !board.getEquipas()[1].isKingAlive()) {
            winnerMessage = "EMPATE";
            return true;
        }

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


    /*TODO - VERIFICAR MOVES CORRETOS DEPOIS DA CRIACAO DAS PECAS NOVAS + ADICIONAR ALTERAÇÃO DE PONTOS
     *  PARA A ESTATISTICA -> QUANDO COME +X PONTOS PARA !CURRENTTEAM -> ADICIONAR NA COMEU(?)
     * ADICIONAR CLONAGEM DO TABULEIRO + REGISTO DO MOVE PARA A GAME HISTORY
     * FALTA VALIDAÇÃO DO JOKER*/
    public boolean move(int oriX, int oriY, int destX, int destY) {

        if (board.generalMoveValidation(oriX, oriY, destX, destY)) {                                    //COORD. DENTRO TABULEIRO + PEÇA VALIDA + DESTINO VALIDO
            Piece pecaMovida = board.getPecaNaPos(oriX, oriY);
            if (pecaMovida.specificMoveValidation(oriX, oriY, destX, destY, board.getTabuleiro())) {    //CAMINHO LIVRE + CUMPRE LIMITES MOVE ESPECIFICO
                /*COMEU - JA FOI VERIFICADO SE A PEÇA NO DESTINO É DA EQUIPA CONTRÁRIA NA generalMoveValidation()*/
                if (board.temPeca(destX, destY)) {
                    Piece pecaNoDestino = board.getPecaNaPos(destX, destY);
                    pecaNoDestino.getPointsWorth();                                                     //TODO -> VER PARTE ESTATISTICA

                    board.tiraPecaOrigem(oriX, oriY);
                    pecaNoDestino.capturada();
                    board.metePecaDestino(pecaMovida, destX, destY);
                    board.comeu();

                    if (Objects.equals(pecaNoDestino.getTypeStr(), "Rei")) {
                        board.getEquipas()[board.convertNumEquipas(pecaMovida.getTeam())].killKing();
                        return true;
                    }

                    turn++;
                    board.homerClock(turn);

                    String moveStr = gameHistory.moveToString(oriX, oriY, destX, destY);
                    gameHistory.addNewMove(moveStr, this.board);

                } else {
                    board.tiraPecaOrigem(oriX, oriY);
                    board.metePecaDestino(pecaMovida, destX, destY);
                    board.moveu();

                    turn++;
                    board.homerClock(turn);

                    String moveStr = gameHistory.moveToString(oriX, oriY, destX, destY);
                    gameHistory.addNewMove(moveStr, this.board);
                }
                return true;

            }
        }
        board.falhou();
        return false;
    }

    public List<Comparable> getHints(int x, int y) {
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

    public Piece pieceByType(int id, int type, int team, String nickname) {
        return switch (type) {
            case 0 -> new Rei(id, type, team, nickname);
            case 1 -> new Rainha(id, type, team, nickname);
            case 2 -> new PoneiMagico(id, type, team, nickname);
            case 3 -> new PadreVila(id, type, team, nickname);
            case 4 -> new TorreHorizontal(id, type, team, nickname);
            case 5 -> new TorreVertical(id, type, team, nickname);
            case 6 -> new HomerSimpson(id, type, team, nickname);
            case 7 -> new Joker(id, type, team, nickname);
            default -> null;
        };
    }

    public Board getBoard() {
        return board;
    }

    public String getWinnerMessage() {
        return winnerMessage;
    }

    public GameHistory getGameHistory() {
        return gameHistory;
    }

    public ArrayList<String> holderMethod() {
        return new ArrayList<>();
    }
}