package pt.ulusofona.lp2.deisichess;

public class PoneiMagico extends Piece {
    public PoneiMagico(int id, int type, int team, String nickname) {
        super(id, type, team, nickname);
        this.pointsWorth = 5;
        this.pngLocation = "src/images/ponei.png";
    }
}
