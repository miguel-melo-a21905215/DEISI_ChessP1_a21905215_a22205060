package pt.ulusofona.lp2.deisichess;

import javax.swing.*;
import java.io.*;
import java.util.*;

public class GameManager {

    GameHistory gameHistory;
    private Board board;
    private String winnerMessage;
    private int turn;
    private boolean aveRaraInGame;
    private boolean aveRaraAppeared;


    public GameManager() {
        this.board = new Board();
        this.winnerMessage = "";
        this.turn = 0;
        this.gameHistory = new GameHistory();
        this.aveRaraAppeared = false;
        this.aveRaraInGame = false;

    }

    public void loadGame(File file) throws InvalidGameInputException, IOException {

        try {
            Scanner scanner = new Scanner(file);
            int boardSize;
            int numPecas;

            boardSize = Integer.parseInt((scanner.nextLine()));                     //size do tabuleiro
            numPecas = Integer.parseInt(scanner.nextLine());                        //numero de pecas presentes no tabuleiro

            this.board = new Board(boardSize, numPecas);
            gameHistory = new GameHistory();
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
            fillStartingBoardInfo();
            while (scanner.hasNext()) {
                String nextLine = scanner.nextLine();
                switch (nextLine) {
                    case "--AVE RARA MAY APPEAR--":
                        this.aveRaraInGame = true;
                        fillStartingBoardInfo();
                        break;

                    case "------------------MOVE HISTORY------------------":
                        break;
                    case "moveID\t:oriX\t:oriY\t:destX\t:destY\t:valid":
                        break;
                    default:
                        String[] split = nextLine.split(":");
                        if (split.length == 6) {
                            int oriX = Integer.parseInt(split[1].trim());
                            int oriY = Integer.parseInt(split[2].trim());
                            int destX = Integer.parseInt(split[3].trim());
                            int destY = Integer.parseInt(split[4].trim());

                            move(oriX, oriY, destX, destY);
                        }
                        break;
                }
            }
        } catch (FileNotFoundException e) {
            throw new FileNotFoundException("Erro ao ler ficheiro");
        }
    }

