package pt.ulusofona.lp2.deisichess;

public class TorreHorizontal extends Piece {
    public TorreHorizontal(int id, int type, int team, String nickname) {
        super(id, type, team, nickname);
        this.pointsWorth = 3;
        this.pngLocation = "src/images/torreHorizontal.png";
        this.inPlay = false;
        this.typeStr = "Torre Horizontal";
    }
}
