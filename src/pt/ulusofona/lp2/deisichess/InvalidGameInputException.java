package pt.ulusofona.lp2.deisichess;

public class InvalidGameInputException extends Exception {

    private int lineWithError;

    private int obtainedData;
    private String problemDescription;

    public InvalidGameInputException(int lineWithError, int obtainedData) {
        this.lineWithError = lineWithError;
        this.problemDescription = "DADOS A MAIS (Esperava 4 ; Obtive " + obtainedData;
    }


    public int getLinewithError() {
        return lineWithError;
    }

    public String getProblemDescription() {
        return problemDescription;
    }

}
