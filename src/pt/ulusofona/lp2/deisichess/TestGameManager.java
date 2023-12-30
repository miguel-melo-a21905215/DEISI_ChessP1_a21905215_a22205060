package pt.ulusofona.lp2.deisichess;

import org.junit.jupiter.api.Test;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

public class TestGameManager {


    /*--------------------------------- TESTES LOADGAME - EXCEPTIONS ---------------------------------*/

    @Test
    void loadGameGeneralData() throws IOException, InvalidGameInputException {
        GameManager gameManager = new GameManager();
        File file = new File("test-files/8x8.txt");
        gameManager.loadGame(file);
        assertEquals(8, gameManager.getBoard().getTabuleiro().length);
        assertEquals(8, gameManager.getBoard().getTabuleiro()[0].length);

        assertEquals(16, gameManager.getBoard().getTotalPieces().size());
        assertEquals(2, gameManager.getBoard().getEquipas().length);

        assertEquals(10, gameManager.getBoard().getEquipas()[0].getIdEquipa());
        assertEquals(20, gameManager.getBoard().getEquipas()[1].getIdEquipa());

        assertEquals(8, gameManager.getBoard().getEquipas()[0].getTeamPieces().size());
        assertEquals(8, gameManager.getBoard().getEquipas()[1].getTeamPieces().size());


    }

    @Test
    void loadGameMissingFile() {
        GameManager gameManager = new GameManager();
        File file = new File("test-files/missing_file.txt");
        assertThrows(FileNotFoundException.class, () -> gameManager.loadGame(file));
    }

    @Test
    void loadGameExtraData() {
        GameManager gameManager = new GameManager();
        File file = new File("test-files/8x8_with_extra_data.txt");

        InvalidGameInputException exception = assertThrows(InvalidGameInputException.class, () -> gameManager.loadGame(file));

        assertEquals(13, exception.getLineWithError());
        assertEquals("DADOS A MAIS (Esperava: 4 ; Obtive: 5)", exception.getProblemDescription());
    }

    @Test
    void loadGameMissingData() {
        GameManager gameManager = new GameManager();
        File file = new File("test-files/8x8_with_missing_data.txt");

        InvalidGameInputException exception = assertThrows(InvalidGameInputException.class, () -> gameManager.loadGame(file));

        assertEquals(13, exception.getLineWithError());
        assertEquals("DADOS A MENOS (Esperava: 4 ; Obtive: 3)", exception.getProblemDescription());
    }


    /*--------------------------------- TESTES PIECEINFO + PIECEINFOASSTRING ---------------------------------*/
    @Test
    void getPieceInfoReiBrancoEmJogo() throws IOException, InvalidGameInputException {
        GameManager gameManager = new GameManager();
        File file = new File("test-files/8x8.txt");
        gameManager.loadGame(file);
        String[] actualArray = gameManager.getPieceInfo(1);
        String[] expectedArray = {"1", "0", "10", "O Poderoso Chefao", "em jogo", "0", "0"};
        assertArrayEquals(expectedArray, actualArray);
    }

    @Test
    void getPieceInfoAsStringReiPreto() throws IOException, InvalidGameInputException {
        GameManager gameManager = new GameManager();
        File file = new File("test-files/8x8.txt");
        gameManager.loadGame(file);

        String actualResult = gameManager.getPieceInfoAsString(1);
        String expectedResult = "1 | Rei | (infinito) | 10 | O Poderoso Chefao @ (0, 0)";
        assertEquals(expectedResult, actualResult);

    }

    @Test
    void getPieceInfoRainhaPretaEmJogo() throws IOException, InvalidGameInputException {
        GameManager gameManager = new GameManager();
        File file = new File("test-files/8x8.txt");
        gameManager.loadGame(file);
        String[] actualArray = gameManager.getPieceInfo(2);
        String[] expectedArray = {"2", "1", "10", "A Dama Selvagem", "em jogo", "1", "0"};
        assertArrayEquals(expectedArray, actualArray);
    }

