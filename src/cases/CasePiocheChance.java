package cases;

import cartes.CategorieCarte;
import exception.MonopolyException;
import joueur.Joueur;
import plateau.Plateau;

public class CasePiocheChance extends CasePioche {

    public CasePiocheChance(int id, String nom) {
        super(id, nom);
    }

    @Override
    public void action(Joueur joueur) throws MonopolyException {
        Plateau.getPlateau().piocherCarte(CategorieCarte.Chance, joueur);
    }

}