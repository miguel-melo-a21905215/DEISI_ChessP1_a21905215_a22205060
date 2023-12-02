package pt.ulusofona.lp2.deisichess;

public class Joker extends Piece {
    public Joker(int id, int type, int team, String nickname) {
        super(id, type, team, nickname);
        this.pointsWorth = 4;
        if (team == 10) {
            this.pngLocation = "joker_black.png";
        } else if (team == 20) {
            this.pngLocation = "joker_white.png";
        }
        this.inPlay = false;
        this.typeStr = "Joker";
    }
}