    @Test
    void getPieceInfoAsStringJokerPreto() throws IOException, InvalidGameInputException {
        GameManager gameManager = new GameManager();
        File file = new File("test-files/8x8.txt");
        gameManager.loadGame(file);

        String actualResult = gameManager.getPieceInfoAsString(8);
        String expectedResult = "8 | Joker/Rainha | 4 | 10 | O Beberolas @ (7, 0)";
        assertEquals(expectedResult, actualResult);

    }

    @Test
    void getPieceInfoAsStringPoneiPreto() throws IOException, InvalidGameInputException {
        GameManager gameManager = new GameManager();
        File file = new File("test-files/8x8.txt");
        gameManager.loadGame(file);

        String actualResult = gameManager.getPieceInfoAsString(3);
        String expectedResult = "3 | Ponei Mágico | 5 | 10 | O Grande Artista @ (2, 0)";

        assertEquals(expectedResult, actualResult);

    }

    @Test
    void getPieceInfoAsStringRainhaPreta() throws IOException, InvalidGameInputException {
        GameManager gameManager = new GameManager();
        File file = new File("test-files/8x8.txt");
        gameManager.loadGame(file);

        String actualResult = gameManager.getPieceInfoAsString(2);
        String expectedResult = "2 | Rainha | 8 | 10 | A Dama Selvagem @ (1, 0)";

        assertEquals(expectedResult, actualResult);

    }

    @Test
    void getPieceInfoAsStringReiBranco() throws IOException, InvalidGameInputException {
        GameManager gameManager = new GameManager();
        File file = new File("test-files/8x8.txt");
        gameManager.loadGame(file);

        String actualResult = gameManager.getPieceInfoAsString(9);
        String expectedResult = "9 | Rei | (infinito) | 20 | Chefe dos Indios @ (0, 7)";
        assertEquals(expectedResult, actualResult);

    }

    @Test
    void getPieceInfoAsStringPoneiBranco() throws IOException, InvalidGameInputException {
        GameManager gameManager = new GameManager();
        File file = new File("test-files/8x8.txt");
        gameManager.loadGame(file);

        String actualResult = gameManager.getPieceInfoAsString(11);
        String expectedResult = "11 | Ponei Mágico | 5 | 20 | My Little Pony @ (2, 7)";

        assertEquals(expectedResult, actualResult);

    }

    @Test
    void getPieceInfoAsStringRainhaBranco() throws IOException, InvalidGameInputException {
        GameManager gameManager = new GameManager();
        File file = new File("test-files/8x8.txt");
        gameManager.loadGame(file);

        String actualResult = gameManager.getPieceInfoAsString(10);
        String expectedResult = "10 | Rainha | 8 | 20 | A Barulhenta do Bairro @ (1, 7)";

        assertEquals(expectedResult, actualResult);

    }

    @Test
    void getPieceInfoAsStringTorreVBranca() throws IOException, InvalidGameInputException {
        GameManager gameManager = new GameManager();
        File file = new File("test-files/8x8.txt");
        gameManager.loadGame(file);

        String actualResult = gameManager.getPieceInfoAsString(14);
        String expectedResult = "14 | TorreVert | 3 | 20 | Torre Trapalhona @ (5, 7)";

        assertEquals(expectedResult, actualResult);

    }

    @Test
    void getPieceInfoAsStringTorreHBranca() throws IOException, InvalidGameInputException {
        GameManager gameManager = new GameManager();
        File file = new File("test-files/8x8.txt");
        gameManager.loadGame(file);

        String actualResult = gameManager.getPieceInfoAsString(13);
        String expectedResult = "13 | TorreHor | 3 | 20 | Torre Poderosa @ (4, 7)";

        assertEquals(expectedResult, actualResult);

    }

    @Test
    void getSquareInfoTesting() throws IOException, InvalidGameInputException {
        GameManager gameManager = new GameManager();
        File file = new File("test-files/8x8.txt");
        gameManager.loadGame(file);
        assertEquals("1", gameManager.getSquareInfo(0, 0)[0]);
        assertEquals(5, gameManager.getSquareInfo(0, 0).length);

        assertEquals(0, gameManager.getSquareInfo(2, 2).length);

        assertEquals(6, gameManager.customizeBoard().size());
    }



