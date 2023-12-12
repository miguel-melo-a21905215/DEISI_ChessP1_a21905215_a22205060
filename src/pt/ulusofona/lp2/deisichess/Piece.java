package pt.ulusofona.lp2.deisichess;

import java.io.File;

public abstract class Piece {


    protected int numCapturas;
    protected int id;
    protected int team;
    protected int type;
    protected int posX;
    protected int posY;
    protected String nickname;
    protected File icon;
    protected boolean inPlay = false;
    protected int pointsWorth;
    protected String pngLocation;
    protected String typeStr;

    protected Piece(int id, int type, int team, String nickname) {
        this.id = id;
        this.team = team;
        this.type = type;
        this.nickname = nickname;
        this.inPlay = false;
        this.pointsWorth = 0;
        this.typeStr = "";
    }

    protected Piece() {
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

    public void setIcon(File icon) {
        this.icon = icon;
    }

    public int getTeam() {
        return team;
    }

    public void setTeam(int team) {
        this.team = team;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public boolean isInPlay() {
        return inPlay;
    }

    public void setInPlay(boolean inPlay) {
        this.inPlay = inPlay;
    }

    public void marcaInPlay() {
        inPlay = true;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getPosX() {
        return posX;
    }

    public void setPosX(int posX) {
        this.posX = posX;
    }

    public int getPosY() {
        return posY;
    }

    public void setPosY(int posY) {
        this.posY = posY;
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
            if (tabuleiro[y][x] != null) {
                return false;
            }
            x += xIncrement;
            y += yIncrement;
        }

        return true;
    }


    public Piece newPieceByType(int id, int type, int team, String nickname) {
        return switch (type) {
            case 0 -> new Rei(id, type, team, nickname);
            case 1 -> new Rainha(id, type, team, nickname);
            case 2 -> new PoneiMagico(id, type, team, nickname);
            case 3 -> new PadreVila(id, type, team, nickname);
            case 4 -> new TorreHorizontal(id, type, team, nickname);
            case 5 -> new TorreVertical(id, type, team, nickname);
            case 6 -> new HomerSimpson(id, type, team, nickname);
            case 7 -> new Joker(id, type, team, nickname);
            default -> null;
        };
    }


    public Piece clonePiece() {
        Piece clonedPiece = newPieceByType(this.id, this.type, this.team, this.nickname);
        clonedPiece.marcaInPlay();
        clonedPiece.novaPos(posX, posY);
        return clonedPiece;
    }

    public void goSleep() {
    }

    public void awake() {
    }

    public String getCopyMoveFrom() {
        return null;
    }

    public void setCopyMoveFrom(String copyMoveFrom) {

    }

    public int getNumCapturas() {
        return numCapturas;
    }

    public void setNumCapturas(int numCapturas) {
        this.numCapturas = numCapturas;
    }

    public Boolean isSleeping() {
        return true;
    }
}
