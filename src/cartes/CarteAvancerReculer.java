package cartes;

import cases.Case;
import cases.CategorieCarte;

public class CarteAvancerReculer extends CarteDeplacement {

    private int deplacement;

    public CarteAvancerReculer(String enonce, boolean caseDepart, int deplacement, CategorieCarte categorie) {
        super(enonce, caseDepart, categorie);
        setDeplacement(deplacement);
    }

    public int getDeplacement() {
        return this.deplacement;
    }

    public void setDeplacement(int deplacement) {
        this.deplacement = deplacement;
    }

    @Override
    public Case determinerCase() {
        return null;
    }

}