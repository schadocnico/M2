package algo;

import base.Point;
import base.Segment;
import base.Vecteur;

public class TD1 {

    public static Point projection(Point A, Point B, Point C){
        /**
         * Retourne un point de le projection du point C sur la droite AB
         */
        Segment segment_AB = new Segment(A, B);
        Segment segment_AC = new Segment(A, C);

        Vecteur AB = segment_AB.toVecteur();
        Vecteur AC = segment_AC.toVecteur();

        double calc_AD = TD1.produit_scalaire(AB, AC)/Math.pow(TD1.norme(AB), 2);
        Vecteur AD = new Vecteur(AB.x*calc_AD, AB.y*calc_AD);

        Point D = new Point(A.x+ AD.x, A.y+ AD.y);

        return D;
    }

    public static boolean estDansSegment(Point A, Point B, Point C){
        /**
         * test si le point C est sur le segment AB
         */

        Segment segment_AB = new Segment(A, B);
        Segment segment_AC = new Segment(A, C);

        Vecteur AB = segment_AB.toVecteur();
        Vecteur AC = segment_AC.toVecteur();

        return produit_vectoriel(AB, AC) == 0 && norme(AB) >= norme(AC);

    }

    public static double produit_vectoriel(Vecteur u, Vecteur v){
        return u.x*v.y-v.x*u.y;
    }

    public static double produit_scalaire(Vecteur u, Vecteur v){
        return u.x*v.x + u.y*v.y;
    }

    public static double norme(Vecteur u){
        return Math.sqrt(u.x*u.x + u.y*u.y);
    }
}
