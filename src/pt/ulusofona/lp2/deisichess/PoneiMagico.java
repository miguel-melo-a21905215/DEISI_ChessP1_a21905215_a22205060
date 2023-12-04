package pt.ulusofona.lp2.deisichess;

public class PoneiMagico extends Piece {
    public PoneiMagico(int id, int type, int team, String nickname) {
        super(id, type, team, nickname);
        this.pointsWorth = 5;
        if (team == 10) {
            this.pngLocation = "ponei_magico_black.png";
        } else if (team == 20) {
            this.pngLocation = "ponei_magico_white.png";
        }
        this.inPlay = false;
        this.typeStr = "Ponei Mágico";
    }

    public PoneiMagico() {

    }

    @Override
    public boolean specificMoveValidation(int oriX, int oriY, int destX, int destY, Piece[][] tabuleiro) {

        int deltaX = Math.abs(destX - oriX);
        int deltaY = Math.abs(destY - oriY);


        if ((deltaX == 2 && deltaY == 2) && ((oriX + destX) % 2 == 0) && ((oriY + destY) % 2 == 0)) {
            return true;
        }

        return false;
    }
}
