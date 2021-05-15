package cartes;

import cases.CategorieCarte;
import joueur.Joueur;

public class CarteLibereDePrison extends Carte {

    public CarteLibereDePrison(String enonce, CategorieCarte categorie) {
        super(enonce, categorie);
    }

    @Override
    public void actionCarte(Joueur J) {

    }
}
