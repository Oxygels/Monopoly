package cases;

import cartes.CategorieCarte;
import joueur.Joueur;
import plateau.Plateau;

public class CasePiocheCommunaute extends CasePioche {

    public CasePiocheCommunaute(int id, String nom) {
        super(id, nom);
    }

    @Override
    public void action(Joueur joueur) {
        Plateau.getPlateau().piocherCarte(CategorieCarte.Communaute);
    }

}