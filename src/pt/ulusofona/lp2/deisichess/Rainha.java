package pt.ulusofona.lp2.deisichess;

public class Rainha extends Piece {

    public Rainha(int id, int team, String nickname, Board board) {
        super(id,10, team, nickname); //aqui n sei o que e suposto por no type
        
    }

    @Override
    public boolean move(int oriX, int oriY, int destX, int destY, Board board) {

        if (!board.coordenadasDentroTabuleiro(oriX, oriY) || !board.coordenadasDentroTabuleiro(destX, destY)) {
            board.falhou();
            return false;
        }


        if (board.temPeca(oriX, oriY) && (destX != oriX || destY != oriY)) {
            Piece pecaMovida = board.getPecaNaPos(oriX, oriY);


            if (pecaMovida.isInPlay() && pecaMovida.getTeam() == board.isCurrentTeamNumb() &&
                    (board.validaMove(oriX, oriY, destX, destY))) {


                if (board.temPeca(destX, destY)) {
                    Piece pecaNoDestino = board.getPecaNaPos(destX, destY);


                    if (pecaNoDestino.getTeam() != board.isCurrentTeamNumb()) {

                        if (Math.abs(destX - oriX) <= 5 && Math.abs(destY - oriY) <= 5 &&
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

                    if (Math.abs(destX - oriX) <= 5 && Math.abs(destY - oriY) <= 5 &&
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

        board.falhou();
        return false;
    }
}
