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
        int nombrebCases = Plateau.getPlateau().getNbCases();
        int position = j.getPosition();

        if(position + deplacement > nombrebCases)
            setCaseDepart(true);


        position = (position + deplacement) % nombrebCases;
        //j.seDeplacer(position);

        return Plateau.getPlateau().getCases().get(position);

    }

}