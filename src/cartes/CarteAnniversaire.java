package cartes;

import exception.FailliteException;
import joueur.Joueur;
import plateau.Plateau;

import java.util.ArrayList;

public class CarteAnniversaire extends Carte {

    private int montantParJoueur;

    /**
     * Cette classe represente une carte anniversaire dans monopoly.
     * Elle consiste a ce que chaque joueur du plateau ( dans la partie ) paye un montant specifique.
     * Un montant totale qui repgrouperas la somme des montant reçu des joueurs serons versé chez le joueur
     * qui receveras cette carte.
     * @param enonce Chaine de caractere qui represente le texte au dos de la carte.
     * @param montantParJoueur represente une somme que chaque joueur de la parte paye , un total sera
     *                         veré au joueur qui receveras cette carte.
     * @see Joueur pour comprendre le mechanisme de payement d'un joueur.
     */
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
    public void actionCarte(Joueur J) throws FailliteException {
        ArrayList<Joueur> players = Plateau.getPlateau().getJoueurs();

        for (Joueur j : players) {
            if (!j.equals(J))
                j.payer(montantParJoueur, J);
        }
    }

}