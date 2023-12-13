package pt.ulusofona.lp2.deisichess;

public class PoneiMagico extends Piece {
    public PoneiMagico(int id, int type, int team, String nickname) {
        super(id, type, team, nickname);
        setPointsWorth(5);
        if (team == 10) {
            setPngLocation("ponei_magico_black.png");
        } else if (team == 20) {
            setPngLocation("ponei_magico_white.png");
        }
        setTypeStr("Ponei Mágico");
    }

    public PoneiMagico() {

    }

    @Override
    public boolean specificMoveValidation(int oriX, int oriY, int destX, int destY, Piece[][] tabuleiro) {
        int deltaX = Math.abs(destX - oriX);
        int deltaY = Math.abs(destY - oriY);

        if (deltaX == 2 && deltaY == 2) {
            int yIncrement = (destY > oriY) ? 2 : -2;
            int xIncrement = (destX > oriX) ? 2 : -2;

            // Check line first
            if (lineCheckForPieces(oriX, oriY, destX, tabuleiro)) {
                oriX += xIncrement;
                // Then check column
                if (columnCheckForPieces(oriX, oriY, destY, tabuleiro)) {
                    return true;
                }
            }

            // Check column first
            if (columnCheckForPieces(oriX, oriY, destY, tabuleiro)) {
                oriY += yIncrement;
                // Then check line
                return lineCheckForPieces(oriX, oriY, destX, tabuleiro);
            }
        }

        return false;
    }


}
