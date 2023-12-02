package pt.ulusofona.lp2.deisichess;

public class PoneiMagico extends Piece {
    public PoneiMagico(int id, int type, int team, String nickname) {
        super(id, type, team, nickname);
        this.pointsWorth = 5;
        if (team == 10) {
            this.pngLocation = "ponei_magico_black.png";
        } else if (team == 20) {
            this.pngLocation = "ponei_magico_white.png";
        }
        this.inPlay = false;
        this.typeStr = "Ponei Magico";
    }
}
