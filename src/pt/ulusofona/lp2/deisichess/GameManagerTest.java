package pt.ulusofona.lp2.deisichess;

import org.junit.jupiter.api.Test;
import java.io.File;
import static org.junit.jupiter.api.Assertions.*;

public class GameManagerTest {

    @Test
    void loadGame() {
        GameManager gameManager = new GameManager();
        File file = new File("4x4.txt");
        assertTrue(gameManager.loadGame(file));
    }

    @Test
    void getPieceInfo() {
        GameManager gameManager = new GameManager();
        // Suponhamos que você tenha um objeto Piece válido com ID 1 no seu tabuleiro.
        String[] pieceInfo = gameManager.getPieceInfo(1);
        assertEquals("1", pieceInfo[0]);
        // Adicione mais asserções de acordo com os detalhes do objeto Piece esperado.
    }

    @Test
    void getPieceInfoAsString() {
        GameManager gameManager = new GameManager();
        // Suponhamos que você tenha um objeto Piece válido com ID 1 no seu tabuleiro.
        String pieceInfoString = gameManager.getPieceInfoAsString(1);
        // Verifique se a string gerada é válida de acordo com os detalhes do objeto Piece esperado.
        // Use asserções apropriadas para verificar os valores na string.
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
