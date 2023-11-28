package pt.ulusofona.lp2.deisichess;

public class PadreVila extends Piece {
    public PadreVila(int id, int type, int team, String nickname) {
        super(id, type, team, nickname);
        this.pointsWorth = 3;
        this.pngLocation = "src/images/padreVila.png";
        this.inPlay = false;
    }
}
