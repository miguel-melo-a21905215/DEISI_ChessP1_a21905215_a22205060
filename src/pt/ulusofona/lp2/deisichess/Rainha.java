package pt.ulusofona.lp2.deisichess;

public class Rainha extends Piece {

    public Rainha(int id, int type, int team, String nickname) {
        super(id, type, team, nickname);
        this.pointsWorth = 8;
        if (team == 10) {
            this.pngLocation = "rainha_black.png";
        } else if (team == 20) {
            this.pngLocation = "rainha_white.png";
        }
        this.inPlay = false;
        this.typeStr = "Rainha";
    }

    @Override
    public boolean specificMoveValidation(int oriX, int oriY, int destX, int destY, Piece[][] tabuleiro) {

        /*NAO PRECISAMOS DE TER AQUI A VERIFICA COORDENADAS DENTRO DO TABULEIRO,
         * PASSEI PARA A CHAMADA DO MOVE NA GAMEMANAGER PORQUE É COMUM A TODAS AS
         * PEÇAS. ADICIONEI MAIS CONDIÇÕES QUE TAMBÉM SÃO COMUNS*/

       /*if (!board.coordenadasDentroTabuleiro(oriX, oriY) || !board.coordenadasDentroTabuleiro(destX, destY)) {
            board.falhou();
            return false;
        }


        if (board.temPeca(oriX, oriY) && (destX != oriX || destY != oriY)) {
            Piece pecaMovida = board.getPecaNaPos(oriX, oriY);


            if (pecaMovida.isInPlay() && pecaMovida.getTeam() == board.isCurrentTeamNumb() &&
                    (board.validaMoveGeral(oriX, oriY, destX, destY))) {


                if (board.temPeca(destX, destY)) {
                    Piece pecaNoDestino = board.getPecaNaPos(destX, destY);


                    if (pecaNoDestino.getTeam() != board.isCurrentTeamNumb()) {

                        if ((destX - oriX <= 5 || oriX - destX <= 5) &&
                                (destY - oriY <= 5 || oriY - destY <= 5) &&
                                !board.temPecaNoCaminho(oriX, oriY, destX, destY)) {

                            pecaNoDestino.capturada();
                            board.metePecaDestino(pecaMovida, destX, destY);
                            board.comeu();
                            board.tiraPecaOrigem(oriX, oriY);
                            return true;

                        } else {
                            board.falhou();
                            return false;
                        }
                    } else {
                        board.equipas[board.isCurrentTeamNumb()].invalida();
                        return false;
                    }
                } else {

                    if ((destX - oriX) <= 5 && (destX - oriX) >= -5 &&
                            (destY - oriY) <= 5 && (destY - oriY) >= -5 &&
                            !board.temPecaNoCaminho(oriX, oriY, destX, destY)) {

                        board.tiraPecaOrigem(oriX, oriY);
                        board.metePecaDestino(pecaMovida, destX, destY);
                        board.moveu();
                        return true;

                    } else {
                        board.falhou();
                        return false;
                    }
                }
            }
        }

        board.falhou();*/
        return false;
    }
}