    /*--------------------------------- TESTES BOARD - GETTERS ---------------------------------*/

    @Test
    void getBoardSize() throws IOException, InvalidGameInputException {
        GameManager gameManager = new GameManager();
        File file = new File("test-files/8x8.txt");
        gameManager.loadGame(file);

        int size = gameManager.getBoardSize();
        assertEquals(8, size);
    }

    @Test
    void getCurrentTeamID() throws IOException, InvalidGameInputException {
        GameManager gameManager = new GameManager();
        File file = new File("test-files/4x4.txt");
        gameManager.loadGame(file);
        // Suponhamos que a equipe atual seja a equipe das Brancas (ID 1).
        int currentTeamID = gameManager.getCurrentTeamID();
        assertEquals(10, currentTeamID);
    }


    /*--------------------------------- TESTES GAME OVER --------------------------------------*/

    @Test
    void gameOverVitoriaBrancasLeitura() throws IOException, InvalidGameInputException {
        /*TODO - LER FICHEIRO TERMINADO || MOVER PARA COMER REI*2 || COMER + 10 PASSIVAS*/
        GameManager gameManager = new GameManager();
        File file = new File("test-files/8x8_No_Black_King.txt");
        gameManager.loadGame(file);
        assertTrue(gameManager.gameOver());
        assertEquals("VENCERAM AS BRANCAS", gameManager.getWinnerMessage());
        assertEquals("JOGO DE CRAZY CHESS", gameManager.getGameResults().get(0));
        assertEquals("Resultado: VENCERAM AS BRANCAS", gameManager.getGameResults().get(1));
        assertEquals("---", gameManager.getGameResults().get(2));
        assertEquals("Equipa das Pretas", gameManager.getGameResults().get(3));
        assertEquals("0", gameManager.getGameResults().get(4));
        assertEquals("0", gameManager.getGameResults().get(5));
        assertEquals("0", gameManager.getGameResults().get(6));
        assertEquals("Equipa das Brancas", gameManager.getGameResults().get(7));
        assertEquals("0", gameManager.getGameResults().get(8));
        assertEquals("0", gameManager.getGameResults().get(9));
        assertEquals("0", gameManager.getGameResults().get(10));

    }

    @Test
    void gameOverVitoriaPretasLeitura() throws IOException, InvalidGameInputException {
        GameManager gameManager = new GameManager();
        File file = new File("test-files/8x8_No_White_King.txt");
        gameManager.loadGame(file);
        assertTrue(gameManager.gameOver());
        assertEquals("VENCERAM AS PRETAS", gameManager.getWinnerMessage());
        assertEquals("JOGO DE CRAZY CHESS", gameManager.getGameResults().get(0));
        assertEquals("Resultado: VENCERAM AS PRETAS", gameManager.getGameResults().get(1));
        assertEquals("---", gameManager.getGameResults().get(2));
        assertEquals("Equipa das Pretas", gameManager.getGameResults().get(3));
        assertEquals("0", gameManager.getGameResults().get(4));
        assertEquals("0", gameManager.getGameResults().get(5));
        assertEquals("0", gameManager.getGameResults().get(6));
        assertEquals("Equipa das Brancas", gameManager.getGameResults().get(7));
        assertEquals("0", gameManager.getGameResults().get(8));
        assertEquals("0", gameManager.getGameResults().get(9));
        assertEquals("0", gameManager.getGameResults().get(10));

    }

