package pt.ulusofona.lp2.deisichess;

public class Rei extends Piece {

    public Rei(int id, int type, int team, String nickname) {
        super(id, type, team, nickname);
        this.pointsWorth = 1000;
        this.pngLocation = "src/images/rei.png";
    }
}
