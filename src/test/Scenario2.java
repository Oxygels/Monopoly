package test;

import joueur.Joueur;
import plateau.Plateau;

public class Scenario2 {
    public static void launch() {
        System.out.println("[DEBUT] Scénario 2 : Tests pour la construction d'un joueur");
        Plateau plateau = Plateau.getPlateau();
        Joueur joueur = new Joueur("Quentin");
        plateau.ajouterJoueur(joueur);
        assert joueur.getMontantBillet() == 1500;
        assert joueur.getProprietesPossedees().size() == 0;
        assert plateau.getCases().get(joueur.getPosition()).getNom() == "Case Départ";
        assert plateau.getNbJoueurs() == 1;
        Joueur joueur2 = new Joueur("Yacine");
        plateau.ajouterJoueur(joueur2);
        assert plateau.getNbJoueurs() == 2;
        System.out.println("[REUSSITE] Scénario 2 : Tests pour la construction d'un joueur\n");
    }
}
