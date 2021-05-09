package cartes;

import joueur.Joueur;

public abstract class Carte {

    private String enonce;

    public Carte(String enonce) {
        if(enonce == null || enonce.trim().isEmpty())
            throw new IllegalArgumentException("l'enonce ne doit pas etre null ou vide");
        else
            this.enonce = enonce;
    }

    public String getEnonce() {
        return enonce;
    }

    public abstract void actionCarte(Joueur J);
}
