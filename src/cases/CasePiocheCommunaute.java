package cases;

import cartes.CategorieCarte;
import exception.MonopolyException;
import joueur.Joueur;
import plateau.Plateau;

public class CasePiocheCommunaute extends CasePioche {

    public CasePiocheCommunaute(int id, String nom) {
        super(id, nom);
    }

    @Override
    public void action(Joueur joueur) throws MonopolyException {
        Plateau.getPlateau().piocherCarte(CategorieCarte.Communaute, joueur);
    }

}