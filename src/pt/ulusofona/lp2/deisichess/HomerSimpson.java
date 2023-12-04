package pt.ulusofona.lp2.deisichess;

public class HomerSimpson extends Piece {
    boolean sleep;

    public HomerSimpson(int id, int type, int team, String nickname) {
        super(id, type, team, nickname);
        this.pointsWorth = 2;
        if (team == 10) {
            this.pngLocation = "homer_black.png";
        } else if (team == 20) {
            this.pngLocation = "homer_white.png";
        }
        this.inPlay = false;
        this.typeStr = "Homer Simpson";
        this.sleep = true;
    }

    public boolean specificMoveValidation(int oriX, int oriY, int destX, int destY, Piece[][] tabuleiro) {

        if (isSleep()) {
            System.out.println("Doh! zzzzzz");
            return false;
        }

        int deltaX = Math.abs(destX - oriX);
        int deltaY = Math.abs(destY - oriY);

        if (deltaX == 1 && deltaY == 1) {
            return diagonalCheckForPieces(oriX, oriY, destX, destY, tabuleiro);
        }

        return false;
    }

    public boolean isSleep() {
        return sleep;
    }

    @Override
    public void goSleep() {
        this.sleep = true;
    }

    @Override
    public void awake() {
        this.sleep = false;
    }
}
