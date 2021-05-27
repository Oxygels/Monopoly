package cartes;

import joueur.Joueur;

public abstract class CarteRecevoir extends CartePayerRecevoir {

    /**
     * Classe abstraite qui herite de la carte PayerRecevoir.
     * cette Classe permet de faire recevoir de l'argent au joueur actuel.
     * @param enonce Chaine de caractere qui represente le texte au dos de la carte.
     * @see CartePayerRecevoir pour comprendre d'ou provient la classe actuelle.
     */
    public CarteRecevoir(String enonce) {
        super(enonce);
    }

    @Override
    public void actionCarte(Joueur J) {
        J.gagnerArgent(getMontant());
    }
}
