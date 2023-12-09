package pt.ulusofona.lp2.deisichess;

public class Rainha extends Piece {

    public Rainha(int id, int type, int team, String nickname) {
        super(id, type, team, nickname);
        setPointsWorth(8);
        if (team == 10) {
            setPngLocation("rainha_black.png");
        } else if (team == 20) {
            setPngLocation("rainha_white.png");
        }
        setTypeStr("Rainha");
    }

    public Rainha() {

    }

    @Override
    public boolean specificMoveValidation(int oriX, int oriY, int destX, int destY, Piece[][] tabuleiro) {

        int deltaX = Math.abs(destX - oriX);
        int deltaY = Math.abs(destY - oriY);


        if (destX < 0 || destX >= tabuleiro.length || destY < 0 || destY >= tabuleiro[0].length) {
            return false;
        }


        if (deltaX == 0 && deltaY == 0) {
            return false;
        }

        if ((deltaX <= 5 && deltaY == 0) || (deltaY <= 5 && deltaX == 0) || (deltaX <= 5 && deltaY <= 5 && deltaX == deltaY)) {
            if (deltaX > 0) {
                return lineCheckForPieces(oriX, oriY, destX, tabuleiro) && canCaptureQueen(tabuleiro[destX][destY]);
            } else if (deltaY > 0) {
                return columnCheckForPieces(oriX, oriY, destY, tabuleiro) && canCaptureQueen(tabuleiro[destX][destY]);
            } else {
                return diagonalCheckForPieces(oriX, oriY, destX, destY, tabuleiro) && canCaptureQueen(tabuleiro[destX][destY]);
            }
        }

        return false;
    }


    public boolean canCaptureQueen(Piece targetPiece) {

        if(targetPiece == null) {
            return true;
        }

        if(targetPiece instanceof Joker) {

            Joker joker = (Joker) targetPiece;

            if(joker.getCopyMoveFrom().equals("Rainha")) {
                return false;
            } else {
                return true;
            }
        }

        if(targetPiece.getTypeStr().equals("Rainha") && this.getTeam() == targetPiece.getTeam()) {
            return false;
        }

        return true;
    }


}
