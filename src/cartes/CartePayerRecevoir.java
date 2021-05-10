package cartes;

import cases.CategorieCarte;
import joueur.Joueur;

public abstract class CartePayerRecevoir extends Carte {

    public CartePayerRecevoir(String enonce, CategorieCarte categorie) {
        super(enonce, categorie);
    }

    @Override
    public void actionCarte(Joueur J) {

    }

}