package modele;

public interface ServiceMembre {

    public void addMembre(String login, String motdepasse, String surnom);

    public Membre findMembreByLogin(String login);
}
