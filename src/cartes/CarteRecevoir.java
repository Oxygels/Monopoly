package cartes;

import joueur.Joueur;

public abstract class CarteRecevoir extends CartePayerRecevoir {

    public CarteRecevoir(String enonce) {
        super(enonce);
    }

    @Override
    public void actionCarte(Joueur J) {
        J.gagnerArgent(getMontant());
    }
}
