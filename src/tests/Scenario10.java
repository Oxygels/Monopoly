package tests;

import exception.MonopolyException;
import joueur.Joueur;
import plateau.Plateau;

public class Scenario10 {
    public static void launch() throws MonopolyException {
        System.out.println("[DEBUT] Scénario 10 : Tests pour le déplacement d’un joueur sur la case « Aller en Prison »");
        Plateau plateau = Plateau.getPlateau();
        plateau.init();
        Joueur jules = new Joueur("Jules");
        plateau.ajouterJoueur(jules);

        jules.seDeplacer(plateau.getCase("Gare du Nord").getId());
        jules.avancer(5);
        assert jules.getPositionCase().getNom().equals("Prison");
        jules.payerPrison();
        assert jules.getMontantBillet() == 1450;
        assert jules.getPositionCase().getNom().equals("Simple visite");

        System.out.println("[REUSSITE] Scénario 10 : Tests pour le déplacement d’un joueur sur la case « Aller en Prison »\n");
    }
}
