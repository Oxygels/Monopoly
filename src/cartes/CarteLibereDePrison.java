package cartes;

import cases.Case;
import exception.MonopolyException;
import joueur.Joueur;
import plateau.Plateau;

import java.util.ArrayList;

public class CarteLibereDePrison extends Carte {

    // Important car le joueur devra à un moment la remettre dans la bonne pile
    private CategorieCarte categorieCarte;

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

        J.setPosition(Plateau.getPlateau().getCase("Simple Visite").getId());
    }
}
