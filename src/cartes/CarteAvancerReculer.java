package cartes;

import cases.Case;
import joueur.Joueur;
import plateau.Plateau;

public class CarteAvancerReculer extends CarteDeplacement {

    private int deplacement;

    /**
     * Permet de faire avancer un joueur dans le plateau ou de le faire reculer de certaines cases.
     * @param enonce Chaine de caractere qui represente le texte au dos de la carte.
     * @param caseDepart un booleen qui permet de savoir si le joueur est dans la case depart ou pas.
     * @param deplacement entier relatif qui permet de faire deplacer le joueur d'un certain nombre de cases.
     */
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

    /**
     * Permet de savoir a quelle desitation le joueur va atterir quand on applique cette methode sur lui.
     * on ne prends pas en compte la case prison.
     * @return la case dans laquelle le joueur va atterir , elle est calculé par rapport a la position du joueur
     * additionné au deplacement modulo le nombre de cases - 1.
     */
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