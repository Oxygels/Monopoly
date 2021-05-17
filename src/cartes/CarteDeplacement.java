package cartes;

import cases.Case;
import joueur.Joueur;

public abstract class CarteDeplacement extends Carte {

    private boolean caseDepart;

    public CarteDeplacement(String enonce, boolean caseDepart) {
        super(enonce);
        setCaseDepart(caseDepart);
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
        // TODO: Verifier caseDepart
        J.seDeplacer(getDestination().getId());
    }

}