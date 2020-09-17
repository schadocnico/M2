package modele;

import java.util.ArrayList;
import java.util.Collection;

public class Facade {
    private Collection<Membre> membres;
    private Collection<Projet> projets;
    private Collection<Competence> competences;

    public Facade() {
        this.membres = new ArrayList<>();
        this.projets = new ArrayList<>();
        this.competences = new ArrayList<>();
    }

    public void addMembre(String login, String motdepasse, String surnom){
        membres.add(new Membre(login, motdepasse, surnom));
    }

    public Membre findMembreByLogin(String login){
        return membres.stream().filter(membre -> membre.getLogin().equals(login)).findFirst()
                .orElse(null);
    }

    public void addProjet(String intituleP, String descriptionP){
        projets.add(new Projet(intituleP, descriptionP));
    }

    public Projet findProjetByIntituleP(String intituleP){
        return projets.stream().filter(projet -> projet.getIntituleP().equals(intituleP)).findFirst()
                .orElse(null);
    }

    public void addCompetence(String intituleC, String descriptionC){
        competences.add(new Competence(intituleC, descriptionC));
    }

    public Competence findCompetence(String intituleC){
        return competences.stream().filter(competence -> competence.getIntituleC().equals(intituleC)).findFirst()
                .orElse(null);
    }
}
