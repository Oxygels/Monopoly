package cartes;

import cases.Case;
import joueur.Joueur;
import plateau.Plateau;

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
        Joueur j = Plateau.getPlateau().getJoueurCourant();
        int nombreCases = Plateau.getPlateau().getNbCases();
        int position = j.getPosition();

        setCaseDepart(position + deplacement >= nombreCases);

        // On ne prend pas en compte la case Prison
        position = (position + deplacement) % (nombreCases - 1);

        return Plateau.getPlateau().getCases().get(position);

    }

}