package cartes;

import cases.Case;
import exception.MonopolyException;
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
    public void actionCarte(Joueur J) throws MonopolyException {
        J.seDeplacer(getDestination().getId());
        if (caseDepart && prendEnCompteCaseDepart && J.getPositionCase().getNom() != "Case Départ") {
            J.gagnerArgent(200);
        }
    }
}