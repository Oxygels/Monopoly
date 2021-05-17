package cartes;

import joueur.Joueur;

public abstract class Carte {

    private final String enonce;

    public Carte(String enonce) {
        if ((enonce == null) || (enonce.trim().isEmpty()))
            throw new IllegalArgumentException("L'enonce ne peut etre vide ou valoir null.");
        else {
            this.enonce = enonce;
        }
    }

    public String getEnonce() {
        return enonce;
    }

    public abstract void actionCarte(Joueur J);

}