    @Test
    void gameOverEmpateLeitura() throws IOException, InvalidGameInputException {
        GameManager gameManager = new GameManager();
        File file = new File("test-files/8x8_No_Kings.txt");
        gameManager.loadGame(file);
        assertTrue(gameManager.gameOver());
        assertEquals("EMPATE", gameManager.getWinnerMessage());
        assertEquals("JOGO DE CRAZY CHESS", gameManager.getGameResults().get(0));
        assertEquals("Resultado: EMPATE", gameManager.getGameResults().get(1));
        assertEquals("---", gameManager.getGameResults().get(2));
        assertEquals("Equipa das Pretas", gameManager.getGameResults().get(3));
        assertEquals("0", gameManager.getGameResults().get(4));
        assertEquals("0", gameManager.getGameResults().get(5));
        assertEquals("0", gameManager.getGameResults().get(6));
        assertEquals("Equipa das Brancas", gameManager.getGameResults().get(7));
        assertEquals("0", gameManager.getGameResults().get(8));
        assertEquals("0", gameManager.getGameResults().get(9));
        assertEquals("0", gameManager.getGameResults().get(10));

    }

    @Test
    void main() {
        assertEquals("MY PRECIOUS", Main.precious());
    }


    /*------------------------ TESTES MOVE ESPECIFICOS + GERAIS  ---------------------------------*/

    @Test
    public void testgetHintBegining() throws IOException, InvalidGameInputException {
        GameManager gameManager = new GameManager();
        File file = new File("test-files/8x8.txt");
        gameManager.loadGame(file);
        assertFalse(gameManager.gameOver());

        assertEquals(2, gameManager.getHints(0, 0).size());         //REI
        assertEquals(11, gameManager.getHints(1, 0).size());        //RAINHA
        assertEquals(2, gameManager.getHints(2, 0).size());         //PONEI MAGICO
        assertEquals(6, gameManager.getHints(3, 0).size());         //PADRE DA VILA
        assertEquals(0, gameManager.getHints(4, 0).size());         //TORRE HORIZONTAL
        assertEquals(7, gameManager.getHints(5, 0).size());         //TORRE VERTICAL
        assertEquals(0, gameManager.getHints(6, 0).size());         //HOMER
        assertEquals(10, gameManager.getHints(7, 0).size());        //JOKER


        assertTrue(gameManager.move(0, 0, 0, 1));

        assertEquals(2, gameManager.getHints(0, 7).size());         //REI
        assertEquals(11, gameManager.getHints(1, 7).size());        //RAINHA
        assertEquals(2, gameManager.getHints(2, 7).size());         //PONEI MAGICO
        assertEquals(6, gameManager.getHints(3, 7).size());         //PADRE DA VILA
        assertEquals(0, gameManager.getHints(4, 7).size());         //TORRE HORIZONTAL
        assertEquals(7, gameManager.getHints(5, 7).size());         //TORRE VERTICAL
        assertEquals(2, gameManager.getHints(6, 7).size());         //HOMER
        assertEquals(1, gameManager.getHints(7, 7).size());         //JOKER

        assertFalse(gameManager.move(5, 7, 4, 3));              //MOVE ERRADO
        assertFalse(gameManager.gameOver());                                          //NOT GAME OVER
        assertTrue(gameManager.move(5, 7, 5, 0));               //MOVE CERTO - TORRE VERT COME TORRE VERT

    }


    /*----------------------TESTES MOVES DAS PEÇAS-----------------------------------*/
    @Test
    public void rainhaTesting() throws IOException, InvalidGameInputException {
        GameManager gameManager = new GameManager();
        File file = new File("test-files/8x8_Rainha_Test.txt");
        gameManager.loadGame(file);
        assertFalse(gameManager.gameOver());

        assertEquals(22, gameManager.getHints(3, 3).size());
        assertEquals(0, gameManager.getHints(3, 4).size());
        assertFalse(gameManager.move(3, 3, 3, 4));
        assertTrue(gameManager.move(3, 3, 4, 4));
        assertEquals(22, gameManager.getHints(3, 4).size());
    }

