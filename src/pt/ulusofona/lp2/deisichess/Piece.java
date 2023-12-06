package pt.ulusofona.lp2.deisichess;

import java.io.File;

public class Piece {

    private int id;
    private int team;
    private int type;
    private int posX;
    private int posY;
    private String nickname;
    private File icon;
    private boolean inPlay;

    public Piece(int id, int type, int team, String nickname) {
        this.id = id;
        this.team = team;
        this.type = type;
        this.nickname = nickname;
        this.inPlay = false;
    }

    public Piece() {
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
}
