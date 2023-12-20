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


        if ((deltaX == 2 && deltaY == 2)) {
            int yIncrement = (destY > oriY) ? 1 : -1;
            int xIncrement = (destX > oriX) ? 1 : -1;

            int xCount = oriX + xIncrement;
            int yCount = oriY + yIncrement;

            if (tabuleiro[xCount][oriY] == null) {
                xCount += xIncrement;
                if (tabuleiro[xCount][oriY] == null) {
                    if (tabuleiro[xCount][yCount] == null) {
                        return true;
                    }
                }
            }
            if (tabuleiro[oriX][yCount] == null) {
                yCount += yIncrement;
                if (tabuleiro[oriX][yCount] == null) {
                    xCount = oriX + xIncrement;
                    if (tabuleiro[xCount][yCount] == null) {
                        return true;
                    }
                }
            }
            return false;
        }

        return false;
    }
}





