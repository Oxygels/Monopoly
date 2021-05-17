package cartes;

import joueur.Joueur;

public class CarteLibereDePrison extends Carte {

    // Important car le joueur devra à un moment la remettre dans la bonne pile
    private CategorieCarte categorieCarte;

    public CarteLibereDePrison(String enonce, CategorieCarte categorie) {
        super(enonce);
    }

    public CategorieCarte getCategorieCarte() {
        return categorieCarte;
    }

    @Override
    public void actionCarte(Joueur J) {

    }
}
