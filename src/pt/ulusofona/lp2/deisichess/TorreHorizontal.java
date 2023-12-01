package pt.ulusofona.lp2.deisichess;

public class TorreHorizontal extends Piece {
    public TorreHorizontal(int id, int type, int team, String nickname) {
        super(id, type, team, nickname);
        this.pointsWorth = 3;
        this.pngLocation = "src/images/torreHorizontal.png";
        this.inPlay = false;
        this.typeStr = "Torre Horizontal";
    }

    @Override
    public boolean specificMoveValidation(int oriX, int oriY, int destX, int destY, Piece[][] tabuleiro) {
        int deltaX = Math.abs(destX - oriX);
        if (deltaX < 6 && deltaX > 0 && oriY == destY) {
            return lineCheckForPieces(oriX, oriY, destX, tabuleiro);
        }

        return false;
    }

}
