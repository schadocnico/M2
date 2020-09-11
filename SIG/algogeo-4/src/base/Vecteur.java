package base;

public class Vecteur{
    public double x;
    public double y;

    public Vecteur(double x, double y) {
        this.x = x;
        this.y = y;
    }

    @Override
    public String toString() {
        return "(" + x + "," + y + ")";
    }

}
