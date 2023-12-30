package pt.ulusofona.lp2.deisichess;

public class TorreHorizontal extends Piece {
    public TorreHorizontal(int id, int type, int team, String nickname) {
        super(id, type, team, nickname);
        this.pointsWorth = 3;
        if (team == 10) {
            setPngLocation("torre_h_black.png");
        } else if (team == 20) {
            setPngLocation("torre_h_white.png");
        }
        setTypeStr("TorreHor");
    }

    public TorreHorizontal() {

    }


    @Override
    public boolean specificMoveValidation(int oriX, int oriY, int destX, int destY, Piece[][] tabuleiro) {
        int deltaX = Math.abs(destX - oriX);
        if (deltaX < tabuleiro.length && deltaX > 0 && oriY == destY) {
            return lineCheckForPieces(oriX, oriY, destX, tabuleiro);
        }

        return false;
    }

}
