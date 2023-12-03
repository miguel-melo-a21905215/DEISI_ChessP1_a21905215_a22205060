package pt.ulusofona.lp2.deisichess;

import java.io.File;

public abstract class Piece {

    int id;
    int team;
    int type;
    int posX;
    int posY;
    String nickname;
    File icon;
    boolean inPlay = false;
    int pointsWorth;
    String pngLocation;
    String typeStr;

    protected Piece(int id, int type, int team, String nickname) {
        this.id = id;
        this.team = team;
        this.type = type;
        this.nickname = nickname;
        this.inPlay = false;
    }

    protected Piece() {
    }

    public int getPointsWorth() {
        return pointsWorth;
    }

    public String getPngLocation() {
        return pngLocation;
    }

    public String getTypeStr() {
        return typeStr;
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
            if (tabuleiro[oriY][x] != null) {
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
            if (tabuleiro[y][oriX] != null) {
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
}
