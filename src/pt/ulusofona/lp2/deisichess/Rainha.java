package pt.ulusofona.lp2.deisichess;

public class Rainha extends Piece {

    public Rainha(int id, int type, int team, String nickname) {
        super(id, type, team, nickname);
        this.pointsWorth = 8;
        if (team == 10) {
            this.pngLocation = "rainha_black.png";
        } else if (team == 20) {
            this.pngLocation = "rainha_white.png";
        }
        this.inPlay = false;
        this.typeStr = "Rainha";
    }

    public Rainha() {

    }

    @Override
    public boolean specificMoveValidation(int oriX, int oriY, int destX, int destY, Piece[][] tabuleiro) {

        int deltaX = Math.abs(destX - oriX);
        int deltaY = Math.abs(destY - oriY);

        if ((deltaX == 0 && deltaY <= 5) || (deltaY == 0 && deltaX <= 5)) {

            if (deltaX > 0) {
                return lineCheckForPieces(oriX, oriY, destX, tabuleiro);
            } else if (deltaY > 0) {
                return columnCheckForPieces(oriX, oriY, destY, tabuleiro);
            }

        } else if (deltaX == deltaY && deltaX <= 5) {

            return diagonalCheckForPieces(oriX, oriY, destX, destY, tabuleiro);
        }

        return false;
    }
}
