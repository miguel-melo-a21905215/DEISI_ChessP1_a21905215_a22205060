package pt.ulusofona.lp2.deisichess;

public class TorreVertical extends Piece {
    public TorreVertical(int id, int type, int team, String nickname) {
        super(id, type, team, nickname);
        this.pointsWorth = 3;
        this.pngLocation = "src/images/torreVertical.png";
        this.inPlay = false;
        this.typeStr = "Torre Vertical";
    }
}
