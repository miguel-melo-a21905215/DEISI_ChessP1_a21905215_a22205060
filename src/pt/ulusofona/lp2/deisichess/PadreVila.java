package pt.ulusofona.lp2.deisichess;

public class PadreVila extends Piece {
    public PadreVila(int id, int type, int team, String nickname) {
        super(id, type, team, nickname);
        this.pointsWorth = 3;
        if (team == 10) {
            this.pngLocation = "padre_vila_black.png";
        } else if (team == 20) {
            this.pngLocation = "padre_vila_white.png";
        }
        this.inPlay = false;
        this.typeStr = "Padre da Vila";
    }
}
