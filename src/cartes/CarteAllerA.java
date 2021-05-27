package cartes;

import cases.Case;

public class CarteAllerA extends CarteDeplacement {

    private Case destination;

    /**
     * Cette classe est une representation de la carte AllerA qui fait deplacer le joueur a une certaine case
     * dans la partie.
     * @param enonce Chaine de caractere qui represente le texte au dos de la carte.
     * @param caseDepart un booleen qui permet de savoir si le joueur est dans la case depart ou pas.
     * @param destination est l'endroit ou le joueur est censé atterir apres que la carte a eu
     *                    effet sur lui , la destination est representé par la Case monopoly.
     * @see Case pour comprendre comment est implementé une Case monopoly.
     */
    public CarteAllerA(String enonce, boolean caseDepart, Case destination) {
        super(enonce, caseDepart);
        setDestination(destination);
    }

    @Override
    public Case getDestination() {
        return destination;
    }

    public void setDestination(Case destination) {
        if (destination == null)
            throw new IllegalArgumentException("destination ne peut etre un objet null.");
        else
            this.destination = destination;
    }
}