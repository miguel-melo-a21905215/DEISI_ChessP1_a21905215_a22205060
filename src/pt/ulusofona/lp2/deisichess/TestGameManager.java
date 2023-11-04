package pt.ulusofona.lp2.deisichess;

import org.junit.jupiter.api.Test;

import java.io.File;

import static org.junit.jupiter.api.Assertions.*;

public class TestGameManager {

    @Test
    void loadGameCorreto() {
        GameManager gameManager = new GameManager();
        File file = new File("test_files/4x4.txt");
        assertTrue(gameManager.loadGame(file));
    }
    @Test
    void loadGameIncorreto() {
        GameManager gameManager = new GameManager();
        File file = new File("test_files/shalom.txt");
        assertFalse(gameManager.loadGame(file));
    }


    @Test
    void getPieceInfo() {
        GameManager gameManager = new GameManager();
        File file = new File("test_files/4x4.txt");
        gameManager.loadGame(file);
        String[] actualArray = gameManager.getPieceInfo(1);
        String[] expectedArray = {"1", "0", "0", "Chefe", "em jogo", "1", "0"};
        assertArrayEquals(expectedArray, actualArray);
    }

    @Test
    void getPieceInfoAsString() {
        GameManager gameManager = new GameManager();
        File file = new File("test_files/4x4.txt");
        gameManager.loadGame(file);

        String ActualResult = gameManager.getPieceInfoAsString(1);
        String expectedResult = "1 | 0 | 0 | Chefe @ (1, 0)";

    }

    @Test
    void getPieceInfoAsString2() {
        GameManager gameManager = new GameManager();
        File file = new File("test_files/8x8.txt");
        gameManager.loadGame(file);

        String ActualResult = gameManager.getPieceInfoAsString(3);
        String expectedResult = "1 | 0 | 0 | Grande Artista @ (2, 1)";

    }


    @Test
    void getBoardSize() {
        GameManager gameManager = new GameManager();
        File file = new File("test_files/8x8.txt");
        gameManager.loadGame(file);

        int size = gameManager.getBoardSize();
        assertEquals(8, size);
    }

    @Test
    void getCurrentTeamID() {
        GameManager gameManager = new GameManager();
        File file = new File("test_files/4x4.txt");
        gameManager.loadGame(file);
        // Suponhamos que a equipe atual seja a equipe das Brancas (ID 1).
        int currentTeamID = gameManager.getCurrentTeamID();
        assertEquals(0, currentTeamID);
    }

    @Test
    void gameOver() {
        GameManager gameManager = new GameManager();
        File file = new File("test_files/4x4.txt");
        gameManager.loadGame(file);
        // Implemente testes que verifiquem se o jogo acabou em diferentes cenários (vitória das Pretas, vitória das Brancas, empate).
        // Use asserções para verificar as mensagens de vitória/empate.
    }

    @Test
    public void testValidMove() {

        GameManager gameManager = new GameManager();
        File file = new File("test_files/4x4.txt");
        gameManager.loadGame(file);

        // Obtenha a situação do tabuleiro antes do movimento
        String[] initialSquareInfo = gameManager.getSquareInfo(1, 0);

        // Tente fazer um movimento válido
        boolean validMove = gameManager.move(1, 0, 1, 1);

        // Obtenha a situação do tabuleiro após o movimento
        String[] updatedSquareInfo = gameManager.getSquareInfo(1, 1);

        // Verifique se o movimento foi válido (true)
        assertTrue(validMove);
        assertNotEquals(initialSquareInfo, updatedSquareInfo); // Verifica se o ID da peça mudou
    }
}
