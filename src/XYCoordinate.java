public class XYCoordinate {

    private int y;
    private int x;
    public XYCoordinate(int y,int x) {
        this.y = y;
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    @Override
    public String toString() {
        return "y=" + y + ", x=" + x +" |";
    }
}
