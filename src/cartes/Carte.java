/**
 * Mother Class Carte which is abstract for all the cartes package.
 * It represents a monopoly cart in general .
 */

package cartes;

import exception.MonopolyException;
import joueur.Joueur;

public abstract class Carte {

    private final String enonce;

    /**
     * Constructeur de la classe abstraite Carte qui represente une carte monopoly.
     * @param enonce Une chaine de caractere qui represente ce qui est ecris dans le dos de la carte
     *               monopoly.
     *               la chaine de caractere ne dois pas etre null ou vide.
     */

    public Carte(String enonce) {
        if ((enonce == null) || (enonce.trim().isEmpty()))
            throw new IllegalArgumentException("L'enonce ne peut etre vide ou valoir null.");
        else {
            this.enonce = enonce;
        }
    }

    public String getEnonce() {
        return enonce;
    }

    /**
     * Methode abstraite qui represente ce que la carte fait dans le jeu.
     * On sait que certaine cartes font en sorte que le joueur fasse une action, et d'autres non.
     * Cette Methode abstraite sera implementé dans les classes derivées de Carte.
     * @param J qui represente un joueur de la classe Joueur car la methode modifieras le comportement du joueur.
     * @see Joueur pour comprendre ce que fait la classe et ce qu'elle contient.
     * @throws MonopolyException
     */
    public abstract void actionCarte(Joueur J) throws MonopolyException;

}