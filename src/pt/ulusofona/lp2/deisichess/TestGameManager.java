package pt.ulusofona.lp2.deisichess;

public class TestGameManager {

/*
    @Test
    void getPieceInfo() throws IOException, InvalidGameInputException {
        GameManager gameManager = new GameManager();
        File file = new File("test-files/4x4.txt");
        gameManager.loadGame(file);
        String[] actualArray = gameManager.getPieceInfo(1);
        String[] expectedArray = {"1", "0", "0", "Chefe", "em jogo", "1", "0"};
        assertArrayEquals(expectedArray, actualArray);
    }

    @Test
    void getPieceInfoAsString() throws IOException, InvalidGameInputException {
        GameManager gameManager = new GameManager();
        File file = new File("test-files/4x4.txt");
        gameManager.loadGame(file);

        String actualResult = gameManager.getPieceInfoAsString(1);
        String expectedResult = "1 | 0 | 0 | Chefe @ (1, 0)";
        assertEquals(actualResult, expectedResult);

    }

    @Test
    void getPieceInfoAsString2() throws IOException, InvalidGameInputException {
        GameManager gameManager = new GameManager();
        File file = new File("test-files/8x8.txt");
        gameManager.loadGame(file);

        String ActualResult = gameManager.getPieceInfoAsString(3);
        String expectedResult = "3 | 0 | 0 | Grande Artista @ (2, 1)";

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
    }*/
}
