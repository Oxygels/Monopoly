package cartes;

import cases.*;
import joueur.Joueur;

public abstract class CarteDeplacement extends Carte{

    private boolean caseDepart;

    public CarteDeplacement(String enonce , boolean caseDepart) {
        super(enonce);
        this.caseDepart=caseDepart;
    }

    public boolean isCaseDepart() {
        return caseDepart;
    }

    public void setCaseDepart(boolean caseDepart) {
        this.caseDepart = caseDepart;
    }

    public Case determinerCase() // a finir
    {
        return new CaseParkingGratuit();
    }

    @Override
    public void actionCarte(Joueur J) {

    }
}
