package pt.ulusofona.lp2.deisichess;

public class HomerSimpson extends Piece {
    public HomerSimpson(int id, int type, int team, String nickname) {
        super(id, type, team, nickname);
        this.pointsWorth = 2;
        this.pngLocation = "src/images/HomerSimpson.png";
        this.inPlay = false;
        this.typeStr = "Homer Simpson";
    }
}
