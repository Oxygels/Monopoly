package test;

import cases.Propriete;
import exception.MonopolyException;
import joueur.Joueur;
import plateau.Plateau;

public class Scenario7 {
    public static void launch() throws MonopolyException {
        System.out.println("[DEBUT] Scénario 7 : Tests pour le déplacement d’un joueur sur une compagnie achetée");

        Plateau plateau = Plateau.getPlateau();
        plateau.init();

        Joueur quentin = new Joueur("Quentin");
        Joueur yacine = new Joueur("Yacine");

        quentin.acheterPropriete((Propriete) plateau.getCase("Compagnie d'électricité"), null);
        assert quentin.getMontantBillet() == 1350;
        yacine.avancer(12);
        assert yacine.getPositionCase().getNom().equals("Compagnie d'électricité");
        assert yacine.getMontantBillet() == 1452;
        assert quentin.getMontantBillet() == 1398;

        quentin.acheterPropriete((Propriete) plateau.getCase("Compagnie des eaux"), null);
        assert quentin.getMontantBillet() == 1248;
        yacine.seDeplacer(plateau.getCase("Boulevard Malesherbes").getId());
        assert yacine.getMontantBillet() == 1452;
        yacine.avancer(4);
        assert yacine.getPositionCase().getNom().equals("Compagnie des eaux");
        assert yacine.getMontantBillet() == 1412;
        assert quentin.getMontantBillet() == 1288;

        System.out.println("[REUSSITE] Scénario 7 : Tests pour le déplacement d’un joueur sur une compagnie achetée\n");
    }
}
