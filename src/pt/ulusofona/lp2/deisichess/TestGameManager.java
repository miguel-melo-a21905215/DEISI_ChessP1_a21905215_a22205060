package pt.ulusofona.lp2.deisichess;

import org.junit.jupiter.api.Test;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;

import static org.junit.jupiter.api.Assertions.*;

public class TestGameManager {

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

        assertEquals(13, exception.getLinewithError());
        assertEquals("DADOS A MAIS (Esperava 4 ; Obtive 5)", exception.getProblemDescription());
    }

    @Test
    void loadGameMissingData() {
        GameManager gameManager = new GameManager();
        File file = new File("test-files/8x8_with_missing_data.txt");

        InvalidGameInputException exception = assertThrows(InvalidGameInputException.class, () -> gameManager.loadGame(file));

        assertEquals(13, exception.getLinewithError());
        assertEquals("DADOS A MENOS (Esperava 4 ; Obtive 3)", exception.getProblemDescription());
    }


    @Test
    void getPieceInfoReiEmJogo() throws IOException, InvalidGameInputException {
        GameManager gameManager = new GameManager();
        File file = new File("test-files/8x8.txt");
        gameManager.loadGame(file);
        String[] actualArray = gameManager.getPieceInfo(1);
        String[] expectedArray = {"1", "Rei", "1000", "10", "O Poderoso Chefao", "em jogo", "0", "0"};
        assertArrayEquals(expectedArray, actualArray);
    }

    @Test
    void getPieceInfoAsStringReiPreto() throws IOException, InvalidGameInputException {
        GameManager gameManager = new GameManager();
        File file = new File("test-files/8x8.txt");
        gameManager.loadGame(file);

        String actualResult = gameManager.getPieceInfoAsString(1);
        String expectedResult = "1 | Rei | (infinito) | 10 | O Poderoso Chefao @ (0, 0)";
        assertEquals(actualResult, expectedResult);

    }

    @Test
    void getPieceInfoAsStringPoneiPreto() throws IOException, InvalidGameInputException {
        GameManager gameManager = new GameManager();
        File file = new File("test-files/8x8.txt");
        gameManager.loadGame(file);

        String ActualResult = gameManager.getPieceInfoAsString(3);
        String expectedResult = "3 | Ponei Magico | 5 | 10 | O Grande Artista @ (2, 0)";

        assertEquals(ActualResult, expectedResult);

    }

    @Test
    void getPieceInfoAsStringRainhaPreta() throws IOException, InvalidGameInputException {
        GameManager gameManager = new GameManager();
        File file = new File("test-files/8x8.txt");
        gameManager.loadGame(file);

        String ActualResult = gameManager.getPieceInfoAsString(2);
        String expectedResult = "2 | Rainha | 8 | 10 | A Dama Selvagem @ (1, 0)";

        assertEquals(ActualResult, expectedResult);

    }

    @Test
    void getPieceInfoAsStringReiBranco() throws IOException, InvalidGameInputException {
        GameManager gameManager = new GameManager();
        File file = new File("test-files/8x8.txt");
        gameManager.loadGame(file);

        String actualResult = gameManager.getPieceInfoAsString(9);
        String expectedResult = "9 | Rei | (infinito) | 20 | Chefe dos Indios @ (0, 7)";
        assertEquals(actualResult, expectedResult);

    }

    @Test
    void getPieceInfoAsStringPoneiBranco() throws IOException, InvalidGameInputException {
        GameManager gameManager = new GameManager();
        File file = new File("test-files/8x8.txt");
        gameManager.loadGame(file);

        String ActualResult = gameManager.getPieceInfoAsString(11);
        String expectedResult = "11 | Ponei Magico | 5 | 20 | My Little Pony @ (2, 7)";

        assertEquals(ActualResult, expectedResult);

    }

    @Test
    void getPieceInfoAsStringRainhaBranco() throws IOException, InvalidGameInputException {
        GameManager gameManager = new GameManager();
        File file = new File("test-files/8x8.txt");
        gameManager.loadGame(file);

        String ActualResult = gameManager.getPieceInfoAsString(10);
        String expectedResult = "10 | Rainha | 8 | 20 | A Barulhenta do Bairro @ (1, 7)";

        assertEquals(ActualResult, expectedResult);

    }


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

    @Test
    void gameOver() throws IOException, InvalidGameInputException {
        GameManager gameManager = new GameManager();
        File file = new File("test-files/4x4.txt");
        gameManager.loadGame(file);
        // Implemente testes que verifiquem se o jogo acabou em diferentes cenários (vitória das Pretas, vitória das Brancas, empate).
        // Use asserções para verificar as mensagens de vitória/empate.
    }

    @Test
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
    }
}
