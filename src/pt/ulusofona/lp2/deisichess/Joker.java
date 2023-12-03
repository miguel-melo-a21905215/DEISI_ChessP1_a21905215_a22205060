package pt.ulusofona.lp2.deisichess;

public class Joker extends Piece {
    String copyMoveFrom;

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

    @Override
    public String getCopyMoveFrom() {
        return copyMoveFrom;
    }

    public void setLastTypeMoved(int turn) {
        if (turn % 6 == 0) {
            this.copyMoveFrom = "Homer Simpson";
        } else if (turn % 5 == 0) {
            this.copyMoveFrom = "Torre Vertical";
        } else if (turn % 4 == 0) {
            this.copyMoveFrom = "Torre Horizontal";
        } else if (turn % 3 == 0) {
            this.copyMoveFrom = "Padre da Vila";
        } else if (turn % 2 == 0) {
            this.copyMoveFrom = "Ponei Magico";
        } else {
            this.copyMoveFrom = "Rainha";
        }
    }

   /* @Override
    public boolean specificMoveValidation(int oriX, int oriY, int destX, int destY, Piece[][] tabuleiro) {
        return switch (this.copyMoveFrom) {
            case "Homer Simpson" -> HomerSimpson.specificMoveValidation(oriX, oriY, destX, destY, tabuleiro);

            case "Torre Vertical" -> TorreVertical.specificMoveValidation(oriX, oriY, destX, destY, tabuleiro);

            case "Torre Horizontal" -> TorreHorizontal.specificMoveValidation(oriX, oriY, destX, destY, tabuleiro);

            case "Padre da Vila" -> PadreVila.specificMoveValidation(oriX, oriY, destX, destY, tabuleiro);

            case "Ponei Magico" -> PoneiMagico.specificMoveValidation(oriX, oriY, destX, destY, tabuleiro);

            case "Rainha" -> Rainha.specificMoveValidation(oriX, oriY, destX, destY, tabuleiro);

            default -> false;

        };
    }*/
}
