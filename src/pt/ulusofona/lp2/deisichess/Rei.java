package pt.ulusofona.lp2.deisichess;

public class Rei extends Piece {

    public Rei(int id, int type, int team, String nickname) {
        super(id, type, team, nickname);
        this.pointsWorth = 1000;
        if (team == 10) {
            this.pngLocation = "crazy_emoji_black.png";
        } else if (team == 20) {
            this.pngLocation = "crazy_emoji_white.png";
        }
        this.inPlay = false;
        this.typeStr = "Rei";
    }

    @Override
    public boolean specificMoveValidation(int oriX, int oriY, int destX, int destY, Piece[][] tabuleiro) {
        int deslocX = Math.abs(destX - oriX);
        int deslocY = Math.abs(destY - oriY);

        return (deslocX == 1 && deslocY == 0) || (deslocX == 0 && deslocY == 1) || (deslocX == 1 && deslocY == 1);
    }
}
