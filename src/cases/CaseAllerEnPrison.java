package cases;

import exception.MonopolyException;
import joueur.Joueur;
import plateau.Plateau;

public class CaseAllerEnPrison extends Case {

    public CaseAllerEnPrison(int id, String nom) {
        super(id, nom);
    }

    @Override
    public void action(Joueur joueur) throws MonopolyException {
        Plateau plateau = Plateau.getPlateau();

        Case prison = plateau.getCase("Prison");

        joueur.seDeplacer(prison.getId());
    }

}