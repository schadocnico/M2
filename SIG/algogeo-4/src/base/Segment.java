package base;

public class Segment {
	public Point debut;
	public Point fin;

	public Segment(Point debut, Point fin) {
		super();
		this.debut = debut;
		this.fin = fin;
	}

	@Override
	public String toString() {
		return "[" + debut + ", " + fin + "]";
	}

	public Vecteur toVecteur() {
		return new Vecteur(fin.x-debut.x,fin.y-debut.y);
	}
}
