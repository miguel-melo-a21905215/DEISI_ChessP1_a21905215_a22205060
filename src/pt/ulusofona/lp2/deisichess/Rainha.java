package pt.ulusofona.lp2.deisichess;

import java.util.Objects;

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

        if (tabuleiro[destX][destY] != null && (Objects.equals(tabuleiro[destX][destY].getTypeStr(), "Rainha") || Objects.equals(tabuleiro[destX][destY].getCopyMoveFrom(), "Rainha"))) {
            return false;
        }

        int deltaX = Math.abs(destX - oriX);
        int deltaY = Math.abs(destY - oriY);


        if (deltaY == 0 && deltaX <= 5) {
            return lineCheckForPieces(oriX, oriY, destX, tabuleiro);
        } else if (deltaX == 0 && deltaY <= 5) {
            return columnCheckForPieces(oriX, oriY, destY, tabuleiro);
        } else if (deltaX == deltaY && deltaY <= 5) {
            return diagonalCheckForPieces(oriX, oriY, destX, destY, tabuleiro);
        }

        return false;
    }


}
