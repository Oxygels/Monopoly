package cartes;

import cases.Case;
import cases.CategorieCarte;

public class CarteAllerA extends CarteDeplacement {

    private Case destination;

    public CarteAllerA(String enonce, boolean caseDepart, Case destination, CategorieCarte categorie) {
        super(enonce, caseDepart, categorie);
        setDestination(destination);
    }

    public Case getDestination() {
        return destination;
    }

    public void setDestination(Case destination) {
        if (destination == null)
            throw new IllegalArgumentException("destination ne peut etre un objet null.");
        else
            this.destination = destination;
    }

    @Override
    public Case determinerCase() {
        return null;
    }

}