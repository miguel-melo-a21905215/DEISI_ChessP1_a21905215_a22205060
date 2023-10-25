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

    void capturada() {
        this.inPlay = false;
    }
}
