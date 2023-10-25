package pt.ulusofona.lp2.deisichess;

import java.io.File;

public class Piece {

    int id;
    int team;
    int type;
    int posX;
    int posY;
    String nickname;
    File icon;
    boolean inPlay;

    public Piece(int id, int team, int type, String nickname) {
        this.id = id;
        this.team = team;
        this.type = type;
        this.nickname = nickname;
        this.inPlay = true;
    }

    public Piece() {
    }

    public File getIcon() {
        return icon;
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

    public int getId() {
        return id;
    }

    public int getPosX() {
        return posX;
    }

    public int getPosY() {
        return posY;
    }

    void capturada() {
        this.inPlay = false;
    }

    void novaPos(int posX, int posY) {
        this.posX = posX;
        this.posY = posY;
    }
}
