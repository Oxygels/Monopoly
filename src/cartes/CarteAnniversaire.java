package cartes;

import joueur.Joueur;

public class CarteAnniversaire extends Carte {

    private int montantParJoueur;

    public CarteAnniversaire(String enonce, int montantParJoueur) {
        super(enonce);
        setMontantParJoueur(montantParJoueur);
    }

    public int getMontantParJoueur() {
        return montantParJoueur;
    }

    public void setMontantParJoueur(int montantParJoueur) {
        if (montantParJoueur < 0)
            throw new IllegalArgumentException("montantParJoueur doit etre un entier positif.");
        else
            this.montantParJoueur = montantParJoueur;
    }

    @Override
    public void actionCarte(Joueur J) {

    }

}