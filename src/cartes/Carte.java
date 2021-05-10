package cartes;

import cases.CategorieCarte;
import joueur.Joueur;

public abstract class Carte {

    private String enonce;
    private CategorieCarte categorie;

    public Carte(String enonce, CategorieCarte categorie) {
        if ((enonce == null) || (enonce.trim().isEmpty()))
            throw new IllegalArgumentException("L'enonce ne peut etre vide ou valoir null.");
        else {
            this.enonce = enonce;
            this.categorie = categorie;
        }
    }

    public String getEnonce() {
        return enonce;
    }

    public CategorieCarte getCategorie() {
        return categorie;
    }

    public abstract void actionCarte(Joueur J);

}