package tests;

import cases.TerrainConstructible;
import exception.MonopolyException;
import joueur.Joueur;
import plateau.Plateau;

public class Scenario9 {

    public static void launch() throws MonopolyException {
        System.out.println("[DEBUT] Scénario 9 : Tests pour l’achat d’un hôtel");
        Plateau plateau = Plateau.getPlateau();
        plateau.init();

        Joueur quentin = new Joueur("Quentin");
        Joueur jules = new Joueur("Jules");

        plateau.ajouterJoueur(quentin);
        plateau.ajouterJoueur(jules);

        TerrainConstructible vaugirard = (TerrainConstructible) plateau.getCase("Rue de Vaugirard");
        TerrainConstructible courcelles = (TerrainConstructible) plateau.getCase("Rue de Courcelles");
        TerrainConstructible republique = (TerrainConstructible) plateau.getCase("Avenue de la République");
        quentin.acheterPropriete(vaugirard, null);
        quentin.acheterPropriete(courcelles, null);
        quentin.acheterPropriete(republique, null);
        for (int i = 0; i < 3; i++) {
            quentin.acheterMaison(vaugirard);
            quentin.acheterMaison(courcelles);
            quentin.acheterMaison(republique);
        }
        quentin.acheterMaison(courcelles);
        quentin.acheterMaison(republique);
        boolean exception = false;
        try {
            quentin.acheterMaison(republique);
        } catch (MonopolyException exception1) {
            exception = true;
        }
        assert exception;

        quentin.acheterMaison(vaugirard);
        quentin.acheterMaison(republique);
        assert quentin.getMontantBillet() == 530;
        jules.seDeplacer(plateau.getCase("Avenue de la République").getId());
        assert jules.getMontantBillet() == 900;
        assert quentin.getMontantBillet() == 1130;

        System.out.println("[REUSSITE] Scénario 9 : Tests pour l’achat d’un hôtel\n");
    }
}