    @Test
    public void padreVilaTesting() throws IOException, InvalidGameInputException {
        GameManager gameManager = new GameManager();
        File file = new File("test-files/8x8_PadreVila_Test.txt");
        gameManager.loadGame(file);
        assertFalse(gameManager.gameOver());

        assertEquals(11, gameManager.getHints(3, 3).size());
        assertEquals(0, gameManager.getHints(3, 4).size());
        assertFalse(gameManager.move(3, 3, 3, 4));
        assertFalse(gameManager.move(3, 3, 7, 7));
        assertTrue(gameManager.move(3, 3, 4, 4));
        assertEquals(11, gameManager.getHints(3, 4).size());
        assertTrue(gameManager.move(3, 4, 2, 3));
        assertEquals(12, gameManager.getHints(4, 4).size());
    }

    @Test
    public void torreVertTesting() throws IOException, InvalidGameInputException {
        GameManager gameManager = new GameManager();
        File file = new File("test-files/8x8_TorreVert_Test.txt");
        gameManager.loadGame(file);
        assertFalse(gameManager.gameOver());

        assertEquals(7, gameManager.getHints(5, 0).size());
        assertEquals(0, gameManager.getHints(5, 7).size());
        assertFalse(gameManager.move(5, 0, 4, 0));
        assertFalse(gameManager.move(5, 0, 6, 1));
        assertTrue(gameManager.move(5, 0, 5, 6));
        assertEquals(1, gameManager.getHints(5, 7).size());
        assertFalse(gameManager.move(5, 7, 5, 5));
        assertTrue(gameManager.move(5, 7, 5, 6));
        assertEquals(0, gameManager.getHints(5, 6).size());
        assertTrue(gameManager.move(0, 0, 1, 0));
        assertTrue(gameManager.move(5, 6, 5, 5));
        assertTrue(gameManager.move(1, 0, 2, 0));
        assertTrue(gameManager.move(5, 5, 5, 6));
        assertTrue(gameManager.move(2, 0, 3, 0));
        assertTrue(gameManager.move(5, 6, 5, 5));
        assertTrue(gameManager.move(3, 0, 4, 0));
        assertTrue(gameManager.move(5, 5, 5, 6));
        assertTrue(gameManager.move(4, 0, 5, 0));
        assertEquals(7, gameManager.getHints(5, 6).size());
        Play winningPLay = new Play(5, 0, 1000);
        assertEquals(winningPLay.toString(), gameManager.getHints(5, 6).get(0).toString());
        assertTrue(gameManager.move(5, 6, 5, 0));
        assertTrue(gameManager.gameOver());
    }

    @Test
    public void torreHorTesting() throws IOException, InvalidGameInputException {
        GameManager gameManager = new GameManager();
        File file = new File("test-files/8x8_TorreHor_Test.txt");
        gameManager.loadGame(file);
        assertFalse(gameManager.gameOver());

        assertEquals(6, gameManager.getHints(5, 0).size());
        assertEquals(0, gameManager.getHints(5, 7).size());
        assertFalse(gameManager.move(5, 0, 0, 0));
        assertFalse(gameManager.move(5, 0, 0, 5));
        assertTrue(gameManager.move(5, 0, 2, 0));

        assertEquals(3, gameManager.getHints(5, 7).size());

    }

    @Test
    public void poneiMagicoTesting() throws IOException, InvalidGameInputException {
        GameManager gameManager = new GameManager();
        File file = new File("test-files/8x8_PoneiMagico_Test.txt");
        gameManager.loadGame(file);
        assertFalse(gameManager.gameOver());


        assertEquals(4, gameManager.getHints(3, 3).size());
        gameManager.move(6, 5, 5, 5);

        gameManager.move(0, 6, 0, 5);
        assertEquals(3, gameManager.getHints(3, 3).size());

        gameManager.move(5, 5, 6, 5);
        gameManager.move(5, 7, 5, 3);
        assertEquals(4, gameManager.getHints(3, 3).size());

        gameManager.move(0, 0, 1, 1);
        gameManager.move(0, 5, 1, 5);
        assertEquals(3, gameManager.getHints(3, 3).size());
        Play winningPlay = new Play(1, 5, 1000);
        Play noPlay = new Play(1, 1, 1000);
        assertEquals(winningPlay.toString(), gameManager.getHints(3, 3).get(0).toString());
        assertFalse(gameManager.getHints(3, 3).contains(noPlay));

        gameManager.move(5, 6, 5, 7);
        gameManager.move(1, 5, 1, 4);

        assertEquals(3, gameManager.getHints(3, 3).size());
        winningPlay = new Play(1, 4, 1000);
        assertNotEquals(winningPlay.toString(), gameManager.getHints(3, 3).get(0).toString());
    }

