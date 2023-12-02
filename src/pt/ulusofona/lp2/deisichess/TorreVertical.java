package pt.ulusofona.lp2.deisichess;

public class TorreVertical extends Piece {
    public TorreVertical(int id, int type, int team, String nickname) {
        super(id, type, team, nickname);
        this.pointsWorth = 3;
        if (team == 10) {
            this.pngLocation = "torre_v_black.png";
        } else if (team == 20) {
            this.pngLocation = "torre_v_white.png";
        }
        this.inPlay = false;
        this.typeStr = "Torre Vertical";
    }

    @Override
    public boolean specificMoveValidation(int oriX, int oriY, int destX, int destY, Piece[][] tabuleiro) {
        int deltaY = Math.abs(destY - oriY);

        if (deltaY < tabuleiro.length && deltaY > 0 && oriX == destX) {
            return columnCheckForPieces(oriX, oriY, destY, tabuleiro);
        }

        return false;
    }

}
