package pt.ulusofona.lp2.deisichess;

public abstract class Piece {

    protected int numCapturas;
    protected int id;
    protected int team;
    protected int type;
    protected int posX;
    protected int posY;
    protected String nickname;
    protected boolean inPlay = false;
    protected String pngLocation;
    protected String typeStr;
    protected int pointsWorth = 0;
    protected int failedAttempts = 0;
    protected int successfulAttempts = 0;

    protected Piece(int id, int type, int team, String nickname) {
        this.id = id;
        this.team = team;
        this.type = type;
        this.nickname = nickname;
        this.inPlay = false;
        this.pointsWorth = 0;
        this.typeStr = "";
        this.numCapturas = 0;
        this.failedAttempts = 0;
        this.successfulAttempts = 0;
    }

    protected Piece() {
    }

    public int getSuccessfulAttempts() {
        return successfulAttempts;
    }

    public int getFailedAttempts() {
        return failedAttempts;
    }

    public void acertou() {
        this.successfulAttempts++;
    }

    public void falhou() {
        this.failedAttempts++;
    }

    public int getPointsWorth() {
        return pointsWorth;
    }

    public void setPointsWorth(int pointsWorth) {
        this.pointsWorth = pointsWorth;
    }

    public String getPngLocation() {
        return pngLocation;
    }

    public void setPngLocation(String pngLocation) {
        this.pngLocation = pngLocation;
    }

    public String getTypeStr() {
        return typeStr;
    }

    public void setTypeStr(String typeStr) {
        this.typeStr = typeStr;
    }


    public int getTeam() {
        return team;
    }


    public int getType() {
        return type;
    }


    public String getNickname() {
        return nickname;
    }


    public boolean isInPlay() {
        return inPlay;
    }


    public void marcaInPlay() {
        inPlay = true;
    }

    public int getId() {
        return id;
    }


    public int getPosX() {
        return posX;
    }


    public int getPosY() {
        return posY;
    }

    public void capturada() {
        this.inPlay = false;
    }

    public void novaPos(int posX, int posY) {
        this.posX = posX;
        this.posY = posY;
    }

    public boolean specificMoveValidation(int oriX, int oriY, int destX, int destY, Piece[][] tabuleiro) {
        return false;
    }

    public boolean lineCheckForPieces(int oriX, int oriY, int destX, Piece[][] tabuleiro) {
        int xIncrement = (destX > oriX) ? 1 : -1;

        int x = oriX + xIncrement;

        while (x != destX) {
            if (tabuleiro[x][oriY] != null) {
                return false;
            }
            x += xIncrement;
        }

        return true;
    }

    public boolean columnCheckForPieces(int oriX, int oriY, int destY, Piece[][] tabuleiro) {
        int yIncrement = (destY > oriY) ? 1 : -1;

        int y = oriY + yIncrement;

        while (y != destY) {
            if (tabuleiro[oriX][y] != null) {
                return false;
            }
            y += yIncrement;
        }

        return true;
    }


    public boolean diagonalCheckForPieces(int oriX, int oriY, int destX, int destY, Piece[][] tabuleiro) {
        int deltaX = destX - oriX;
        int deltaY = destY - oriY;

        int xIncrement = (deltaX > 0) ? 1 : -1;
        int yIncrement = (deltaY > 0) ? 1 : -1;

        int x = oriX + xIncrement;
        int y = oriY + yIncrement;

        while (x != destX && y != destY) {
            if (tabuleiro[x][y] != null) {
                return false;
            }
            x += xIncrement;
            y += yIncrement;
        }

        return true;
    }


    public void goSleep() {
    }

    public void awake() {
    }

    public String getCopyMoveFrom() {
        return null;
    }

    public void jokerClock() {

    }

    public Boolean isSleeping() {
        return true;
    }


    public int getNumCapturas() {
        return numCapturas;
    }

    public void capturou(int pontos) {
        this.numCapturas++;
        this.pointsWorth += pontos;
    }


}
