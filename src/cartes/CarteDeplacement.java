package cartes;

import cases.Case;
import cases.CategorieCarte;
import joueur.Joueur;

public abstract class CarteDeplacement extends Carte {

    private boolean caseDepart;

    public CarteDeplacement(String enonce, boolean caseDepart, CategorieCarte categorie) {
        super(enonce, categorie);
        setCaseDepart(caseDepart);
    }

    public boolean isCaseDepart() {
        return caseDepart;
    }

    public void setCaseDepart(boolean caseDepart) {
        this.caseDepart = caseDepart;
    }

    public abstract Case determinerCase();

    @Override
    public void actionCarte(Joueur J) {

    }

}