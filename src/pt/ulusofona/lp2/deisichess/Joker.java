package pt.ulusofona.lp2.deisichess;

public class Joker extends Piece {
    public Joker(int id, int type, int team, String nickname) {
        super(id, type, team, nickname);
        this.pointsWorth = 4;
        this.pngLocation = "src/images/Joker.png";
    }
}
