package modele;

import java.util.ArrayList;

public class CompetenceMembre {
    private int niveau;
    private String commentaire;
    private Membre dirigePar;
    private Competence compMembre;

    public CompetenceMembre(int niveau, String commentaire) {
        this.niveau = niveau;
        this.commentaire = commentaire;
    }

    public int getNiveau() {
        return niveau;
    }

    public void setNiveau(int niveau) {
        this.niveau = niveau;
    }

    public String getCommentaire() {
        return commentaire;
    }

    public void setCommentaire(String commentaire) {
        this.commentaire = commentaire;
    }

    public Membre getDirigePar() {
        return dirigePar;
    }

    public void setDirigePar(Membre dirigePar) {
        this.dirigePar = dirigePar;
    }

    public Competence getCompMembre() {
        return compMembre;
    }

    public void setCompMembre(Competence compMembre) {
        this.compMembre = compMembre;
    }
}
