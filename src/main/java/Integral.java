public class Integral {


    public static double f(double x) {
        return (x * x * 5 + 3 * x + 6);
    }

    public static double f(double x, double y) {

        return (5 * x*x * y*y + 3 * x * y + 6);
    }


    public static double gaussTwoPoints2D(double p1, double p2) {
        double mid = (Math.abs(p1) + Math.abs(p2)) / 2;
        double pp = p1 + p2;
        double w1 = 1.0;
        double w2 = 1.0;

        return (w1 * f(pp + mid / Math.sqrt(3)) + w2 * f(pp - mid / Math.sqrt(3)));
    }

    public static double gaussThreePoints2D(double p1, double p2) {
        double mid = (Math.abs(p1) + Math.abs(p2)) / 2;
        double pp = p1 + p2;
        double w1 = 5.0 / 9.0;
        double w2 = 8.0 / 9.0;

        double f1 = f(pp - mid * Math.sqrt(3.0 / 5.0));
        double f2 = f(pp);
        double f3 = f(pp + mid * Math.sqrt(3.0 / 5.0));
        return (w1 * f1 + w2 * f2 + w1 * f3);
    }

    public static double gaussFourPoints3D(double p1, double p2) {
        double w1 = 1.0;

        double f1 = f(-1.0 / Math.sqrt(3), -1.0 / Math.sqrt(3));
        double f2 = f(1.0 / Math.sqrt(3), -1.0 / Math.sqrt(3));
        double f3 = f(1.0 / Math.sqrt(3), 1.0 / Math.sqrt(3));
        double f4 = f(-1.0 / Math.sqrt(3), 1.0 / Math.sqrt(3));

        return (w1 * f1 + w1 * f2 + w1 * f3 + w1 * f4);
    }

    public static double gaussNinePoints3D(double p1, double p2) {
        double w1 = 5.0 / 9.0;
        double w2 = 8.0 / 9.0;

        double sqrt = Math.sqrt(3.0 / 5.0);

        double f1 = f(-sqrt, -sqrt) * w1 * w1;
        double f2 = f(0, -sqrt) * w1 * w2;
        double f3 = f(sqrt, -sqrt) * w1 * w1;
        double f4 = f(-sqrt, 0) * w2 * w1;
        double f5 = f(0.0, 0.0) * w2 * w2;
        double f6 = f(sqrt, 0) * w2 * w1;
        double f7 = f(-sqrt, sqrt) * w1 * w1;
        double f8 = f(0, sqrt) * w1 * w2;
        double f9 = f(sqrt, sqrt) * w1 * w1;

        return (f1+f2+f3+f4+f5+f6+f7+f8+f9);
    }

    public static void main(String[] args) {
        double x = gaussTwoPoints2D(-1, 1);
        System.out.println(x);
        System.out.println(gaussThreePoints2D(-1, 1));
        System.out.println(gaussFourPoints3D(1, 1));
        System.out.println(gaussNinePoints3D(1, 1));
        System.out.println("end");
    }

}
