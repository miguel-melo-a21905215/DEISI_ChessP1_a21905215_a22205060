package pt.ulusofona.lp2.deisichess;

public class AveRara extends Piece {

    public AveRara(int team) {
        this.id = 100;
        this.team = team;
        this.type = 44;
        if (team == 10) {
            this.nickname = "Hell Hawk";
        } else if (team == 20) {
            this.nickname = "Gwaihir";
        }
        this.inPlay = true;
        this.typeStr = "Ave Rara";
        this.numCapturas = 0;
        this.accumulatedPoints = 0;
        this.failedAttempts = 0;
        this.successfulAttempts = 0;
        this.pointsWorth = 5;
        if (team == 10) {
            setPngLocation("aveRara_black.png");
        } else if (team == 20) {
            setPngLocation("aveRara_white.png");
        }
    }


    @Override
    public boolean specificMoveValidation(int oriX, int oriY, int destX, int destY, Piece[][] tabuleiro) {


        int deltaX = Math.abs(destX - oriX);
        int deltaY = Math.abs(destY - oriY);


        if (deltaY == 0 && deltaX > 0) {
            return lineCheckForPieces(oriX, oriY, destX, tabuleiro);
        } else if (deltaX == 0 && deltaY > 0) {
            return columnCheckForPieces(oriX, oriY, destY, tabuleiro);
        } else if (deltaX == deltaY && deltaY > 0) {
            return diagonalCheckForPieces(oriX, oriY, destX, destY, tabuleiro);
        }

        return false;
    }

    @Override
    public String checkAdjacent(Piece[][] tabuleiro, int y, int x, int team) {
        if (team == 10) {
            int[] directions = {-1, 0, 1, 0, -1};

            for (int i = 0; i < directions.length - 1; i++) {
                int newY = y + directions[i];
                int newX = x + directions[i + 1];

                if (newY >= 0 && newY < tabuleiro.length && newX >= 0 && newX < tabuleiro[newY].length && (checkPosition(tabuleiro, newY, newX, team))) {
                    return newX + "," + newY;

                }
            }

        } else if (team == 20) {
            int[] directions = {1, 0, -1, 0, 1};

            for (int i = 0; i < directions.length - 1; i++) {
                int newY = y + directions[i];
                int newX = x + directions[i + 1];

                if (newY >= 0 && newY < tabuleiro.length && newX >= 0 && newX < tabuleiro[newY].length && (checkPosition(tabuleiro, newY, newX, team))) {
                    return newX + "," + newY;

                }
            }
        }
        return null;
    }

    @Override
    public boolean checkPosition(Piece[][] tabuleiro, int y, int x, int team) {
        return (y >= 0 && y < tabuleiro.length && x >= 0 && x < tabuleiro[y].length &&
                (tabuleiro[y][x] == null || tabuleiro[y][x].getTeam() == team));
    }

}
