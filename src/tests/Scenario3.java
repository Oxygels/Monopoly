package tests;

import exception.MonopolyException;
import joueur.Joueur;
import plateau.Plateau;

public class Scenario3 {
    public static void launch() throws MonopolyException {
        System.out.println("[DEBUT] Scénario 3 : Tests pour le déplacement simple d’un joueur");

        Plateau plateau = Plateau.getPlateau();
        plateau.init();
        Joueur j = new Joueur("Quentin");
        j.avancer(5);
        assert j.getPositionCase().getNom().equals("Gare Montparnasse");
        assert j.getMontantBillet() == 1500;
        assert j.getProprietesPossedees().size() == 0;
        j.avancer(5);
        assert j.getPositionCase().getNom().equals("Simple visite");
        assert j.getMontantBillet() == 1500;
        assert j.getProprietesPossedees().size() == 0;

        j.seDeplacer(plateau.getCase("Rue de la Paix").getId());
        j.avancer(2);
        assert j.getPositionCase().getNom().equals("Boulevard de Belleville");
        assert j.getMontantBillet() == 1700;

        boolean test = false;
        try {
            j.avancer(1); // Le stack est vide donc une exception doit être lancée
        } catch (Exception ignore) {
            test = true;
        }
        assert test;
        System.out.println("[REUSSITE] Scénario 3 : Tests pour le déplacement simple d’un joueur\n");
    }
}
