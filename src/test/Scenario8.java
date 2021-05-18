package test;

import cases.Propriete;
import cases.TerrainConstructible;
import exception.MonopolyException;
import joueur.Joueur;
import plateau.Plateau;

public class Scenario8 {
    public static void launch() throws MonopolyException {
        System.out.println("[DEBUT] Scénario 8 : Tests pour l’achat d’une maison");
        Plateau plateau = Plateau.getPlateau();
        plateau.init();

        Joueur quentin = new Joueur("Quentin");
        Joueur yacine = new Joueur("Yacine");

        TerrainConstructible vaugirard = (TerrainConstructible) plateau.getCase("Rue de Vaugirard");
        quentin.acheterPropriete(vaugirard, null);
        boolean exception = false;
        try {
            quentin.acheterMaison(vaugirard);
        } catch (MonopolyException exception1) {
            exception = true;
        }
        assert exception;
        quentin.acheterPropriete((Propriete) plateau.getCase("Rue de Courcelles"), null);
        quentin.acheterPropriete((Propriete) plateau.getCase("Avenue de la République"), null);
        assert quentin.getMontantBillet() == 1180;
        quentin.acheterMaison(vaugirard);
        assert quentin.getMontantBillet() == 1130;

        yacine.seDeplacer(plateau.getCase("Rue de Vaugirard").getId());
        assert yacine.getMontantBillet() == 1470;


        System.out.println("[REUSSITE] Scénario 8 : Tests pour l’achat d’une maison\n");
    }
}
