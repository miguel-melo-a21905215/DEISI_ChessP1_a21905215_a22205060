package pt.ulusofona.lp2.deisichess;

public class HomerSimpson extends Piece {
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
    }
}
