package pt.ulusofona.lp2.deisichess;

class Play implements Comparable<Play> {
    private int destX;
    private int destY;
    private int pointsWorthMove;

    public Play(int destX, int destY, int pointsWorthMove) {
        this.destX = destX;
        this.destY = destY;
        this.pointsWorthMove = pointsWorthMove;
    }

    public int getDestX() {
        return destX;
    }

    public int getDestY() {
        return destY;
    }

    public int getPointsWorthMove() {
        return pointsWorthMove;
    }

    @Override
    public int compareTo(Play other) {
        return Integer.compare(other.pointsWorthMove, this.pointsWorthMove);
    }

    @Override
    public String toString() {
        return "(" + destX + "," + destY + ") -> " + pointsWorthMove;
    }
}


