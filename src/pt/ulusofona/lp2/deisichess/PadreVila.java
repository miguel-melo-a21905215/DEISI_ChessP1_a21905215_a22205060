package pt.ulusofona.lp2.deisichess;

public class PadreVila extends Piece {
    public PadreVila(int id, int type, int team, String nickname) {
        super(id, type, team, nickname);
        setPointsWorth(3);
        if (team == 10) {
            setPngLocation("padre_vila_black.png");
        } else if (team == 20) {
            setPngLocation("padre_vila_white.png");
        }
        setTypeStr("Padre da Vila");
    }

    public PadreVila() {

    }

    @Override
    public boolean specificMoveValidation(int oriX, int oriY, int destX, int destY, Piece[][] tabuleiro) {

        int deltaX = Math.abs(destX - oriX);
        int deltaY = Math.abs(destY - oriY);

        if (deltaX == deltaY && deltaX <= 3) {
            return diagonalCheckForPieces(oriX, oriY, destX, destY, tabuleiro);
        }

        return false;
    }
}
