package pt.ulusofona.lp2.deisichess;

public class HomerSimpson extends Piece {
    private boolean sleep;

    public HomerSimpson(int id, int type, int team, String nickname) {
        super(id, type, team, nickname);
        setPointsWorth(2);
        if (team == 10) {
            setPngLocation("homer_black.png");
        } else if (team == 20) {
            setPngLocation("homer_white.png");
        }
        setTypeStr("Doh! zzzzzz");
        this.sleep = true;
    }

    public HomerSimpson() {
        this.sleep = false;
    }

    @Override
    public boolean specificMoveValidation(int oriX, int oriY, int destX, int destY, Piece[][] tabuleiro) {

        if (!sleep) {

            int deltaX = Math.abs(destX - oriX);
            int deltaY = Math.abs(destY - oriY);

            if (deltaX == 1 && deltaY == 1) {
                return diagonalCheckForPieces(oriX, oriY, destX, destY, tabuleiro);
            }

        }
        return false;
    }

    @Override
    public Boolean isSleeping() {
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
