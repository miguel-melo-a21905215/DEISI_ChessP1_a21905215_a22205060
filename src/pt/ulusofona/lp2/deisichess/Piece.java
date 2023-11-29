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

    public boolean move(int oriX, int oriY, int destX, int destY, Board board){
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

    public Piece clonePiece(int id, int type, int team, String nickname, int posX, int posY) {
        Piece clonedPiece = newPieceByType(id, type, team, nickname);
        clonedPiece.novaPos(posX, posY);
        return clonedPiece;
    }


}
