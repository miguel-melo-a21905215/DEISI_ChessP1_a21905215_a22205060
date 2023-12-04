package pt.ulusofona.lp2.deisichess;

import org.junit.jupiter.api.Test;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;

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

        assertEquals(10, gameManager.getBoard().getEquipas()[0].idEquipa);
        assertEquals(20, gameManager.getBoard().getEquipas()[1].idEquipa);

        assertEquals(8, gameManager.getBoard().getEquipas()[0].teamPieces.size());
        assertEquals(8, gameManager.getBoard().getEquipas()[1].teamPieces.size());

        boolean containsWhitePieces = false;
        boolean containsBlackPieces = false;
        for (Piece blackPiece : gameManager.getBoard().getEquipas()[0].teamPieces.values()) {
            if (blackPiece.getTeam() == 20) {
                containsWhitePieces = true;
                break;
            }

        }

        for (Piece whitePiece : gameManager.getBoard().getEquipas()[1].teamPieces.values()) {
            if (whitePiece.getTeam() == 10) {
                containsWhitePieces = true;
                break;
            }

        }
        assertFalse(containsWhitePieces);
        assertFalse(containsWhitePieces);
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
        String[] expectedArray = {"1", "0", "1000", "10", "O Poderoso Chefao", "em jogo", "00"};
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
        String[] expectedArray = {"2", "1", "8", "10", "A Dama Selvagem", "em jogo", "10"};
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
        String expectedResult = "3 | Ponei Magico | 5 | 10 | O Grande Artista @ (2, 0)";

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
        String expectedResult = "11 | Ponei Magico | 5 | 20 | My Little Pony @ (2, 7)";

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
    void gameOver() throws IOException, InvalidGameInputException {
        GameManager gameManager = new GameManager();
        File file = new File("test-files/4x4.txt");
        gameManager.loadGame(file);
        /*TODO - LER FICHEIRO TERMINADO || MOVER PARA COMER REI*2 || COMER + 10 PASSIVAS*/
    }


    /*------------------------ TESTES MOVE ESPECIFICOS + GERAIS  ---------------------------------*/

    /*@Test
    public void testValidMove() throws IOException, InvalidGameInputException {

        GameManager gameManager = new GameManager();
        File file = new File("test-files/4x4.txt");
        gameManager.loadGame(file);

        // Obtenha a situação do tabuleiro antes do movimento
        String[] initialSquareInfo = gameManager.getSquareInfo(1, 0);

        // Tente fazer um movimento válido
        boolean validMove = gameManager.move(1, 0, 1, 1);

        // Obtenha a situação do tabuleiro após o movimento
        String[] updatedResultInfo = gameManager.getSquareInfo(1, 1);

        // Verifique se o movimento foi válido (true)
        assertTrue(validMove);
        assertNotEquals(initialSquareInfo, updatedResultInfo); // Verifica se o ID da peça mudou
    }*/
}
