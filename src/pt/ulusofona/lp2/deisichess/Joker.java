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
        this.copyMoveFrom = "Rainha";
    }

    @Override
    public String getCopyMoveFrom() {
        return this.copyMoveFrom;
    }

    @Override
    public void setCopyMoveFrom(String copyMoveFrom) {


        switch (this.copyMoveFrom) {
            case "Homer Simpson" -> {
                this.copyMoveFrom = "Rainha";
            }

            case "TorreVert" -> {
                this.copyMoveFrom = "Homer Simpson";
            }

            case "TorreHor" -> {
                this.copyMoveFrom = "TorreVert";
            }

            case "Padre da Vila" -> {
                this.copyMoveFrom = "TorreHor";
            }
            case "Ponei Mágico" -> {
                this.copyMoveFrom = "Padre da Vila";
            }
            case "Rainha" -> this.copyMoveFrom = "Ponei Mágico";
        }
    }

    @Override
    public boolean specificMoveValidation(int oriX, int oriY, int destX, int destY, Piece[][] tabuleiro) {
        return switch (this.copyMoveFrom) {
            case "Homer Simpson" -> {
                HomerSimpson tempHomer = new HomerSimpson();
                yield tempHomer.specificMoveValidation(oriX, oriY, destX, destY, tabuleiro);
            }

            case "Torre Vertical" -> {
                TorreVertical tempTorreVert = new TorreVertical();
                yield tempTorreVert.specificMoveValidation(oriX, oriY, destX, destY, tabuleiro);
            }

            case "Torre Horizontal" -> {
                TorreHorizontal tempTorreHor = new TorreHorizontal();
                yield tempTorreHor.specificMoveValidation(oriX, oriY, destX, destY, tabuleiro);
            }

            case "Padre da Vila" -> {
                PadreVila tempPadreVila = new PadreVila();
                yield tempPadreVila.specificMoveValidation(oriX, oriY, destX, destY, tabuleiro);
            }

            case "Ponei Magico" -> {
                PoneiMagico tempPoneiMagico = new PoneiMagico();
                yield tempPoneiMagico.specificMoveValidation(oriX, oriY, destX, destY, tabuleiro);
            }

            case "Rainha" -> {
                Rainha tempRainha = new Rainha();
                yield tempRainha.specificMoveValidation(oriX, oriY, destX, destY, tabuleiro);
            }

            default -> false;

        };
    }


}
