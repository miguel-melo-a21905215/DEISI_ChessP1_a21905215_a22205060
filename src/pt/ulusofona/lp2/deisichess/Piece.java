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

    public Piece(int id, int type, int team, String nickname) {
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
}
