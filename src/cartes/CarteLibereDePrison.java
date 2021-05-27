package cartes;

import cases.Case;
import exception.MonopolyException;
import joueur.Joueur;
import plateau.Plateau;

import java.util.ArrayList;

public class CarteLibereDePrison extends Carte {

    // Important car le joueur devra à un moment la remettre dans la bonne pile
    private CategorieCarte categorieCarte;

    /**
     * Cette classe represente la carte Liberer de prison.
     * Elle permet au joueur actuel de se liberer de prison si ce dernier est en prison.
     * Le joueur va systematiquement etre deplacé vers la case "simple visite" si il sors de prison.
     * @param enonce Chaine de caractere qui represente le texte au dos de la carte.
     * @param categorie Important car le joueur devra à un moment la remettre dans la bonne pile.
     * @see CategorieCarte pour comprendre a quoi cela correspond dans monopoly.
     */
    public CarteLibereDePrison(String enonce, CategorieCarte categorie) {
        super(enonce);
    }

    public CategorieCarte getCategorieCarte() {
        return categorieCarte;
    }

    @Override
    public void actionCarte(Joueur J) throws MonopolyException {
        ArrayList<Case> cases = Plateau.getPlateau().getCases();

        if (!cases.get(J.getPosition()).getNom().equals("Prison"))
            throw new MonopolyException("Le joueur n'est pas en prison , il ne peut pas utiliser cette carte");

        J.seDeplacer(Plateau.getPlateau().getCase("Simple Visite").getId());
    }
}