    public void saveGame(File file) throws IOException {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(file))) {
            gameHistory.writeFile(file);
        } catch (IOException e) {
            throw new IOException("File not found: " + file.getPath());
        }
    }

    public void undo() {
        gameHistory.deleteUntilValid();
        try {
            aveRaraAppeared = false;
            File usableFile = new File("gameHistoryFile.txt");
            gameHistory.writeFile(usableFile);
            loadGame(usableFile);
        } catch (FileNotFoundException ignored) {
        } catch (IOException e) {
            throw new RuntimeException(e);
        } catch (InvalidGameInputException e) {
            throw new RuntimeException(e);
        }
    }

    public String[] getPieceInfo(int id) {

        //ID|tipo(emString)|Equipa|Alcunha|Estado|posX|posY


        String[] result = new String[7];
        result[0] = String.valueOf(id);
        while (!Objects.equals(result[1], "")) {
            for (Piece consideredPiece : board.getTotalPieces()) {
                if (id == consideredPiece.getId()) {
                    result[1] = String.valueOf(consideredPiece.getType());
                    result[2] = String.valueOf(consideredPiece.getTeam());
                    result[3] = String.valueOf(consideredPiece.getNickname());
                    if (consideredPiece.isInPlay()) {
                        result[4] = "em jogo";
                    } else {
                        result[4] = "capturado";
                    }
                    if (consideredPiece.isInPlay()) {
                        result[5] = String.valueOf(consideredPiece.getPosX());
                        result[6] = String.valueOf(consideredPiece.getPosY());
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
        Piece consideredPiece = board.getPieceByID(id);

        String points = switch (consideredPiece.getType()) {
            case 0 -> "(infinito)";
            case 1 -> "8";
            case 2 -> "5";
            case 3, 4, 5 -> "3";
            case 6 -> "2";
            case 7 -> "4";
            default -> "";
        };


        if (Objects.equals(pieceInfo[1], "6") && (consideredPiece.isSleeping())) {
            return "Doh! zzzzzz";

        }

        result += pieceInfo[0] + espBarra;
        result += pieceTypeStr(Integer.parseInt(pieceInfo[1]));
        if (pieceInfo[1].equals("7")) {
            result += "/" + consideredPiece.getCopyMoveFrom();
        }
        result += espBarra;

        result += points + espBarra;
        result += pieceInfo[2] + espBarra;
        result += pieceInfo[3] + " @ ";

        if (consideredPiece != null && consideredPiece.isInPlay()) {
            result += '(' + String.valueOf(pieceInfo[5]);
            result += ", " + pieceInfo[6] + ')';
        } else {
            result += "(n/a)";
        }

        return result;

    }

    private String pieceTypeStr(int type) {
        return switch (type) {
            case 0 -> "Rei";
            case 1 -> "Rainha";
            case 2 -> "Ponei Mágico";
            case 3 -> "Padre da Vila";
            case 4 -> "TorreHor";
            case 5 -> "TorreVert";
            case 6 -> "Homer Simpson";
            case 7 -> "Joker";
            case 44 -> "Ave Rara";
            default -> null;
        };
    }

    public String[] getSquareInfo(int x, int y) {
        Piece consideredPiece = board.getPecaNaPos(x, y);
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
        int whitePieces = board.getEquipas()[1].getInPlayPieces();
        int blackPieces = board.getEquipas()[0].getInPlayPieces();
        boolean isWhiteKingAlive = board.getEquipas()[1].isKingAlive();
        boolean isBlackKingAlive = board.getEquipas()[0].isKingAlive();

        if (!isBlackKingAlive && isWhiteKingAlive) {
            winnerMessage = "VENCERAM AS BRANCAS";
        } else if (!isWhiteKingAlive && isBlackKingAlive) {
            winnerMessage = "VENCERAM AS PRETAS";
        } else if ((!isWhiteKingAlive && !isBlackKingAlive) || (whitePieces == 0 && blackPieces == 0)) {
            winnerMessage = "EMPATE";
        } else if ((blackPieces == 1 && whitePieces == 1) || board.getConsecPassPlays() == 10) {
            winnerMessage = "EMPATE";
        } else {
            return false;
        }

        return true;
    }

    public GameHistory getGameHistory() {
        return gameHistory;
    }

    public int getTurn() {
        return turn;
    }

    public boolean move(int oriX, int oriY, int destX, int destY) {

        if (board.generalMoveValidation(oriX, oriY, destX, destY)) {                                    //COORD. DENTRO TABULEIRO + PEÇA VALIDA + DESTINO VALIDO
            Piece pecaMovida = board.getPecaNaPos(oriX, oriY);
            if (pecaMovida.specificMoveValidation(oriX, oriY, destX, destY, board.getTabuleiro())) {    //CAMINHO LIVRE + CUMPRE LIMITES MOVE ESPECIFICO
                /*COMEU - JA FOI VERIFICADO SE A PEÇA NO DESTINO É DA EQUIPA CONTRÁRIA NA generalMoveValidation()*/
                /*VERIFCAÇÃO DE RAINHA COME RAINHA/JOKER->RAINHA É FEITA NO SPECIFIC MOVE DA RAINHA*/

                if (board.temPeca(destX, destY)) {
                    Piece pecaNoDestino = board.getPecaNaPos(destX, destY);
                    String typeStr = pecaNoDestino.getTypeStr();
                    int type = pecaNoDestino.getType();

                    board.tiraPecaOrigem(oriX, oriY);
                    pecaNoDestino.capturada();
                    board.metePecaDestino(pecaMovida, destX, destY);

                    board.comeu(type, typeStr);
                    pecaMovida.capturou(pecaNoDestino.getPointsWorth());

                    if (aveRaraInGame && !aveRaraAppeared && aveRaraConditions() != 0 && type != 1) {
                        aveRaraAppeared = true;
                        aveRaraAppears(getCurrentTeamID());
                    }


                } else {
                    board.tiraPecaOrigem(oriX, oriY);
                    board.metePecaDestino(pecaMovida, destX, destY);
                    board.moveu();
                }

                pecaMovida.acertou();

                turn++;
                board.homerClock(this.turn);
                board.jokerClock(this.turn);
                gameHistory.addPlay(oriX, oriY, destX, destY, true);
                return true;
            }
        }
        if (oriX >= 0 && oriX < getBoardSize() && oriY >= 0 && oriY < getBoardSize() && board.temPeca(oriX, oriY)) {
            Piece pecaConsiderada = board.getPecaNaPos(oriX, oriY);
            pecaConsiderada.falhou();
        }
        board.falhou();
        gameHistory.addPlay(oriX, oriY, destX, destY, false);
        return false;
    }

    public List<Comparable> getHints(int oriX, int oriY) {
        Piece movingPiece = this.board.getPecaNaPos(oriX, oriY);
        int size = this.getBoardSize();
        Piece[][] tabuleiro = this.board.getTabuleiro();
        List<Comparable> hintedPlays = new ArrayList<>();

        for (int destY = 0; destY < size; destY++) {
            for (int destX = 0; destX < size; destX++) {
                if (board.generalMoveValidation(oriX, oriY, destX, destY)
                        && movingPiece.specificMoveValidation(oriX, oriY, destX, destY, tabuleiro)) {
                    int pointsWorthMove = 0;
                    if (board.getPecaNaPos(destX, destY) != null) {
                        pointsWorthMove = board.getPecaNaPos(destX, destY).getPointsWorth();
                    }
                    Play hintedPlay = new Play(destX, destY, pointsWorthMove);
                    hintedPlays.add(hintedPlay);
                }
            }
        }

        Collections.sort(hintedPlays);

        return hintedPlays;
    }


    public Map<String, String> customizeBoard() {
        HashMap<String, String> customization = new HashMap<>();
        customization.put("title", "The Chess of the Middle Earth");
        customization.put("imageBlackSquare", "black-box.png");
        customization.put("imageWhiteSquare", "white-box.png");
        customization.put("imageBackground", "imageBackground.png");
        customization.put("boardMarginTop", "10");
        customization.put("boardMarginBottom", "20");

        return customization;
    }

    public JPanel getAuthorsPanel() {
        JPanel result = new JPanel();

        ImageIcon gifIcon = new ImageIcon("src/images/DefesaGoneWrong.gif");
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

    public void fillStartingBoardInfo() {

        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(getBoardSize()).append("\n");
        stringBuilder.append(getBoard().getTotalPieces().size()).append("\n");
        for (Piece piece : getBoard().getTotalPieces()) {
            stringBuilder.append(piece.getId()).append(":").append(piece.getType()).append(":").append(piece.getTeam()).append(":").append(piece.getNickname()).append("\n");
        }
        for (int y = 0; y < getBoardSize(); y++) {
            for (int x = 0; x < getBoardSize(); x++) {
                Piece currentPiece = getBoard().getPecaNaPos(x, y);
                if (currentPiece == null) {
                    stringBuilder.append("0");
                } else {
                    stringBuilder.append(currentPiece.getId());
                }
                if (x < getBoardSize() - 1) {
                    stringBuilder.append(":");
                }
            }
            stringBuilder.append("\n");
        }
        if (aveRaraInGame) {
            stringBuilder.append("--AVE RARA MAY APPEAR--");
            stringBuilder.append("\n");
        }

        gameHistory.setStartingBoard(stringBuilder.toString());
    }

    public void aveRaraAppears(int team) {
        AveRara aveRara = new AveRara(team);
        int kingX = 0;
        int kingY = 0;
        int teamIndex;

        if (team == 10) {
            teamIndex = 0;
        } else {
            teamIndex = 1;
        }

        Map<Integer, Piece> teamPiecesMap = board.getEquipas()[teamIndex].getTeamPieces();

        for (Map.Entry<Integer, Piece> entry : teamPiecesMap.entrySet()) {
            Piece piece = entry.getValue();
            if (piece.getType() == 0) {
                kingX = piece.getPosX();
                kingY = piece.getPosY();
                break;
            }

        }

        String landingCoordinates = aveRara.checkAdjacent(board.getTabuleiro(), kingX, kingY, team);
        int landingX = Integer.parseInt(landingCoordinates.split(",")[0].trim());
        int landingY = Integer.parseInt(landingCoordinates.split(",")[1].trim());

        board.metePecaDestino(aveRara, landingX, landingY);

        board.getEquipas()[board.convertNumEquipas(team)].addPieceToHmap(aveRara);
        board.getTotalPieces().add(aveRara);

    }

    public int aveRaraConditions() {

        if (board.getEquipas()[0].getInPlayPieces() == 1 || board.getEquipas()[1].getInPlayPieces() == 1) {
            if (board.getEquipas()[0].getInPlayPieces() == 1 && board.getEquipas()[1].getInPlayPieces() > board.getEquipas()[1].getTeamPieces().size() / 2) {
                return 10;
            } else if (board.getEquipas()[1].getInPlayPieces() == 1 && board.getEquipas()[0].getInPlayPieces() > board.getEquipas()[0].getTeamPieces().size() / 2) {
                return 20;
            }
        }
        return 0;
    }

    public boolean isAveRaraInGame() {
        return aveRaraInGame;
    }

    public boolean isAveRaraAppeared() {
        return aveRaraAppeared;
    }
}