    @Test
    public void homerTesting() throws IOException, InvalidGameInputException {
        GameManager gameManager = new GameManager();
        File file = new File("test-files/8x8_Homer_Test.txt");
        gameManager.loadGame(file);
        assertFalse(gameManager.gameOver());

        assertEquals(0, gameManager.getHints(3, 2).size());
        assertEquals("Doh! zzzzzz", gameManager.getPieceInfoAsString(7));

        assertEquals("Doh! zzzzzz", gameManager.getPieceInfoAsString(15));
        gameManager.move(4, 4, 5, 4);
        assertEquals(4, gameManager.getHints(3, 4).size());
        assertTrue(gameManager.move(3, 4, 2, 3));

        gameManager.move(5, 4, 4, 4);

        assertEquals("Doh! zzzzzz", gameManager.getPieceInfoAsString(7));
        assertEquals("Doh! zzzzzz", gameManager.getPieceInfoAsString(15));
    }

    @Test
    public void jokerTesting() throws IOException, InvalidGameInputException {
        GameManager gameManager = new GameManager();
        File file = new File("test-files/8x8.txt");
        gameManager.loadGame(file);

        assertTrue(gameManager.move(7, 0, 7, 5));
        assertFalse(gameManager.move(7, 7, 7, 2));
        assertTrue(gameManager.move(6, 7, 5, 6));
        assertFalse(gameManager.move(7, 7, 5, 5));
        assertTrue(gameManager.move(7, 5, 5, 3));
        assertTrue(gameManager.move(7, 7, 6, 7));
        assertTrue(gameManager.move(5, 3, 5, 6));
        assertTrue(gameManager.move(6, 7, 7, 6));
    }

    @Test
    public void saveGameTesting() throws IOException, InvalidGameInputException {
        GameManager gameManager = new GameManager();
        File file = new File("test-files/8x8.txt");
        gameManager.loadGame(file);

        assertEquals(0, gameManager.getGameHistory().getPlays().size());
        gameManager.move(0, 0, 1, 1);
        gameManager.move(0, 7, 0, 7);
        assertEquals(2, gameManager.getGameHistory().getPlays().size());
        gameManager.move(0, 7, 0, 6);

        gameManager.undo();

        assertEquals(10, gameManager.getBoard().getPecaNaPos(1, 1).getTeam());
        ;
        assertEquals(20, gameManager.getBoard().getPecaNaPos(0, 7).getTeam());
        ;
        assertEquals(2, gameManager.getGameHistory().getPlays().size());


    }

    @Test
    public void enumTesting() throws IOException, InvalidGameInputException {
        GameManager gameManager = new GameManager();
        File file = new File("test-files/8x8_saveGame_Test.txt");
        gameManager.loadGame(file);

        StatType statType = StatType.TOP_5_PONTOS;

        ArrayList<String> result = StatisticsKt.getStatsCalculator(statType).invoke(gameManager);
        assertEquals(2, result.size());

        statType = StatType.PECAS_MAIS_5_CAPTURAS;

        result = StatisticsKt.getStatsCalculator(statType).invoke(gameManager);
        assertEquals(0, result.size());

        statType = StatType.TOP_5_CAPTURAS;

        result = StatisticsKt.getStatsCalculator(statType).invoke(gameManager);
        assertEquals(5, result.size());

        statType = StatType.PECAS_MAIS_BARALHADAS;

        result = StatisticsKt.getStatsCalculator(statType).invoke(gameManager);
        assertEquals(3, result.size());

        statType = StatType.TIPOS_CAPTURADOS;
        result = StatisticsKt.getStatsCalculator(statType).invoke(gameManager);
        assertEquals(1, result.size());

    }
}
