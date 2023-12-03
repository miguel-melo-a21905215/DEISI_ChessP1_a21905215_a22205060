package pt.ulusofona.lp2.deisichess;

public class InvalidGameInputException extends Exception {

    int lineWithError;
    String problemDescription;
    int obtainedData;

    public InvalidGameInputException(int lineWithError, int obtainedData) {
        this.lineWithError = lineWithError;
        if (obtainedData > 4) {
            this.problemDescription = "DADOS A MAIS (Esperava 4 ; Obtive " + obtainedData + ")";
        } else {
            this.problemDescription = "DADOS A MENOS (Esperava 4 ; Obtive " + obtainedData + ")";
        }

    }


    public int getLinewithError() {
        return lineWithError;
    }

    public String getProblemDescription() {
        return problemDescription;
    }

}
