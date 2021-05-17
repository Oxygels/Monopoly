package cases;

import cartes.CategorieCarte;
import joueur.Joueur;
import plateau.Plateau;

public class CasePiocheChance extends CasePioche {

    public CasePiocheChance(int id, String nom) {
        super(id, nom);
    }

    @Override
    public void action(Joueur joueur) {
        Plateau.getPlateau().piocherCarte(CategorieCarte.Chance);
    }

}