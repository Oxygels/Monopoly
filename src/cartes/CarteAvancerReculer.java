package cartes;

import cases.Case;

public class CarteAvancerReculer extends CarteDeplacement {

    private int deplacement;

    public CarteAvancerReculer(String enonce, boolean caseDepart, int deplacement) {
        super(enonce, caseDepart);
        setDeplacement(deplacement);
    }

    public int getDeplacement() {
        return this.deplacement;
    }

    public void setDeplacement(int deplacement) {
        this.deplacement = deplacement;
    }

    @Override
    public Case getDestination() {
        //TODO
        return null;
    }

}