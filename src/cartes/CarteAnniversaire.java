package cartes;

import exception.ArgentException;
import joueur.Joueur;
import plateau.Plateau;

import java.util.ArrayList;

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
    public void actionCarte(Joueur J) throws ArgentException {
        ArrayList<Joueur> players = Plateau.getPlateau().getJoueurs();

        for(Joueur j : players){
            if(!j.equals(J))
                j.payer(montantParJoueur, J);
        }
    }

}