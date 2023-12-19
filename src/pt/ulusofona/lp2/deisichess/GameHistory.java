package pt.ulusofona.lp2.deisichess;

import java.io.*;
import java.util.ArrayList;

/*TODO - FUNCAO PARA CORRER OS MOVES GUARDADOS -> TER UNDO DEPOIS DE LER UM FICHEIRO DE JOGO A MEIO*/
public class GameHistory {


    private String startingBoard;
    private ArrayList<Play> plays;


    public GameHistory() {
        this.plays = new ArrayList<>();
        this.startingBoard = "";
    }

    public void writeFile(File destinationFile) throws FileNotFoundException {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(destinationFile))) {
            writer.write(startingBoard);
            writer.write(initiateHistory());
            writer.newLine();
            int count = 0;
            for (Play play : plays) {
                writer.write(count + "");
                writer.write(play.toString());
                writer.newLine();
                count++;
            }

        } catch (IOException e) {
            throw new FileNotFoundException();
        }
    }

    public void deleteUntilValid() {

        if (plays.size() > 1) {
            int count = plays.size() - 1;
            Play consideredPlay = plays.get(count);
            boolean validDeted = false;

            while (!plays.isEmpty() && (!consideredPlay.isValid() || !validDeted && count >= 0)) {
                if (plays.get(plays.size() - count).isValid()) {
                    validDeted = true;
                }
                plays.remove(count);
                count--;
            }
        }
    }

    public String initiateHistory() {
        return "------------------MOVE HISTORY------------------\n" +
                "moveID\t:oriX\t:oriY\t:destX\t:destY\t:valid";
    }

    public void addPlay(int oriX, int oriY, int destX, int destY, boolean valid) {
        Play play = new Play(oriX, oriY, destX, destY, valid);
        plays.add(play);
    }

    public String getStartingBoard() {
        return startingBoard;
    }

    public void setStartingBoard(String startingBoard) {
        this.startingBoard = startingBoard;
    }

    public ArrayList<Play> getPlays() {
        return plays;
    }

}
