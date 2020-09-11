import algo.TD1;
import base.Vecteur;
import fenetre.Fenetre;
import fenetre.Panneau;
import base.Point;
import base.Segment;
import base.Polyedre;


public class Main {
	public static void main(String[] arg) {
		Point A = new Point (400,400);
		Point B = new Point (200,500);
		Point C = new Point (300,300);

		Point D = TD1.projection(A, B, C);

		Panneau panneau = new Panneau();
		panneau.add(new Segment(A,B));
		panneau.add(C);
		panneau.add(D);
		System.out.println(TD1.estDansSegment(A,B,D));


		Fenetre fenetre = new Fenetre(panneau);
		fenetre.setVisible(true);
	}
}
