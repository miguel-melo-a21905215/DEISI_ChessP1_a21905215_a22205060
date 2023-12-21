package pt.ulusofona.lp2.deisichess;

class Play implements Comparable<Play> {

    private boolean valid;
    private int oriX;
    private int oriY;
    private int destX;
    private int destY;
    private int pointsWorthMove;

    public Play(int destX, int destY, int pointsWorthMove) {
        this.oriX = -1;
        this.oriY = -1;
        this.destX = destX;
        this.destY = destY;
        this.pointsWorthMove = pointsWorthMove;
    }

    public Play(int oriX, int oriY, int destX, int destY, boolean valid) {
        this.oriX = oriX;
        this.oriY = oriY;
        this.destX = destX;
        this.destY = destY;
        this.valid = valid;
    }


    public boolean isValid() {
        return valid;
    }

    @Override
    public int compareTo(Play other) {
        return Integer.compare(other.pointsWorthMove, this.pointsWorthMove);
    }

    @Override
    public String toString() {
        if (oriX == -1 && oriY == -1) {
            return "(" + destX + "," + destY + ") -> " + pointsWorthMove;
        } else {
            return "\t\t:" + oriX + "\t\t:" + oriY + "\t\t:" + destX + "\t\t:" + destY + "\t\t:" + valid;
        }
    }
}


