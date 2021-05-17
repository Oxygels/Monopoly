package cases;

import joueur.Joueur;
import plateau.Plateau;

public class CaseAllerEnPrison extends Case {

    public CaseAllerEnPrison(int id, String nom) {
        super(id, nom);
    }

    @Override
    public void action(Joueur joueur) {
        Plateau plateau = Plateau.getPlateau();
        Case prison = plateau.getCases()
                .stream()
                .filter(c -> c.getNom().equals("PRISON"))
                .findAny()
                .get();
        joueur.seDeplacer(prison.getId());
    }

}