package test;

import cases.Propriete;
import exception.MonopolyException;
import joueur.Joueur;
import plateau.Plateau;

public class Scenario6 {
    public static void launch() throws MonopolyException {
        System.out.println("[DEBUT] Scénario 6 : Tests pour le déplacement d’un joueur sur une gare achetée");
        Plateau plateau = Plateau.getPlateau();
        plateau.init();

        Joueur quentin = new Joueur("Quentin");
        Joueur yacine = new Joueur("Yacine");

        plateau.ajouterJoueur(quentin);
        plateau.ajouterJoueur(yacine);

        quentin.acheterPropriete((Propriete) plateau.getCase("Gare Montparnasse"), null);
        yacine.seDeplacer(plateau.getCase("Gare Montparnasse").getId());
        assert yacine.getMontantBillet() == 1450;
        assert quentin.getMontantBillet() == 1350;

        quentin.acheterPropriete((Propriete) plateau.getCase("Gare de Lyon"), null);
        yacine.seDeplacer(plateau.getCase("Gare de Lyon").getId());

        assert yacine.getMontantBillet() == 1350;
        assert quentin.getMontantBillet() == 1250; // 1350 - 200 + 2 * 50

        yacine.seDeplacer(plateau.getCase("Gare du Nord").getId());
        assert yacine.getMontantBillet() == 1350;
        assert quentin.getMontantBillet() == 1250;

        System.out.println("[REUSSITE] Scénario 6 : Tests pour le déplacement d’un joueur sur une gare achetée\n");

    }
}
