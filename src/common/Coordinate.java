package common;

public class Coordinate {
    private int x;
    private int y;
    private int x1;
    private int y2;

    public Coordinate(int x, int y, int x1, int y2) {
        this.x = x;
        this.y = y;
        this.x1 = x1;
        this.y2 = y2;
    }

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }

    public int getX1() {
        return x1;
    }

    public void setX1(int x1) {
        this.x1 = x1;
    }

    public int getY2() {
        return y2;
    }

    public void setY2(int y2) {
        this.y2 = y2;
    }
}
