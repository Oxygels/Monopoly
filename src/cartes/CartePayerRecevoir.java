package cartes;

public abstract class CartePayerRecevoir extends Carte {

    /**
     * Classe abstraite heritante de la classe Carte qui permet de definir les types de cartes
     * qui font payer de l'argent au joueur ou de lui en faire recevoir.
     * @param enonce Chaine de caractere qui represente le texte au dos de la carte.
     */
    public CartePayerRecevoir(String enonce) {
        super(enonce);
    }

    /**
     * Une methode abstraite qui permet de calculer le montant que le joueur reçois ou dois payer.
     * @return un entier (le montant) ou ce dernier va etre retiré ou ajouté a l'argent que le joueur actuel possede.
     */
    public abstract int getMontant();

}