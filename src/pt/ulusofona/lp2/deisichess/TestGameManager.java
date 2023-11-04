package pt.ulusofona.lp2.deisichess;

import org.junit.jupiter.api.Test;

import java.io.File;

import static org.junit.jupiter.api.Assertions.*;

public class TestGameManager {

    @Test
    void loadGame() {
        GameManager gameManager = new GameManager();
        File file = new File("test_files/4x4.txt");
        assertTrue(gameManager.loadGame(file));
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
    void getSquareInfo() {
        GameManager gameManager = new GameManager();
        // Suponhamos que você tenha um objeto Piece válido nas coordenadas (0, 0) no seu tabuleiro.
        String[] squareInfo = gameManager.getSquareInfo(0, 0);
        assertEquals("1", squareInfo[0]); // Suponha que o ID seja 1 para esse exemplo.
        // Adicione mais asserções de acordo com os detalhes do objeto Piece esperado.
    }

    @Test
    void getBoardSize() {
        GameManager gameManager = new GameManager();
        // Suponhamos que o tamanho do tabuleiro seja 8x8.
        int size = gameManager.getBoardSize();
        assertEquals(8, size);
    }

    @Test
    void getCurrentTeamID() {
        GameManager gameManager = new GameManager();

        // Suponhamos que a equipe atual seja a equipe das Brancas (ID 1).
        int currentTeamID = gameManager.getCurrentTeamID();
        assertEquals(1, currentTeamID);
    }

    @Test
    void gameOver() {
        GameManager gameManager = new GameManager();
        // Implemente testes que verifiquem se o jogo acabou em diferentes cenários (vitória das Pretas, vitória das Brancas, empate).
        // Use asserções para verificar as mensagens de vitória/empate.
    }

    @Test
    void move() {
        GameManager gameManager = new GameManager();
        // Implemente testes para o método move, verificando diferentes cenários de movimento válido e inválido.
        // Use asserções apropriadas para verificar o comportamento esperado.
    }
}
