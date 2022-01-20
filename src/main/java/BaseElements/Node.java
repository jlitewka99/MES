package BaseElements;

public class Node {
    /*
    wezly
     */

    double x;
    double y;
    short BC;
    double temperature;

    public Node(double x, double y, short BC, double startTemperature) {
        this.x = x;
        this.y = y;
        this.BC = BC;
        this.temperature = startTemperature;
    }

    public double getX() {
        return x;
    }

    public double getY() {
        return y;
    }

    public short getBC() {
        return BC;
    }

    public double getTemperature() {
        return temperature;
    }

    public void setTemperature(double temperature) {
        this.temperature = temperature;
    }

    public void setBC(short BC) {
        this.BC = BC;
    }

    @Override
    public String toString() {
        return "Node{" +
                "x=" + x +
                ", y=" + y +
                ", BC=" + BC +
                "}\n";
    }
}
