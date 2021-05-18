package cartes;

import cases.Case;
import joueur.Joueur;

public abstract class CarteDeplacement extends Carte {

    private boolean caseDepart = false;
    private boolean prendEnCompteCaseDepart;

    public CarteDeplacement(String enonce, boolean prendEnCompteCaseDepart) {
        super(enonce);
        setPrendEnCompteCaseDepart(prendEnCompteCaseDepart);
    }

    public boolean isPrendEnCompteCaseDepart() {
        return prendEnCompteCaseDepart;
    }

    private void setPrendEnCompteCaseDepart(boolean prendEnCompteCaseDepart) {
        this.prendEnCompteCaseDepart = prendEnCompteCaseDepart;
    }

    public boolean isCaseDepart() {
        return caseDepart;
    }

    public void setCaseDepart(boolean caseDepart) {
        this.caseDepart = caseDepart;
    }

    public abstract Case getDestination();

    @Override
    public void actionCarte(Joueur J) {
        J.seDeplacer(getDestination().getId());
        if (caseDepart && prendEnCompteCaseDepart) {
            J.gagnerArgent(200);
        }
    }
}