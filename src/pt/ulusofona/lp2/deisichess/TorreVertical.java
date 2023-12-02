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
}
