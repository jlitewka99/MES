public class Node {
    /*
    wezly
     */

    double x;
    double y;
    short BC;

    public Node(double x, double y, short BC) {
        this.x = x;
        this.y = y;
        this.BC = BC;
    }

    public double getX() {
        return x;
    }

    public double getY() {
        return y;
    }

    public void setBC(short BC) {
        this.BC = BC;
    }

    public short getBC() {
        return BC;
    }

    @Override
    public String toString() {
        return "Node{" +
                "x=" + x +
                ", y=" + y +
                ", BC=" + BC +
                '}';
    }
}
